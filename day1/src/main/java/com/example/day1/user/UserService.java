package com.example.day1.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Demo2 demo2;

    public UserService(UserRepository userRepository, Demo2 demo2) {
        this.userRepository = userRepository;
        this.demo2 = demo2;
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

    @Transactional
    public UserResponse create(CreateUserRequest request) {
        MyUser newUser = new MyUser();
        newUser.setFirstName(request.getFname());
        newUser.setLastName(request.getLname());
        newUser.setAge(request.getAge());
        newUser = userRepository.saveAndFlush(newUser);
        userRepository.deleteAll();
        demo2.step2();

        UserResponse userResponse = new UserResponse();
        userResponse.setId(Math.toIntExact(newUser.getId()));
        userResponse.setFname(newUser.getFirstName());
        userResponse.setLname(newUser.getLastName());
        return userResponse;
    }


}

@Service
class Demo2 {

    private final UserRepository userRepository;

    public Demo2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void step2() {
        userRepository.deleteAll();
    }
}
