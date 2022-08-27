package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.AuthenticationService;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.MessageService;
import edu.school21.cinema.services.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ChatController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final MessageService messageService;
    private final FilmService filmService;

    public ChatController(AuthenticationService authenticationService,
                          UserService userService,
                          MessageService messageService,
                          FilmService filmService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.messageService = messageService;
        this.filmService = filmService;
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
        headerAccessor.getSessionAttributes().put("username", message.getUser().getLogin()); //del
        Object clientIp = headerAccessor.getSessionAttributes().get("ip");
        User user = userService.save(message.getUser());
        authenticationService.save(user, clientIp.toString());
//        message.getUser().setId(authenticationService.authUser(message.getUser(), clientIp.toString()));
//        return message;
    }
}
