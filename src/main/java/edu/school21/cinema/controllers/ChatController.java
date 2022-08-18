package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.FilmRepository;
import edu.school21.cinema.services.AuthenticationService;
import edu.school21.cinema.services.ChatService;
import edu.school21.cinema.services.MessageService;
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
    private final MessageService messageService;
    private final FilmRepository filmRepository;
    private final ChatService chatService;

    public ChatController(AuthenticationService authenticationService,
                          MessageService messageService,
                          FilmRepository filmRepository,
                          ChatService chatService) {
        this.authenticationService = authenticationService;
        this.messageService = messageService;
        this.filmRepository = filmRepository;
        this.chatService = chatService;
    }

    @GetMapping(value = "/films/{film-id}/chat")
    public String startChat(@PathVariable("film-id") Long filmId, Model model) {
        Film film = filmRepository.getById(filmId);
        List<Message> lastMessages = messageService.getLastTwelveMessagesFromFilmId(filmId);
        model.addAttribute("film", film);
        model.addAttribute("history", lastMessages);
        return "chat";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {
        if (message.getType().equals(Message.MessageType.CHAT)) {
            messageService.save(message);
        }
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getUser().getLogin());
        Object clientIp = headerAccessor.getSessionAttributes().get("ip"); //test
//        String clientIp = (String) headerAccessor.getSessionAttributes().get("ip");
//        message.getUser().setId(authenticationService.authUser(message.getUser(), clientIp.toString()));
        message.getUser().setId(1L); //test
        return message;
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
    public @ResponseBody List<String> getListOfAvatars(@ModelAttribute("id") Long id) {
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
