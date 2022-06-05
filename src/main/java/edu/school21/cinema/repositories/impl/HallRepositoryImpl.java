package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class HallRepositoryImpl implements HallRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Hall> findAll() {
        return entityManager.createQuery("from Hall", Hall.class).getResultList();
    }

    @Override
    @Transactional
    public Hall getById(Long id) {
        return entityManager.find(Hall.class, id);
    }

    @Override
    @Transactional
    public void save(Hall hall) {
        entityManager.merge(hall);
    }

    @Override
    public Hall getBySerialNumber(Integer serialNumber) {
        Hall hall = null;
        try {
            hall = entityManager.createQuery("from Hall where serialNumber = :serialNumber", Hall.class)
                    .setParameter("serialNumber", serialNumber)
                    .getSingleResult();
        } catch (NoResultException ignored) {}
        return hall;
    }
}
