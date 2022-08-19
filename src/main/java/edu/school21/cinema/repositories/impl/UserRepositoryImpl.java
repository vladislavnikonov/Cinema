package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return entityManager.merge(user);
    }

    @Override
    @Transactional
    public User getByLogin(String login) {
        User user = null;
        try {
            user = entityManager.createQuery("from User where login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        }
        return user;
    }
}
