package com.example.demo.servicesMock;

import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Users;
import com.example.demo.repositories.ScoreRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImp userService;

    @Test
    public void testInicioSesionValidateSuccess() {
        Users mockUser = new Users();
        mockUser.setName("user");
        mockUser.setPassword("1234");

        Mockito.when(userRepository.findByName("user")).thenReturn(mockUser);

        boolean result = userService.inicioSesionValidate("user", "1234");

        assertTrue(result);
    }

    @Test
    public void testInicioSesionValidateFailed() {
        Users mockUser = new Users();
        mockUser.setName("user");
        mockUser.setPassword("1234");

        Mockito.when(userRepository.findByName("user")).thenReturn(mockUser);

        boolean result = userService.inicioSesionValidate("user", "123");

        assertFalse(result);
    }

    @Test
    public void testRegisterUserSuccess() {
        Mockito.when(userRepository.findByName("newuser")).thenReturn(null);

        Users savedUser = new Users();
        savedUser.setName("newuser");
        savedUser.setPassword("pass");

        Mockito.when(userRepository.save(Mockito.any(Users.class))).thenReturn(savedUser);

        boolean result = userService.registerUser("newuser", "pass");

        assertTrue(result);
    }

    @Test
    public void testInicioSesionValidateUserNotFound() {
        Mockito.when(userRepository.findByName("user")).thenReturn(null);

        boolean result = userService.inicioSesionValidate("user", "1234");

        assertFalse(result);
    }

}

