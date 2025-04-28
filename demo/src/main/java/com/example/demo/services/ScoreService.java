package com.example.demo.services;

import com.example.demo.dtos.responses.ScoreResponseDTO;
import com.example.demo.models.Scores;

import java.util.List;

public interface ScoreService {
    List<Scores> getAllScores();
    ScoreResponseDTO getScoresById(String id);
}
