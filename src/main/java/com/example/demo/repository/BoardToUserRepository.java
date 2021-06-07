package com.example.demo.repository;

import com.example.demo.entity.BoardToUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardToUserRepository extends JpaRepository<BoardToUser, Long> {
    boolean deleteBoardToUserByUser_UserNameAndBoardId(String userName, Long boardId);
}
