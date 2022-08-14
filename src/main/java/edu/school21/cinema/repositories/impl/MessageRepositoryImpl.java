package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessageRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Message> findAll() {
        return entityManager.createQuery("from Message", Message.class).getResultList();
    }

    @Override
    @Transactional
    public Message getById(Long id) {
        return entityManager.find(Message.class, id);
    }

    @Override
    @Transactional
    public void save(Message message) {
        entityManager.merge(message);
    }

    @Override
    public List<Message> getLastTwelveMessagesFromFilmId(Long filmId) {
        return entityManager.createQuery("from Message where film.filmId =:filmId order filmId", Message.class).getResultList();
    }
}
