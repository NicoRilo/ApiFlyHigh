package com.example.demo.dtos.responses;

import lombok.Data;

@Data
public class ScoreResponseDTO {
    private Integer id;
    private Integer score;
    private Integer maxScore;
    private String userName;
}
