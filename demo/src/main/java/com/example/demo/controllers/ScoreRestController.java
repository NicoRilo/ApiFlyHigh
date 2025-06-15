package com.example.demo.controllers;

import com.example.demo.dtos.requests.ScoreRequestDTO;
import com.example.demo.dtos.responses.ScoreResponseDTO;
import com.example.demo.dtos.responses.ScoreTop10ResponseDTO;

import com.example.demo.models.Scores;
import com.example.demo.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Define esta clase como un controlador REST para manejar solicitudes HTTP
@RequestMapping("/proyecto/scores") // Ruta para los endpoints
public class ScoreRestController {
    ScoreService scoreService;

    @Autowired
    public ScoreRestController(ScoreService scoreService){
        this.scoreService = scoreService;
    }

    // Endpoint GET para obtener todas las puntuaciones
    @GetMapping()
    public ResponseEntity<List<Scores>> getAllScores() {
        return ResponseEntity.ok(scoreService.getAllScores());
    }

    // Endpoint GET para obtener la puntucion por ID
    @GetMapping("/{id}")
    public ResponseEntity<ScoreResponseDTO> getScoreById(@PathVariable String id) {
        ScoreResponseDTO score = scoreService.getScoresById(id);
        if (score == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(score);
    }

    // Endpoint GET para pbtener la puntuacion maxima de un usuario
    @GetMapping("/max/{username}")
    public ResponseEntity<Integer> getMaxScore(@PathVariable String username) {
        Optional<Integer> maxScore = scoreService.getMaxScoreByUsername(username);
        if (maxScore.isPresent()) {
            return ResponseEntity.ok(maxScore.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    // Enpoint POST para añadir una puntuacion
    @PostMapping("/add")
    public ResponseEntity<String> addScore(@RequestBody ScoreRequestDTO scoreRequest) {
        boolean result = scoreService.saveScore(scoreRequest);

        if (result) {
            return ResponseEntity.ok("Score saved");
        } else {
            return ResponseEntity.status(400).body("Error saving score.");
        }
    }

    // Endpoint DELETE para eliminar una puntuacion
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScore(@PathVariable String id) {
        boolean deleted = scoreService.deleteScoreById(id);
        if (deleted) {
            return ResponseEntity.ok("Score eliminado correctamente");
        } else {
            return ResponseEntity.status(404).body("Score no encontrado");
        }
    }

    // Endpoint GET para obtener las 10 primeros mejores puntuaciones
    @GetMapping("/top10")
    public ResponseEntity<List<ScoreTop10ResponseDTO>> getTop10Scores() {
        return ResponseEntity.ok(scoreService.getTop10Scores());
    }

}
