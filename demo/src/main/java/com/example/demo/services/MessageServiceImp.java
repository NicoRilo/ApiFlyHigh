package com.example.demo.services;

import com.example.demo.models.Message;
import com.example.demo.models.Users;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService{
    private final MessageRepository messageRepository;
    private UserRepository userRepository;

    @Autowired
    public MessageServiceImp(MessageRepository messageRepository, UserRepository userRepository ) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    // Obtenemos todos los messages que se han enviado
    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAllByOrderByTimestampDesc();
    }

    // Añadimos nuevos messages
    @Override
    public boolean saveMessage(String username, String message) {
        Users user = userRepository.findByName(username);
        if (user == null) {
            return false;
        }
        Message chatMessage = new Message();
        chatMessage.setUsername(username);
        chatMessage.setMessage(message);
        chatMessage.setTimestamp(LocalDateTime.now());
        messageRepository.save(chatMessage);
        return true;
    }

    // Eliminamos messages segun su id
    @Override
    public boolean deleteMessageById(Integer messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return true;
        }
        return false;
    }
}
