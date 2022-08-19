package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

public interface UserRepository {

    User getById(Long id);

    User save(User user);

    User getByLogin(String login);
}
