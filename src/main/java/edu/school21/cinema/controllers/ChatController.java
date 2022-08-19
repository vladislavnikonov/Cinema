package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.*;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ChatController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final MessageService messageService;
    private final FilmService filmService;
    private final ChatService chatService;

    public ChatController(AuthenticationService authenticationService,
                          UserService userService,
                          MessageService messageService,
                          FilmService filmService,
                          ChatService chatService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.messageService = messageService;
        this.filmService = filmService;
        this.chatService = chatService;
    }

    @GetMapping(value = "/films/{film-id}/chat")
    public String startChat(@PathVariable("film-id") Long filmId, Model model) {
        Film film = filmService.get(filmId);
        List<Message> lastMessages = messageService.getLastTwelveMessagesByFilmId(filmId);
        model.addAttribute("film", film);
        model.addAttribute("history", lastMessages);
        return "chat";
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic")
    public Message sendMessage(@Payload Message message) {
        Film film = filmService.get(message.getFilm().getFilmId());
        User user = userService.get(message.getUser().getLogin());
        message.setFilm(film);
        message.setUser(user);
//        if (message.getType().equals(Message.MessageType.CHAT)) {
        messageService.save(message);
//        }
        return message;
    }

    @MessageMapping("/addUser")
    @SendTo("/topic")
    public void addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getUser().getLogin());
        Object clientIp = headerAccessor.getSessionAttributes().get("ip");
        User user = userService.save(message.getUser());
        authenticationService.save(user, clientIp.toString());
//        message.getUser().setId(authenticationService.authUser(message.getUser(), clientIp.toString()));
//        return message;
    }

    //загрузка авторизаций
    @GetMapping(value = "/userInfo/auth", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public @ResponseBody
    List<Authentication> getAuthList(@ModelAttribute("id") Long id) {
        List<Authentication> rs = authenticationService.getAllByUserId(id);
        if (rs.isEmpty()) {
            return null;
        }
        return rs;
    }

    //загрузка аватара
    @PostMapping(value = "/images", consumes = "multipart/form-data")
    public String uploadAvatar(@ModelAttribute("avatar") MultipartFile avatar,
                               @ModelAttribute("filmId") Long filmId,
                               @ModelAttribute("userId") Long userId) throws IOException {
        chatService.saveAvatar(avatar, userId);
        return "redirect: /films/" + filmId + "/chat";
    }

    //загрузка аватара
    @GetMapping(value = "/chat/UsersAvatars/list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public @ResponseBody
    List<String> getListOfAvatars(@ModelAttribute("id") Long id) {
        List<String> listOfAvatars = chatService.getListOfAvatarByUserId(id);
        return listOfAvatars;
    }

    //загрузка аватара
    @GetMapping(value = "/chat/avatar/{userId}/{fileName}")
    public String getListOfAvatars(@PathVariable("fileName") String filename, @PathVariable("userId") Long userid, Model model) {
        String avatar = chatService.getAvatar(filename, userid);
        model.addAttribute("avatar", avatar);
        return "images";
    }
}
