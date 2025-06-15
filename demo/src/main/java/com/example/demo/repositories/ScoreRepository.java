package com.example.demo.repositories;

import com.example.demo.models.Scores;
import com.example.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// Repository para gestionar los datos de la entidad Scores

public interface ScoreRepository extends JpaRepository<Scores, String> {

    // Obtener el usuario con mayor MaxDcore
    @Query("SELECT MAX(s.maxScore) FROM Scores s WHERE s.user = :user")
    Optional<Integer> findMaxScoreByUser(Users user);

    // Obtener los usuarios ordenados por id de un usuario
    List<Scores> findByUserOrderById(Users user);

    // Obtenemos los diez mejores puntuaciones en orden descendente
    List<Scores> findTop10ByOrderByScoreDesc();
}
