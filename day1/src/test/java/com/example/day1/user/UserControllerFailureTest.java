package com.example.day1.user;

import com.example.day1.global.ErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerFailureTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Failure with get user by id=2 => 404 User not found")
    void case01() {
        // Arrange
        userRepository.deleteAll();
        // Act
        ResponseEntity<ErrorResponse> result = restTemplate.getForEntity("/user/2", ErrorResponse.class);
        // Assert
        assertEquals(404, result.getStatusCode().value());
        assertEquals("User id =2 not found", result.getBody().getMessage());
    }

    @Test
    @DisplayName("Failure with get user by id=fff => Error input type mismatch")
    void case02() {
        // Act
        ResponseEntity<ErrorResponse> result = restTemplate.getForEntity("/user/fff", ErrorResponse.class);
        // Assert
        assertEquals(400, result.getStatusCode().value());
        assertEquals("Failed to convert value of type 'java.lang.String' to required type 'int'; For input string: \"fff\"", result.getBody().getMessage());
    }
}