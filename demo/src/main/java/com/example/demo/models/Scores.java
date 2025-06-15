package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// Representa la entidad Scores.
// Cada valor corresponde a un registro en la tabla "scores" de la base de datos.

@Entity // Entidad JPA
@Data // Genera getters, setters, etc.
@Table(name = "scores")
public class Scores {

    @Id // Idica que es la calve primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_user", nullable = false)
    private Users user;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "max_score", nullable = false)
    private Integer maxScore;
}
