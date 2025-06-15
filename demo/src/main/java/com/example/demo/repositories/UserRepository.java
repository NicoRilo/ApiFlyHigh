package com.example.demo.repositories;

import com.example.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository para gestionar los datos de la entidad Users

public interface UserRepository extends JpaRepository<Users, Integer> {

    // Obtenemos el usuario segun su nombre
    Users findByName(String name);
}
