package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;

import java.util.List;

public interface MessageRepository {

    List<Message> findAll();

    Message getById(Long id);

    void save(Message message);

    List<Message> getLastTwelveMessagesByFilmId(Long filmId);
}
