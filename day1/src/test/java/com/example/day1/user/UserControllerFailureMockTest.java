package com.example.day1.user;

import com.example.day1.global.ErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerFailureMockTest {

    @MockBean
    private UserService userService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("With Mock, Failure with get user by id=2 => 404 User not found")
    void case01() {
        // Arrange
        when(userService.get(2))
                .thenThrow(new UserNotFoundException("User id =2 not found"));
        // Act
        ResponseEntity<ErrorResponse> result = restTemplate.getForEntity("/user/2", ErrorResponse.class);
        // Assert
        assertEquals(404, result.getStatusCode().value());
        assertEquals("User id =2 not found", result.getBody().getMessage());
    }
}