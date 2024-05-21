package com.example.demo.service;

import com.example.demo.dao.entities.User;

public interface UserManager {
    User findByLogin(String login);
    void registerUser(User user);
}
