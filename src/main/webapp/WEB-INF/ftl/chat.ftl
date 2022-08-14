<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <#setting classic_compatible=true>
        <#--        <#include "css/chat.css">-->
    </style>
    <meta charset="UTF-8">
    <title>Chat</title>
</head>
<body>
<div id="username-page">
    <div class="username-page-container">
        <h1 class="title">Type your username</h1>
        <form id="usernameForm" name="usernameForm">
            <div class="form-group">
                <input type="text" id="name" placeholder="Username" autocomplete="off" class="form-control"/>
            </div>
            <div class="form-group">
                <button type="submit" class="accent username-submit">Start Chatting</button>
            </div>
        </form>
    </div>
</div>
<div id="chat-page" class="hidden">
    <div class="chat-container">
        <div class="chat-header">
            <h2>${film.title}</h2>
        </div>
        <div class="connecting">
            Connecting...
        </div>
        <ul id="messageArea">
            <#list history as historyMessage>
                <li class="chat-message">
                    <span>${historyMessage.user.login}</span>
                    <p>${historyMessage.message}</p>
                </li>
            </#list>
        </ul>
        <form id="messageForm" name="messageForm">
            <div class="form-group">
                <div class="input-group clearfix">
                    <input type="text" id="message" placeholder="Type a message..." autocomplete="off"
                           class="form-control"/>
                    <button type="submit" class="primary">Send</button>
                </div>
            </div>
        </form>
    </div>
    <div class="user-info">
        <div id="authInfo" class="auth">
            <h3>Users authentications</h3>
        </div>
        <div class="avatar">
            <div id="userAvatars">
                <h3>User's avatars</h3>
            </div>
            <form id="avatarForm" enctype="multipart/form-data" method="post" action="/images">
                <p>
                    <input type="file" name="avatar" id="avatar" multiple accept="image/*">
                    <input type="hidden" name="filmId" id="formFilmId">
                    <input type="hidden" name="userId" id="formUserId">
                    <button id="uploadButton">Upload</button>
                </p>
            </form>
        </div>
    </div>
</div>
<#-- версии | 1.1.4 | 2.3.3 | 1.3 |-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
<script>
    'use strict';
    let usernamePage = document.querySelector('#username-page');
    let chatPage = document.querySelector('#chat-page');
    let usernameForm = document.querySelector('#usernameForm');
    let messageForm = document.querySelector('#messageForm');
    let messageInput = document.querySelector('#message');
    let messageArea = document.querySelector('#messageArea');
    let connectingElement = document.querySelector('.connecting');
    let chatField = '${film.filmId}';
    let stompClient = null;
    let username = null;

    let colors = [
        '#2196F3', '#32c787', '#000C04', '#ff5652',
        '#ffc107', '#ff65af', '#345ff0', "#53f3cc"
    ];

    $(document).ready(function () {
        console.log("log: ready")
        document.getElementById("formFilmId").value = ('${film.filmId}');
        // let userCookie = getCookie("user");
        let userCookie = "vika"; //todo: получать куки
        if (userCookie) {
            console.log("log: readyIf")
            username = userCookie;
            usernamePage.classList.add('hidden');
            chatPage.classList.remove('hidden');
            let socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, onConnected, onError);
        } else {
            console.log("log: readyElse")
        }
    })

    function connect(event) {
        console.log("log: connect")
        username = document.querySelector('#name').value.trim();
        if (username) {
            document.cookie = "user=" + username;
            usernamePage.classList.add('hidden');
            chatPage.classList.remove('hidden');
            let socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, onConnected, onError);
        }
        event.preventDefault();
    }

    function onConnected() {
        console.log("log: onConnected")
        stompClient.subscribe('/topic/public', onMessageReceived);
        stompClient.send("/app/chat.addUser",
            {},
            JSON.stringify({
                type: 'JOIN', user: {
                    login: username
                }
            })
        )
        connectingElement.classList.add('hidden');
    }

    function onError(error) {
        console.log("log: onError")
        connectingElement.textContent = 'Could not connect to WebSocket server.';
        connectingElement.style.color = 'red';
    }

    function sendMessage(event) {
        console.log("log: sendMessage")
        let messageContent = messageInput.value.trim();
        if (messageContent && stompClient) {
            let chatMessage = {
                message: messageInput.value,
                type: 'CHAT',
                file: {
                    filmId: chatField
                },
                user: {
                    id: getCookie('userId'),
                    login: username
                }
            };
            stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
            messageInput.value = '';
        }
        event.preventDefault();
    }

    function onMessageReceived(payload) {
        console.log("log: onMessageReceived")
        let message = JSON.parse(payload.body);
        let messageElement = document.createElement('li');
        if (message.type === 'JSON') {
            messageElement.classList.add('event-message');
            message.message = message.user.login = 'joined';
            if (!getCookie("userId")) {
                document.cookie = "userId" + message.user.id;
            }
            if (getCookie("userId") == message.user.id) {
                document.getElementById("formUserId").valueOf(getCookie("userId"));
                getAuthList();
                getListOfAvatar();
            }
        } else if (message.type === 'LEAVE') {
            messageElement.classList.add('chat-message');
            message.message = message.user.login + ' left';
        } else {
            messageElement.classList.add('chat-message');
            let avatarElement = document.createElement('1');
            let avatarText = document.createTextNode(message.user.login[0]);
            avatarElement.appendChild(avatarText);
            avatarElement.style['background-color'] = getAvatarColor(message.user.login);
            messageElement.appendChild(avatarElement);
            let usernameElement = document.createElement('span');
            let usernameText = document.createTextNode(message.user.login);
            usernameElement.appendChild(usernameText);
            messageElement.appendChild(usernameElement);
        }
        let textElement = document.createElement('p');
        let messageText = document.createTextNode(message.message);
        textElement.appendChild(messageText);
        messageElement.appendChild(textElement);
        messageArea.appendChild(messageText);
        messageArea.scrollTop = messageArea.scrollHeight;
    }

    function getAvatarColor(messageSender) {
        console.log("log: getAvatarColor")
        let hash = 0;
        for (let i = 0; i < messageSender.length; i++) {
            hash = 31 * hash + messageSender.charCodeAt(i);
        }
        let index = Math.abs(hash % colors.length);
        return colors[index];
    }

    //закончили на 194 строке
    function getCookie(userId) {
        console.log("log: getCookie")
        return undefined;
    }

    function getAuthList() {
        console.log("log: getAuthList")
        return undefined;
    }

    function getListOfAvatar() {
        console.log("log: getListOfAvatar")
        return undefined;
    }
</script>
</body>
</html>