package com.example.day1.user;

import com.example.day1.post.PostGateway;
import com.example.day1.post.PostResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;
    private final PostGateway postGateway;

    public UserController(UserService userService, PostGateway postGateway) {
        this.userService = userService;
        this.postGateway = postGateway;
    }

    @GetMapping("/mypost/{id}")
    public UserResponse workWithPost(@PathVariable int id){
        Optional<PostResponse> result = postGateway.getById(id);
        if(result.isEmpty()) {
            // Empty
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(result.get().getId());
        userResponse.setFname(result.get().getBody().toUpperCase());
        return userResponse;
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
