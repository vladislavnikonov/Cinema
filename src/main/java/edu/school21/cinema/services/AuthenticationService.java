package edu.school21.cinema.services;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    public List<Authentication> getAllByUserId(Long id) {
        return null;
    }

    public Long authUser(User user, String clientIp) {
        return null;
    }
}
