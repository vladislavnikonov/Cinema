package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;

import java.util.List;

public interface SessionService {

    void save(Session session, Long filmId, Long hallId);

    List<Session> getAll();

    Session get(Long id);
}
