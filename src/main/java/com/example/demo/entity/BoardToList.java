package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BoardToList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long boardId;
    private Long listId;
    private Long createdBy;
    private Long updatedBy;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "boardToUser")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "boardToUser")
    private List list;
}
