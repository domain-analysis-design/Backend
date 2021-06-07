package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = {"boardToCard", "listToCard"})
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long createdBy;
    private Long updatedBy;

    @OneToOne
    private BoardToCard boardToCard;

    @OneToOne
    private ListToCard listToCard;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    private List<Item> itemList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    private List<Comment> commentList;
}
