package com.example.demo.repository;

import com.example.demo.entity.ListToCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListToCardRepository extends JpaRepository<ListToCard, Long> {
    void deleteListToCardByCardId(Long cardId);
}
