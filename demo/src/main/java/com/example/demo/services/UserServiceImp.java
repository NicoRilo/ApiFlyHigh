package com.example.demo.services;

import com.example.demo.dtos.responses.UserResponseDTO;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Users;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    private UserRepository userRepository;
    private UserMapper userMapper;
    @Autowired
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        Users user = userRepository.findByName(username);
        return user != null && user.getPassword().equals(password);
    }
    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public UserResponseDTO getUsersById(String id) {
        Users user = userRepository.findById(id).orElse(null);
        return userMapper.toDto(user);
    }
}



