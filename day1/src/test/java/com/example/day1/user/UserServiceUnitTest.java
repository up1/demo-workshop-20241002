package com.example.day1.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
    private UserRepository mockRepository;

    @Mock
    private Demo2 demo2;

    @Test
    void get() {
        // Arrange
        MyUser u1 = new MyUser();
        u1.setId(1L);
        u1.setFirstName("Mock fname");
        when(mockRepository.findById(1L))
                .thenReturn(Optional.of(u1));
        UserService service = new UserService(mockRepository, demo2);
        // Act
        UserResponse result = service.get(1);
        // Assert
        assertEquals(1, result.getId());
        assertEquals("Mock fname", result.getFname());
    }
}