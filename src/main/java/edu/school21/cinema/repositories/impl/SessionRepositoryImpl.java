package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.SessionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
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
//        entityManager.persist(session);
        entityManager.merge(session);
    }

    @Override
    public Session getByHallIdAndSessionDate(Long hallId, Date sessionDate) {
        Session session = null;
        try {
            session = entityManager.createQuery("from Session where hall.hallId = :hallId and sessionDate = :sessionDate", Session.class)
                    .setParameter("hallId", hallId)
                    .setParameter("sessionDate", sessionDate)
                    .getSingleResult();
        } catch (NoResultException ignored) {}
        return session;
    }

    @Override
    public List<Session> searchByRequest(String request) {
        request = request + "%";
        return entityManager.createQuery("select new Session(s.sessionId, s.ticketCost, s.sessionDate, f) " +
                "from Session s join Film f on s.film.filmId = f.filmId " +
                "where f.title like :request", Session.class)
                .setParameter("request", request)
                .getResultList();
    }
}
