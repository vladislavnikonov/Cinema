package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends BaseCRUDRepository {

    List<Film> findAll();

    Film get(Long id);

    void save(Film film);

    Film getByTitle(String title);
}
