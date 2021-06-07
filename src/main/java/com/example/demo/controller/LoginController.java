package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody UserDTO.LoginDTO loginDTO) throws  Exception {
        return userService.getUserEntityByUserNameAndPasswordOrThrow(loginDTO.getUserName(), loginDTO.getPassword());
    }
}
