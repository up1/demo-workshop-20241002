package com.example.day1.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/{id}")
    public UserResponse getById(@PathVariable int id) {
        if(id == 2) {
            throw new UserNotFoundException("User id =" + id + " not found");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(id);
        userResponse.setFname("Somkiat");
        userResponse.setLname("Pui");
        return userResponse;
    }

}
