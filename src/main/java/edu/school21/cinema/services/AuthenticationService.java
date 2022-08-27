package edu.school21.cinema.services;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.AuthenticationRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    public List<Authentication> getAllByUserId(Long id) {
        return authenticationRepository.getAuthenticationByUserId(id);
    }

    public void save(User user, String clientIp) {
        Date date = new Date();
        Authentication authentication = new Authentication(user, date, new Time(date.getTime()), clientIp);
        authenticationRepository.save(authentication);
    }
}
