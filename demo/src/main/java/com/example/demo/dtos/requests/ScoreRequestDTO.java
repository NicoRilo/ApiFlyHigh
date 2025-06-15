package com.example.demo.dtos.requests;

import lombok.Data;

// DTO para recibir los datos de puntuacion que el usuario envia al servidor
@Data
public class ScoreRequestDTO {
    private String username;
    private Integer score;
    private Integer maxScore;
}
