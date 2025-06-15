package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity // Entidad JPA
@Table(name = "messages")
@Data // Genera getters, setters, etc.
public class Message {
    @Id // Idica que es la calve primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}

