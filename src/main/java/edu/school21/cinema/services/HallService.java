package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;

import java.util.List;

public interface HallService {

    List<Hall> findAll();

    boolean create(Hall hall);
}
