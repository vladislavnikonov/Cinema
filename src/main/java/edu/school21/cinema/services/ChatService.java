package edu.school21.cinema.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ChatService {
    public String getAvatar(String filename, Long userid) {
        return null;
    }

    public List<String> getListOfAvatarByUserId(Long id) {
        return null;
    }

    public void saveAvatar(MultipartFile avatar, Long userId) {
    }
}
