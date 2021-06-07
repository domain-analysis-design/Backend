package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.user.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/create")
    public Board postBoard(@RequestBody BoardDTO.createBoardDTO createBoardDTO) {
        return boardService.createBoardEntity(createBoardDTO);
    }

    @GetMapping("/userId/{userId}")
    public List<Board> getBoardsByUser(@PathVariable(name = "userId") Long userId) {
        return boardService.getBoardEntitiesByUserId(userId);
    }

    @GetMapping("/search/{keyword}")
    public List<Board> getBoardsByKeyword(@PathVariable(name = "keyword") String keyword) {
        return boardService.getBoardEntitiesByBoardName(keyword);
    }

    @PatchMapping("/update")
    public boolean updateBoardName(@RequestBody BoardDTO.updateBoardDTO updateBoardDTO) throws Exception{
        return boardService.updateBoardEntity(updateBoardDTO);
    }

    @DeleteMapping("/delete")
    public boolean deleteBoard(BoardDTO.deleteBoardDTO deleteBoardDTO){
        return boardService.deleteBoardEntity(deleteBoardDTO);
    }

    @DeleteMapping("/searchUser/delete")
    public boolean kickUser(@RequestBody BoardDTO.kickUserDTO kickUserDTO){
        return boardService.deleteBoardToUserEntity(kickUserDTO);
    }
}
