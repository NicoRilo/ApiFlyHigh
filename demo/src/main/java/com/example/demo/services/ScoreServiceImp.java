package com.example.demo.services;

import com.example.demo.dtos.requests.ScoreRequestDTO;
import com.example.demo.dtos.responses.ScoreResponseDTO;
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
    @Override
    public List<Scores> getAllScores() {
        return scoreRepository.findAll();
    }

    @Override
    public ScoreResponseDTO getScoresById(String id) {
        Scores score = scoreRepository.findById(id).orElse(null);
        return scoreMapper.toDto(score);
    }

    @Override
    public boolean saveScore(ScoreRequestDTO scoreRequest) {
        // Buscar al usuario por nombre
        Users user = userRepository.findByName(scoreRequest.getUsername());
        if (user == null) {
            return false;  // Si el usuario no existe, retornar false
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

    public Optional<Integer> getMaxScoreByUsername(String username) {
        Users user = userRepository.findByName(username);
        if (user == null) {
            return Optional.empty();  // Si el usuario no existe
        }
        return scoreRepository.findMaxScoreByUser(user);
    }

    @Override
    public boolean deleteScoreById(String id) {
        if (scoreRepository.existsById(id)) {
            scoreRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
