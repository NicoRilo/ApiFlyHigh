package com.example.demo.mappers;

import com.example.demo.dtos.responses.UserResponseDTO;
import com.example.demo.models.Users;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserResponseDTO toDto(Users user){
        if (user == null){
            return null;
        }
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setCoins(user.getCoins());
        dto.setRole(user.getRole());

        if (user.getScores() != null) {
            List<UserResponseDTO.ScoreResposneDTO> scoreDTOs = user.getScores().stream().map(score -> {
                UserResponseDTO.ScoreResposneDTO scoreDTO = new UserResponseDTO.ScoreResposneDTO();
                scoreDTO.setId(score.getId());
                scoreDTO.setScore(score.getScore());
                scoreDTO.setMaxScore(score.getMaxScore());
                return scoreDTO;
            }).collect(Collectors.toList());
            dto.setScores(scoreDTOs);
        }

        return dto;

    }
}
