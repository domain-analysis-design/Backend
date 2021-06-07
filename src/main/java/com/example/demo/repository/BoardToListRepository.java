package com.example.demo.repository;

import com.example.demo.entity.BoardToList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardToListRepository extends JpaRepository<BoardToList, Long> {
    void deleteBoardToListByBoardIdAndListId(Long boardId, Long listId);
}
