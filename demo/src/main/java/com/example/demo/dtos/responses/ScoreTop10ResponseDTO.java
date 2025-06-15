package com.example.demo.dtos.responses;

import lombok.Data;

// DTO para devolver los datos de las 10 mejores puntuaciones
@Data
public class ScoreTop10ResponseDTO {
    private Integer maxScore;
    private String username;
}
