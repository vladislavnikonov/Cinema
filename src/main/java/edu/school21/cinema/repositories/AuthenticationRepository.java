package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Authentication;

import java.util.List;

public interface AuthenticationRepository {
    void save(Authentication authentication);

    List<Authentication> getAuthenticationByUserId(Integer userId);
}
