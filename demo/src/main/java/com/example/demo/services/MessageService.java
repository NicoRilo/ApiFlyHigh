package com.example.demo.services;

import com.example.demo.models.Message;

import java.util.List;

public interface MessageService {
    // Devuelve una lista con todos los mensajes
    List<Message> getAllMessages();
    // Guarda o añade un nuevo mensaje
    boolean saveMessage(String username, String message);
    // Elimina un message a traves de su id
    boolean deleteMessageById(Integer messageId);
}
