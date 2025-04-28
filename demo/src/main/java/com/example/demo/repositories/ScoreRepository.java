package com.example.demo.repositories;

import com.example.demo.models.Scores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Scores, String> {
}
