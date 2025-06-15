package com.example.demo.servicesH2;

import com.example.demo.models.Users;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServiceImp;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UserServiceImpIntegrationTest {

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegisterAndLoginUser() {
        boolean registered = userService.registerUser("user", "1234");
        assertTrue(registered);

        boolean login = userService.inicioSesionValidate("user", "1234");
        assertTrue(login);
    }

    @Test
    public void testUserExistInBBDD() {
        String userExistente = "Nico";

        Users user = userRepository.findByName(userExistente);

        assertNotNull(user, "El usuario debería existir en la base de datos");
        assertEquals(userExistente, user.getName());
    }

}

