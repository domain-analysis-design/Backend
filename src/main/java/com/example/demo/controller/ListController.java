package com.example.demo.controller;

import com.example.demo.dto.ListDTO;
import com.example.demo.entity.List;
import com.example.demo.service.user.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
public class ListController {
    @Autowired
    private ListService listService;

    @GetMapping("/list/getList/{boardId}")
    public java.util.List<List> getLists(@PathVariable(name = "boardId") Long boardId) {
        return listService.getLists(boardId);
    }

    @PostMapping("/createList")
    public List postList(@RequestBody ListDTO.createListDTO createListDTO){
        return listService.createListEntity(createListDTO);
    }

    @PatchMapping("/updateList")
    public boolean updateListName(@RequestBody ListDTO.updateListDTO updateListDTO){
        return listService.updateListEntity(updateListDTO);
    }

    @DeleteMapping("/deleteList")
    public boolean deleteList(@RequestBody ListDTO.deleteListDTO deleteListDTO){
        return listService.deleteListEntity(deleteListDTO);
    }
}
