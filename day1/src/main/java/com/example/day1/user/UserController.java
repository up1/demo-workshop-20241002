package com.example.day1.user;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public UserResponse getById(@PathVariable int id) {
        return userService.get(id);
    }

    @PostMapping("/user")
    public UserResponse createNewUser(
            @Valid  @RequestBody CreateUserRequest createUserRequest) {
        System.out.println(createUserRequest);
        // 1. Validate input
        // 2. Call create() in User service
        UserResponse userResponse = userService.create(createUserRequest);
        return userResponse;
    }

}
