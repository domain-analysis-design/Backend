package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.user.UserService;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerIn(@RequestBody User user) {
        return userService.createUserEntity(user);
    }

}
