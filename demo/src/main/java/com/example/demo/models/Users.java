package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// Representa la entidad Users.
// Cada valor corresponde a un registro en la tabla "users" de la base de datos.

@Entity // Entidad JPA
@Data // Genera getters, setters, etc.
@Table(name = "users")
public class Users {

    @Id // Idica que es la calve primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "bibliography")
    private String bibliography;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scores> scores;

    @Column(name = "role", nullable = false)
    private String role;
}
