package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.models.Message;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepository {

    @Transactional
    List<Message> findAll();

    @Transactional
    Message getById(Long id);

    @Transactional
    void save(Message message);

    List<Message> getLastTwelveMessagesFromFilmId(Long filmId);
}
