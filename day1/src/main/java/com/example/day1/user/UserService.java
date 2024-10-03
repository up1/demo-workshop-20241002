package com.example.day1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse get(int id) {
        Optional<MyUser> result =  userRepository.findById((long) id);
        if (result.isEmpty()) {
            throw new UserNotFoundException("User id =" + id + " not found");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(id);
        userResponse.setFname(result.get().getFirstName());
        userResponse.setLname(result.get().getLastName());
        return userResponse;
    }

    public UserResponse create(CreateUserRequest request) {
        MyUser newUser = new MyUser();
        newUser.setFirstName(request.getFname());
        newUser.setLastName(request.getLname());
        newUser.setAge(request.getAge());
        newUser = userRepository.saveAndFlush(newUser);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(Math.toIntExact(newUser.getId()));
        userResponse.setFname(newUser.getFirstName());
        userResponse.setLname(newUser.getLastName());
        return userResponse;
    }
}
