package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "scores")
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_user", nullable = false)
    private Users user;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

}
