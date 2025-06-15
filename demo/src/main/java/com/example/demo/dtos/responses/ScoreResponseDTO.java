package com.example.demo.dtos.responses;

import lombok.Data;

// DTO para devolver los datos de las puntuaciones
@Data
public class ScoreResponseDTO {
    private Integer id;
    private Integer score;
    private Integer maxScore;
    private String username;
}
