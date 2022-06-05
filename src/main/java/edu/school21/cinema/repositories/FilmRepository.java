package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;

import java.util.List;

public interface FilmRepository {

    List<Film> findAll();

    Film getById(Long id);

    void save(Film film);

    Film getByTitle(String title);
}
