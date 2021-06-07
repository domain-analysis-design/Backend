package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString(exclude = {"boardToUser"})
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private String displayName;

    private String email;

    @OneToOne
    private BoardToUser boardToUser;
}