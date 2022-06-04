package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
        return entityManager.createQuery("FROM Hall", Hall.class).getResultList();
    }

    @Override
    public Hall get(Long id) {
        return null;
    }

    @Override
    public void save(Hall hall) {

    }

    @Override
    public Integer getFromSerialNumber(Integer serialNumber) {
        return null;
    }
}
