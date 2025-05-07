package com.example.demo.services;

import com.example.demo.dtos.requests.ScoreRequestDTO;
import com.example.demo.dtos.responses.ScoreResponseDTO;
import com.example.demo.models.Scores;

import java.util.List;
import java.util.Optional;

public interface ScoreService {
    List<Scores> getAllScores();
    ScoreResponseDTO getScoresById(String id);
    boolean saveScore(ScoreRequestDTO scoreRequest);
    Optional<Integer> getMaxScoreByUsername(String username);
    boolean deleteScoreById(String id);

}
