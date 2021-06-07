package com.example.demo.repository;

import com.example.demo.entity.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<List, Long> {
    java.util.List<List> findListsByBoardToListListId(Long boardId);
}
