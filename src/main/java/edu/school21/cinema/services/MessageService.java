package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getLastTwelveMessagesFromFilmId(Long filmId) {
        return null;
    }

    public void save(Message message) {
        // TODO: 18.08.2022 обработать сообщение
        messageRepository.save(message);
    }
}
