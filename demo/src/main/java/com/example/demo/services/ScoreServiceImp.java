package com.example.demo.services;

import com.example.demo.dtos.requests.ScoreRequestDTO;
import com.example.demo.dtos.responses.ScoreResponseDTO;
import com.example.demo.dtos.responses.ScoreTop10ResponseDTO;
import com.example.demo.mappers.ScoreMapper;
import com.example.demo.models.Scores;
import com.example.demo.models.Users;
import com.example.demo.repositories.ScoreRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImp implements ScoreService{
    private ScoreRepository scoreRepository;
    private ScoreMapper scoreMapper;
    private UserRepository userRepository;
    @Autowired
    public ScoreServiceImp(ScoreRepository scoreRepository, ScoreMapper scoreMapper, UserRepository userRepository){
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
        this.userRepository = userRepository;
    }

    // Obtiene todos los registros de puntuaciones desde la base de datos
    @Override
    public List<Scores> getAllScores() {
        return scoreRepository.findAll();
    }

    // Obtiene una puntuación por su ID y la convierte a DTO para la respuesta
    @Override
    public ScoreResponseDTO getScoresById(String id) {
        Scores score = scoreRepository.findById(id).orElse(null);
        return scoreMapper.toDto(score);
    }

    // Guarda una nueva puntuación, asociada a un usuario
    @Override
    public boolean saveScore(ScoreRequestDTO scoreRequest) {
        // Buscar al usuario por nombre
        Users user = userRepository.findByName(scoreRequest.getUsername());
        if (user == null) {
            return false;  // Si el usuario no existe, retornar false
        }


        // Obtenemos los scores ordenados del usuario, por id
        List<Scores> scoresList = scoreRepository.findByUserOrderById(user);

        // Si es mayor a 10 eliminamos el mas antiguo
        if (scoresList.size() >= 5) {
            Scores oldestScore = scoresList.get(0);
            scoreRepository.delete(oldestScore);
        }

        // Crear un nuevo objeto de puntuación
        Scores score = new Scores();
        score.setUser(user);
        score.setScore(scoreRequest.getScore());
        score.setMaxScore(scoreRequest.getMaxScore());

        // Guardar la puntuación en la base de datos
        scoreRepository.save(score);
        return true;
    }

    // Obtiene el máximo score de un usuario dado su nombre
    public Optional<Integer> getMaxScoreByUsername(String username) {
        Users user = userRepository.findByName(username);
        if (user == null) {
            return Optional.empty();  // Si el usuario no existe
        }
        return scoreRepository.findMaxScoreByUser(user);
    }

    // Elimina un score por su ID, si existe
    @Override
    public boolean deleteScoreById(String id) {
        if (scoreRepository.existsById(id)) {
            scoreRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Obtiene los 10 mejores scores ordenados descendientemente y los convierte en DTO para la respuesta
    @Override
    public List<ScoreTop10ResponseDTO> getTop10Scores() {
        List<Scores> topScores = scoreRepository.findTop10ByOrderByScoreDesc();
        return topScores.stream().map(score -> {
            ScoreTop10ResponseDTO dto = new ScoreTop10ResponseDTO();
            dto.setUsername(score.getUser().getName());
            dto.setMaxScore(score.getMaxScore());
            return dto;
        }).toList();
    }
}
