package com.example.demo.service;

import com.example.demo.dao.entities.Role;
import com.example.demo.dao.entities.User;
import com.example.demo.dao.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserManager {

    private final PasswordEncoder passwordEncoder;
    private static List<User> users = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void postConstruct() {
        User user = new User();
        user.setId(user.getId());
        user.setRole(Role.ADMIN);
        user.setUsername("hiba");
        user.setEmail("hiba@gmail.com");
        user.setPassword(passwordEncoder.encode("hiba"));

        //userRepository.save(user);
    }

    @Override
    @Transactional
    public void registerUser(User user) {
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByLogin(String username) {
        return userRepository.findByUsername(username);
    }
}
