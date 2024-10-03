package com.example.day1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse get(int id) {
        Optional<MyUser> result =  userRepository.findById(1L);
        if (result.isEmpty()) {
            throw new UserNotFoundException("User id =" + id + " not found");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(id);
        userResponse.setFname(result.get().getFirstName());
        userResponse.setLname(result.get().getLastName());
        return userResponse;
    }
}
