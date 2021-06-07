package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BoardToCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sourceBoardId;
    private Long targetBoardId;
    private Long cardId;
    protected String state;
    private Long createdBy;
    private Long updatedBy;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "boardToCard")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "boardToCard")
    private Card card;
}
