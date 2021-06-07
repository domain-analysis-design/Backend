package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = {"boardToList", "listToCard"})
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long createdBy;
    private Long updatedBy;

    @OneToOne
    private BoardToList boardToList;

    @OneToOne
    private ListToCard listToCard;
}
