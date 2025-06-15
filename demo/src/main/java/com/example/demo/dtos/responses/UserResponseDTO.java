package com.example.demo.dtos.responses;

import lombok.Data;

import java.util.List;

// DTO para devolver los datos de los usuarios

@Data
public class UserResponseDTO {
    private Integer id;
    private String name;
    private String password;
    private String bibliography;
    private String role;
    private List<ScoreResposneDTO> scores;

    @Data
    public static class ScoreResposneDTO {
        private Integer id;
        private Integer score;
        private Integer maxScore;
    }
}
