package com.example.demo.services;

import com.example.demo.dtos.responses.ScoreResponseDTO;
import com.example.demo.mappers.ScoreMapper;
import com.example.demo.models.Scores;
import com.example.demo.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImp implements ScoreService{
    private ScoreRepository scoreRepository;
    private ScoreMapper scoreMapper;
    @Autowired
    public ScoreServiceImp(ScoreRepository scoreRepository, ScoreMapper scoreMapper){
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
    }
    @Override
    public List<Scores> getAllScores() {
        return scoreRepository.findAll();
    }

    @Override
    public ScoreResponseDTO getScoresById(String id) {
        Scores score = scoreRepository.findById(id).orElse(null);
        return scoreMapper.toDto(score);
    }
}
