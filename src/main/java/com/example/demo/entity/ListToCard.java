package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ListToCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long listId;
    private Long cardId;
    private Long createdBy;
    private Long updatedBy;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "listToCard")
    private List list;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "listToCard")
    private Card card;
}
