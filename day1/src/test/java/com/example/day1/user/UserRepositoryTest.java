package com.example.day1.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Found user id = 1")
    public void case01(){
        // Arrange
        MyUser dummy = new MyUser();
        dummy.setId(1L);
        dummy.setFirstName("Somkiat");
        dummy.setLastName("Pui");
        userRepository.saveAndFlush(dummy);
        // Act
        Optional<MyUser> result =  userRepository.findById(1L);
        // Assert
        assertEquals(1, result.get().getId());
        assertEquals("Somkiat", result.get().getFirstName());
    }

    @Test
    @DisplayName("Success with create a new user")
    public void case02(){
        // Arrange
        MyUser dummy = new MyUser();
        dummy.setFirstName("Somkiat");
        dummy.setLastName("Pui");
        // Act
        dummy = userRepository.saveAndFlush(dummy);

        // Assert
        assertNotNull(dummy.getId());
    }

    @Test
    @DisplayName("Search by first name")
    public void case03(){
        // Arrange
        MyUser dummy = new MyUser();
        dummy.setFirstName("Somkiat");
        dummy.setLastName("Pui");
        userRepository.saveAndFlush(dummy);
        // Act
        Optional<MyUser> result =  userRepository.findByFirstName("Somkiat");
        // Assert
        assertEquals("Somkiat", result.get().getFirstName());
    }



}