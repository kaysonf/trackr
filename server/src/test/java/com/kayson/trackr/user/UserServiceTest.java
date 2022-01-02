package com.kayson.trackr.user;

import com.kayson.trackr.exception.AlreadyExistsException;
import com.kayson.trackr.user.dto.CreateUserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void whenCreateUserWithExistingHandleOrEmail_shouldThrowException() {
        when(userRepository.findByHandle("userThatExists")).thenReturn(Optional.of(new User("userThatExists", "userThatExists@a.com", "userThatExistspass")));

        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setHandle("userThatExists");
        createUserDTO.setEmail("userThatExists@a.com");
        createUserDTO.setPassword("my_password123");

        Exception exceptionFromExistingHandle = assertThrows(AlreadyExistsException.class, () -> {
            userService.createNewUser(createUserDTO);
        });

        assertNotNull(exceptionFromExistingHandle);

        when(userRepository.findByEmail("userThatExists@a.com")).thenReturn(Optional.of(new User("userThatExists", "userThatExists@a.com", "userThatExistspass")));

        CreateUserDTO createUserDTO2 = new CreateUserDTO();
        createUserDTO2.setHandle("non_existent_handle");
        createUserDTO2.setEmail("userThatExists@a.com");
        createUserDTO2.setPassword("my_password123");

        Exception exceptionFromExistingEmail = assertThrows(AlreadyExistsException.class, () -> {
            userService.createNewUser(createUserDTO2);
        });

        assertNotNull(exceptionFromExistingEmail);
    }
}
