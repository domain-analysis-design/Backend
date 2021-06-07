package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/searchUser/{keyword}")
    public List<User> getUserByKeyword(@PathVariable(name = "keyword") String keyword) {
        return userService.getUserEntityByUserName(keyword);
    }
}