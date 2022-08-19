package edu.school21.cinema.services;

import edu.school21.cinema.models.Film;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilmService {

    List<Film> findAll();

    boolean create(String title, Integer releaseYear, Integer ageRegistration, String description, MultipartFile file);

    Film get(Long id);
}
