package com.example.demo.dtos.responses;

import lombok.Data;

// DTO para devolver los datos de los detalles del usuario
@Data
public class UserResponseDetailDTO {
    private String username;
    private String password;
    private String bibliography;
}
