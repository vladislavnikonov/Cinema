package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessageRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Message> getLastTwelveMessagesByFilmId(Long filmId) {
        return entityManager.createQuery("from Message where film.filmId =:filmId order by message.id desc", Message.class)
                .setParameter("filmId", filmId)
                .getResultList()
                .stream()
                .limit(20)
                .sorted((o1, o2) -> {
                    if (o1.getId() == o2.getId()) {
                        return 0;
                    }
                    return (o1.getId() > o2.getId()) ? 1 : -1;
                })
                .collect(Collectors.toList());
    }
}
