package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.models.Session;

import java.util.List;

public interface HallRepository {
    List<Hall> findAll();

    Hall get(Long id);

    void save(Hall hall);

    Integer getFromSerialNumber(Integer serialNumber);
}
