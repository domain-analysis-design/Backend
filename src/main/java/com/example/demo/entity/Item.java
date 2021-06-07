package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@ToString(exclude = {"card"})
@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cardId;
    private String contents;
    private String state;
    private Long createdBy;
    private Long updatedBy;

    @ManyToOne
    private Card card;
}
