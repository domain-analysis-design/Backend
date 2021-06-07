package com.example.demo.service.user;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUserEntity(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User getUserEntityByUserNameAndPasswordOrThrow(String userName, String password) throws Exception{
        Optional<User> user = Optional.of(userRepository.findUserByUserNameAndPassword(userName, password));
        return user.orElseThrow(() -> new Exception("wrong Id Or Password"));
    }

    @Transactional
    public List<User> getUserEntityByUserName (String userName) {
        return userRepository.findUsersByUserNameContains(userName);
    }
}
