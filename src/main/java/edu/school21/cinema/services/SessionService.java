package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;

import java.text.ParseException;
import java.util.List;

public interface SessionService {

    void save(Session session, Long filmId, Long hallId);

    List<Session> findAll();

    Session get(Long id);

    boolean create(Long filmId, Long hallId, Integer ticketCost, String sessionDate) throws ParseException;
}
