package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;

import java.util.Date;
import java.util.List;

public interface SessionRepository {
    List<Session> findAll();

    Session get(Long id);

    void save(Session session);

    Session getByHallIdAndSessionDate(Long hallId, Date sessionDate);
}
