package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.user.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/board")
public class InviteController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/searchUser/invite")
    public boolean inviteUser(@RequestBody BoardDTO.inviteUserDTO inviteUserDTO) {
        return boardService.upsertBoardToUserEntity(inviteUserDTO);
    }
}
