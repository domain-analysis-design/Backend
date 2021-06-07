package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class UserDTO {

    @Data
    @Builder
    public static class LoginDTO {
        private String userName;
        private String password;
    }
}
