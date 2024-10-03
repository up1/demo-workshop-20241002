package com.example.day1.user;

import com.example.day1.hello.HelloResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerSuccessTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Success with get user by id=1")
    void getById() {
        // Arrange
        MyUser dummy = new MyUser();
        dummy.setId(1L);
        dummy.setFirstName("Somkiat");
        dummy.setLastName("Pui");
        userRepository.saveAndFlush(dummy);
        // Act
        UserResponse result = restTemplate.getForObject("/user/1", UserResponse.class);
        // Assert
        assertEquals(1, result.getId());
        assertEquals("Somkiat", result.getFname());
        assertEquals("Pui", result.getLname());
    }
}