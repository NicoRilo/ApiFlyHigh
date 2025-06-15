package com.example.demo.controllers;

import com.example.demo.dtos.requests.MessageRequestDTO;
import com.example.demo.models.Message;
import com.example.demo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define esta clase como un controlador REST para manejar solicitudes HTTP
@RequestMapping("/proyecto/chat") // Ruta para los endpoints
public class MessageRestController {
    private final MessageService messageService;

    @Autowired
    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Endpoint GET para obtener los mensajes
    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    // Endpoint POST para crear o añadir mensajes para el usuario correspondiente
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequestDTO request) {
        boolean success = messageService.saveMessage(request.getUsername(), request.getMessage());
        if (!success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe.");
        }
        return ResponseEntity.ok("Mensaje guardado con exito.");
    }

    // Endpoint DELETE para eliminar un mensaje segun su id
    @DeleteMapping("/message/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Integer id) {
        boolean deleted = messageService.deleteMessageById(id);
        if (deleted) {
            return ResponseEntity.ok().body("Mensaje eliminado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mensaje no encontrado.");
        }
    }
}
