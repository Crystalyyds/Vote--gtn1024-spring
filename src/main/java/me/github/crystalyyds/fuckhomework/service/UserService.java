package me.github.crystalyyds.fuckhomework.service;

import me.github.crystalyyds.fuckhomework.entity.User;
import me.github.crystalyyds.fuckhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User newUser(String username, String password,String number) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNumber(number);
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

}
