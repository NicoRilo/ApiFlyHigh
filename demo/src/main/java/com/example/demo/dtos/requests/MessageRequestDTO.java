package com.example.demo.dtos.requests;

public class MessageRequestDTO {
    // DTO para recibir los datos para el inicio de sesion
    private String username;
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
