package com.example.demo.repositories;

import com.example.demo.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repository para gestionar los datos de la entidad Message
public interface MessageRepository extends JpaRepository<Message, Integer> {
    // Obtener los mensajes en orden Ascendente segun el tiempo
    List<Message> findAllByOrderByTimestampDesc();
}

