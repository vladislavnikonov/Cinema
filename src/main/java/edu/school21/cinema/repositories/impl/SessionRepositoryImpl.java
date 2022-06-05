package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.SessionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Session> findAll() {
        return entityManager.createQuery("from Session", Session.class).getResultList();
    }

    @Override
    @Transactional
    public Session get(Long id) {
        return entityManager.find(Session.class, id);
    }

    @Override
    @Transactional
    public void save(Session session) {
        entityManager.persist(session);
    }
}
