package edu.school21.cinema.services;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.models.SaveFilm;
import exceptions.NotAllDataException;
import exceptions.ObjectAlreadyExistsException;

import java.util.List;

public interface FilmService {

    void saveFilm(SaveFilm saveFilm) throws NotAllDataException, ObjectAlreadyExistsException;

    void updateFilm(SaveFilm saveFilm);

    List<Film> getAll();
}
