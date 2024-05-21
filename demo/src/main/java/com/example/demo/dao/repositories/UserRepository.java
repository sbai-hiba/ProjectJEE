package com.example.demo.dao.repositories;

import com.example.demo.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUsername(String username);
}