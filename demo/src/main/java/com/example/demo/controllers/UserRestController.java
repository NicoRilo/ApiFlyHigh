package com.example.demo.controllers;



import com.example.demo.dtos.requests.LoginRequestDTO;
import com.example.demo.dtos.requests.RegisterRequestDTO;
import com.example.demo.dtos.responses.UserResponseDetailDTO;
import com.example.demo.dtos.responses.UserResponseDTO;
import com.example.demo.models.Users;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define esta clase como un controlador REST para manejar solicitudes HTTP
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen
@RequestMapping("/proyecto/users") // Ruta para los endpoints
public class UserRestController {
    UserService userService;
    UserRepository userRepository;

    @Autowired
    public UserRestController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // Endpoint GET para obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    // Endpoint GET para obtener el usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        UserResponseDTO user = userService.getUsersById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Enpoint para iniciar sesion
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        boolean isValid = userService.inicioSesionValidate(loginRequest.getUsername(), loginRequest.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // Endpoint para registrarse
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registerRequest) {
        boolean registered = userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
        if (registered) {
            return ResponseEntity.ok("Register successfull");
        } else {
            return ResponseEntity.status(409).body("Username already exists");
        }
    }

    // Endpoint para eliminar un usuario
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUserByName(@PathVariable String username) {
        Users user = userRepository.findByName(username);
        if (user != null) {
            userService.deleteUserByName(username);
            return ResponseEntity.ok("User delete successfull");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    // Endpoint para ver detalles de un usuario
    @GetMapping("/details/{username}")
    public ResponseEntity<UserResponseDetailDTO> getUserDetails(@PathVariable String username) {
        UserResponseDetailDTO dto = userService.getUserDetails(username);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    // Enpoint para modificar los detalles de un usuario
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserResponseDetailDTO userDto) {
        boolean updated = userService.updateUser(userDto);
        if (updated) {
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update user");
        }
    }
}
