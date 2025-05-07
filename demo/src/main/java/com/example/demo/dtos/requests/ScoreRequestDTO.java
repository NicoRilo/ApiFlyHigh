package com.example.demo.dtos.requests;

import lombok.Data;

@Data

public class ScoreRequestDTO {
    private String username;
    private Integer score;
    private Integer maxScore;
}
