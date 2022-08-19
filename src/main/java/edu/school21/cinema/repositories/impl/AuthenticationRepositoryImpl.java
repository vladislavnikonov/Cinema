package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.repositories.AuthenticationRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AuthenticationRepositoryImpl implements AuthenticationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Authentication authentication) {
        entityManager.merge(authentication);
    }

    @Override
    public List<Authentication> getAuthenticationByUserId(Integer userId) {
        return null;
    }
}
