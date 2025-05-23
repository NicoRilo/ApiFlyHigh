package com.example.demo.repositories;

import com.example.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByName(String name);
}
