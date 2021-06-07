package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString(exclude = {"boardToUser", "boardToList", "boardToCard"})
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long createdBy;

    private Long updatedBy;

    @OneToOne
    private BoardToUser boardToUser;

    @OneToOne
    private BoardToList boardToList;

    @OneToOne
    private BoardToCard boardToCard;
}
