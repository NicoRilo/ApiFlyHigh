package com.example.demo.services;


import com.example.demo.dtos.responses.UserResponseDetailDTO;
import com.example.demo.dtos.responses.UserResponseDTO;
import com.example.demo.models.Users;

import java.util.List;

public interface UserService {

    // Devuelve una lista con todos los usuarios en la base de datos
    List<Users> getAllUsers();

    // Obtiene un usuario por su id y devuelve un DTO simplificado para la respuesta
    UserResponseDTO getUsersById(Integer id);

    // Obtiene detalles completos de un usuario dado su nombre de usuario
    UserResponseDetailDTO getUserDetails(String username);

    // Valida las credenciales de inicio de sesión: usuario y contraseña
    boolean inicioSesionValidate(String username, String password);

    // Registra un nuevo usuario con nombre y contraseña
    boolean registerUser(String username, String password);

    // Elimina un usuario por su nombre de usuario
    void deleteUserByName(String username);

    // Actualiza un usuario con los datos recibidos en el DTO
    boolean updateUser(UserResponseDetailDTO userDto);
}
