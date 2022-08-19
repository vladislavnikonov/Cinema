package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.repositories.FilmRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FilmRepositoryImpl implements FilmRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Film> findAll() {
        return entityManager.createQuery("from Film", Film.class).getResultList();
    }

    @Override
    @Transactional
    public Film getById(Long id) {
        return entityManager.find(Film.class, id);
    }

    @Override
    @Transactional
    public void save(Film film) {
        entityManager.merge(film);
    }

    @Override
    public Film getByTitle(String title) {
        Film film = null;
        try {
            film = entityManager.createQuery("from Film where title = :title", Film.class)
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        }
        return film;
    }
}
