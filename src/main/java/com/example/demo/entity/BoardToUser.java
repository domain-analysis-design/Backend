package com.example.demo.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Data
@Entity
public class BoardToUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long boardId;
    private Long userId;
    private Long createdBy;
    private Long updatedBy;

    // Board 1 : 1 BoardToUser
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "boardToUser")
    private Board board;

    // User 1 : 1 BoardToUser
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "boardToUser")
    private User user;}
