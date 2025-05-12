package com.example.demo.services;

import com.example.demo.dtos.responses.UserResponseDTO;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Scores;
import com.example.demo.models.Users;
import com.example.demo.repositories.ScoreRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    private UserRepository userRepository;
    private UserMapper userMapper;
    private ScoreRepository scoreRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper, ScoreRepository scoreRepository){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public boolean inicioSesionValidate(String username, String password) {
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

    @Override
    public boolean registerUser(String username, String password) {
        if (userRepository.findByName(username) != null) {
            return false;
        }

        Users newUser = new Users();
        newUser.setName(username);
        newUser.setPassword(password);
        newUser.setCoins(0);
        newUser.setRole("player");

        userRepository.save(newUser);

        Scores initialScore = new Scores();
        initialScore.setScore(0);
        initialScore.setMaxScore(0);
        initialScore.setUser(newUser);

        scoreRepository.save(initialScore);
        return true;
    }

    @Override
    public void deleteUserByName(String username) {
        Users user = userRepository.findByName(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}



