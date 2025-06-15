package com.example.demo.services;

import com.example.demo.dtos.responses.UserResponseDetailDTO;
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

    // Valida las credenciales del usuario para el login
    @Override
    public boolean inicioSesionValidate(String username, String password) {
        Users user = userRepository.findByName(username);
        return user != null && user.getPassword().equals(password);
    }

    // Devuelve la lista de todos los usuarios en la base de datos
    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtiene un usuario por su ID y lo mapea a DTO para respuesta
    @Override
    public UserResponseDTO getUsersById(Integer id) {
        Users user = userRepository.findById(id).orElse(null);
        return userMapper.toDto(user);
    }

    // Obtiene los detalles completos de un usuario por su nombre y lo mapea a DTO con detalles
    @Override
    public UserResponseDetailDTO getUserDetails(String username) {
        Users user = userRepository.findByName(username);
        return userMapper.toDetailDto(user);
    }

    // Registra un nuevo usuario si no existe uno con el mismo nombre
    @Override
    public boolean registerUser(String username, String password) {
        if (userRepository.findByName(username) != null) {
            return false;
        }

        Users newUser = new Users();
        newUser.setName(username);
        newUser.setPassword(password);
        newUser.setBibliography("Esta es la bibliografía");
        newUser.setRole("player");

        userRepository.save(newUser);

        Scores initialScore = new Scores();
        initialScore.setScore(0);
        initialScore.setMaxScore(1);
        initialScore.setUser(newUser);

        scoreRepository.save(initialScore);
        return true;
    }

    // Borra un usuario por su nombre
    @Override
    public void deleteUserByName(String username) {
        Users user = userRepository.findByName(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    // Actualiza la información del usuario según DTO recibido
    @Override
    public boolean updateUser(UserResponseDetailDTO userDto) {
        Users user = userRepository.findByName(userDto.getUsername());
        if (user != null) {
            user.setPassword(userDto.getPassword());
            user.setBibliography(userDto.getBibliography());
            userRepository.save(user);
            return true;
        }
        return false;
    }
}



