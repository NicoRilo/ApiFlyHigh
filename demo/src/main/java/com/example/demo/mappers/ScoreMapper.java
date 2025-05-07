package com.example.demo.mappers;

import com.example.demo.dtos.responses.ScoreResponseDTO;
import com.example.demo.models.Scores;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ScoreMapper {

    public ScoreResponseDTO toDto(Scores score) {
        if (score == null) return null;

        ScoreResponseDTO dto = new ScoreResponseDTO();
        dto.setId(score.getId());
        dto.setScore(score.getScore());
        dto.setMaxScore(score.getMaxScore());
        dto.setUserName(score.getUser().getName());

        return dto;
    }
}
