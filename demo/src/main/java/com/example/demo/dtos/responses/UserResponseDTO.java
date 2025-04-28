package com.example.demo.dtos.responses;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private Integer id;
    private String name;
    private Integer coins;
    private String role;
    private List<ScoreResposneDTO> scores;

    @Data
    public static class ScoreResposneDTO {
        private Integer id;
        private Integer score;
        private String date;
    }
}
