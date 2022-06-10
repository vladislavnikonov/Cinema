package edu.school21.cinema.services;

import edu.school21.cinema.exceptions.NotAllDataException;
import edu.school21.cinema.exceptions.ObjectAlreadyExistsException;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.SaveFilm;

import java.util.List;

public interface FilmService {

    void saveFilm(SaveFilm saveFilm) throws NotAllDataException, ObjectAlreadyExistsException;

    void updateFilm(SaveFilm saveFilm);

    List<Film> findAll();

    boolean create(Film film);
}
