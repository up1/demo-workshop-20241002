package com.example.day1.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Success case")
    void case01() {
        // Arrange
        MyUser dummy = new MyUser();
        dummy.setId(1L);
        dummy.setFirstName("Somkiat");
        dummy.setLastName("Pui");
        userRepository.saveAndFlush(dummy);
        // Act
        UserResponse result = userService.get(1);
        // Assert
        assertEquals(1, result.getId());
        assertEquals("Somkiat", result.getFname());
        assertEquals("Pui", result.getLname());
    }

    @Test
    @DisplayName("User not found case")
    void case02() {
        Exception exception = assertThrows(UserNotFoundException.class, ()
                -> userService.get(2));
        assertEquals("User id =2 not found", exception.getMessage());
    }
}