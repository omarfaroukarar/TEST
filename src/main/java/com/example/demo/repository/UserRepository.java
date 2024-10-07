package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// UserRepository interface extending JpaRepository for User entity
public interface UserRepository extends JpaRepository<User, Long> {
    // Method to find a User by username
    User findByUsername(String username);

    // Method to find a User by email
    User findByEmail(String email);
}
