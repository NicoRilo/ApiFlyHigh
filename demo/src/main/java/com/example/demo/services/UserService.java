package com.example.demo.services;


import com.example.demo.dtos.responses.UserResponseDTO;
import com.example.demo.models.Users;

import java.util.List;

public interface UserService {
    List<Users> getAllUsers();
    UserResponseDTO getUsersById(String id);
    boolean validateCredentials(String username, String password);
}
