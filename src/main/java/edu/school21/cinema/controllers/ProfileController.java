package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.services.AuthenticationService;
import edu.school21.cinema.services.ChatService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProfileController {
    private final AuthenticationService authenticationService;
    private final ChatService chatService;

    public ProfileController(AuthenticationService authenticationService, ChatService chatService) {
        this.authenticationService = authenticationService;
        this.chatService = chatService;
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
        return "image";
    }
}
