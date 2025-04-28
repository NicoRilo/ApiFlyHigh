package com.example.demo.controllers;

import com.example.demo.dtos.responses.ScoreResponseDTO;
import com.example.demo.models.Scores;
import com.example.demo.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/proyecto/scores")
public class ScoreRestController {
    ScoreService scoreService;

    @Autowired
    public ScoreRestController(ScoreService scoreService){
        this.scoreService = scoreService;
    }
    @GetMapping()
    public ResponseEntity<List<Scores>> getAllScores() {
        return ResponseEntity.ok(scoreService.getAllScores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreResponseDTO> getScoreById(@PathVariable String id) {
        ScoreResponseDTO score = scoreService.getScoresById(id);
        if (score == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(score);
    }

}
