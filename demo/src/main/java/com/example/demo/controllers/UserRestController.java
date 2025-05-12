package com.example.demo.controllers;



import com.example.demo.dtos.requests.LoginRequestDTO;
import com.example.demo.dtos.requests.RegisterRequestDTO;
import com.example.demo.dtos.responses.UserResponseDTO;
import com.example.demo.models.Users;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/proyecto/users")
public class UserRestController {
    UserService userService;
    UserRepository userRepository;

    @Autowired
    public UserRestController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        UserResponseDTO user = userService.getUsersById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        boolean isValid = userService.inicioSesionValidate(loginRequest.getUsername(), loginRequest.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registerRequest) {
        boolean registered = userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
        if (registered) {
            return ResponseEntity.ok("Register successfull");
        } else {
            return ResponseEntity.status(409).body("Username already exists");
        }
    }

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
}
