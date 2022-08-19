package edu.school21.cinema.services;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.AuthenticationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    public List<Authentication> getAllByUserId(Long id) {
        return null;
    }

    public void save(User user, String clientIp) {
        LocalDateTime localDateTime = LocalDateTime.now();
        // TODO: 19.08.2022 посмотреть как реализовано в fwa сохранение авторизации
        Authentication authentication = new Authentication(user, localDateTime, clientIp);
        authenticationRepository.save(authentication);
    }
}
