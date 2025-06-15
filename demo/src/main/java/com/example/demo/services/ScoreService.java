package com.example.demo.services;

import com.example.demo.dtos.requests.ScoreRequestDTO;
import com.example.demo.dtos.responses.ScoreResponseDTO;
import com.example.demo.dtos.responses.ScoreTop10ResponseDTO;
import com.example.demo.models.Scores;

import java.util.List;
import java.util.Optional;

public interface ScoreService {

    // Obtiene todos los registros de puntuación existentes
    List<Scores> getAllScores();

    // Obtiene una puntuación especifica por su ID, devuelta como DTO
    ScoreResponseDTO getScoresById(String id);

    // Guarda una nueva puntuación a partir del DTO recibido
    boolean saveScore(ScoreRequestDTO scoreRequest);

    // Obtiene el puntaje maximo de un usuario específico por su nombre de usuario
    Optional<Integer> getMaxScoreByUsername(String username);

    // Elimina una puntuación según su ID
    boolean deleteScoreById(String id);

    // Devuelve el top 10 de las puntuaciones mas altas, en forma de DTO
    List<ScoreTop10ResponseDTO> getTop10Scores();
}
