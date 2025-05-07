package com.example.demo.controllers;

import com.example.demo.dtos.requests.ScoreRequestDTO;
import com.example.demo.dtos.responses.ScoreResponseDTO;
import com.example.demo.models.Scores;
import com.example.demo.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/max/{username}")
    public ResponseEntity<Integer> getMaxScore(@PathVariable String username) {
        Optional<Integer> maxScore = scoreService.getMaxScoreByUsername(username);
        if (maxScore.isPresent()) {
            return ResponseEntity.ok(maxScore.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addScore(@RequestBody ScoreRequestDTO scoreRequest) {
        boolean result = scoreService.saveScore(scoreRequest);

        if (result) {
            return ResponseEntity.ok("Score saved");
        } else {
            return ResponseEntity.status(400).body("Error saving score.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScore(@PathVariable String id) {
        boolean deleted = scoreService.deleteScoreById(id);
        if (deleted) {
            return ResponseEntity.ok("Score eliminado correctamente");
        } else {
            return ResponseEntity.status(404).body("Score no encontrado");
        }
    }

}
