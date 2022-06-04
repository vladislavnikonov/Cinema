package edu.school21.cinema.services.impl;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.SessionRepository;
import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    public SessionServiceImpl(@Qualifier("sessionRepositoryImpl") SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void save(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public List<Session> getAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session get(Long id) {
        return sessionRepository.get(id);
    }
}
