package com.example.demo.repositories;

import com.example.demo.models.Scores;
import com.example.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Scores, String> {
    @Query("SELECT MAX(s.maxScore) FROM Scores s WHERE s.user = :user")
    Optional<Integer> findMaxScoreByUser(Users user);

}
