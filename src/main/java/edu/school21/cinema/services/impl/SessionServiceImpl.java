package edu.school21.cinema.services.impl;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.FilmRepository;
import edu.school21.cinema.repositories.HallRepository;
import edu.school21.cinema.repositories.SessionRepository;
import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;

    public SessionServiceImpl(@Qualifier("sessionRepositoryImpl") SessionRepository sessionRepository, FilmRepository filmRepository, HallRepository hallRepository) {
        this.sessionRepository = sessionRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }

    @Override
    public void save(Session session, Long filmId, Long hallId) {
        session.setFilm(filmRepository.getById(filmId));
        session.setHall(hallRepository.getById(hallId));
        sessionRepository.save(session);
    }

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session get(Long id) {
        return sessionRepository.get(id);
    }

    @Override
    public boolean create(Long filmId, Long hallId, Integer ticketCost, String sessionDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = formatter.parse(sessionDate);
        if (sessionRepository.getByHallIdAndSessionDate(hallId, date) != null) {
            return false;
        }
        Session session = new Session(ticketCost, date);
        save(session, filmId, hallId);
        return true;
    }

    @Override
    public List<Session> searchByRequest(String request) {
        return sessionRepository.searchByRequest(request);
    }
}
