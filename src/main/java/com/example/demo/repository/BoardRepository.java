package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findBoardsByBoardToUser_UserId(Long userId);
    List<Board> findBoardsByNameLike(String keyword);

}
