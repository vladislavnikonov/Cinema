package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        User exist = get(user.getLogin());
        if (exist == null) {
            return userRepository.save(user);
        }
        return exist;
    }

    public User get(String login) {
        return userRepository.getByLogin(login);
    }
}
