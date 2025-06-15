package com.example.demo.dtos.requests;

public class RegisterRequestDTO {

    // DTO para recibir los datos para el registro
    private String username;
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
