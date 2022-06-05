package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.models.Session;

import java.util.List;

public interface HallRepository {
    List<Hall> findAll();

    Hall getById(Long id);

    void save(Hall hall);

    Hall getBySerialNumber(Integer serialNumber);
}
