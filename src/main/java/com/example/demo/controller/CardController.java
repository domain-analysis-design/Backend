package com.example.demo.controller;

import com.example.demo.dto.CardDTO;
import com.example.demo.entity.Card;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Item;
import com.example.demo.service.user.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/card/getCard/{listId}")
    public List<Card> getCards(@PathVariable(name = "listId") Long listId) {
        return cardService.getCards(listId);
    }

    @PostMapping("/createCard")
    public Card postCard(@RequestBody CardDTO.createCardDTO createCardDTO) {
        return cardService.createCardEntity(createCardDTO);
    }

    @GetMapping("/card/comment/{id}")
    public List<Comment> getComments(@PathVariable(name = "id") Long id) {
        return cardService.getCommentEntitiesByCardId(id);
    }

    @GetMapping("/card/item/{id}")
    public List<Item> getItems(@PathVariable(name = "id") Long id){
        return cardService.getItemEntitiesByCardId(id);
    }

    @PostMapping("/card/createComment")
    public Comment postComment(@RequestBody CardDTO.createCommentDTO createCommentDTO){
        return cardService.createCommentEntity(createCommentDTO);
    }

    @PostMapping("/card/createItem")
    public Item postItem(@RequestBody CardDTO.createItemDTO createItemDTO) {
        return cardService.createItemEntity(createItemDTO);
    }

    @PatchMapping("/card/updateBoardCard")
    public boolean updateBoardCard(@RequestBody CardDTO.updateBoardCardDTO updateBoardCardDTO) {
        return cardService.upsertBoardToCardEntity(updateBoardCardDTO);
    }

    @PatchMapping("/card/updateListCard")
    public boolean updateListCard(@RequestBody CardDTO.updateListCardDTO updateListCardDTO) {
        return cardService.upsertListToCardEntity(updateListCardDTO);
    }

    @PatchMapping("/updateCard")
    public boolean updateCard(@RequestBody CardDTO.updateCardDTO updateCardDTO) {
        return cardService.updateCardEntity(updateCardDTO);
    }

    @PatchMapping("/card/updateComment")
    public boolean updateComment(@RequestBody CardDTO.updateCommentDTO updateCommentDTO){
        return cardService.updateCommentEntity(updateCommentDTO);
    }

    @PatchMapping("/card/updateItem")
    public boolean updateItem(@RequestBody CardDTO.updateItemDTO updateItemDTO){
        return cardService.updateItemEntity(updateItemDTO);
    }

    @DeleteMapping("/{cardId}")
    public boolean deleteCard(@PathVariable Long cardId) {
        return cardService.deleteCardEntity(cardId);
    }

    @DeleteMapping("/card/deleteComment/{id}")
    public boolean deleteComment(@PathVariable Long id) {
        return cardService.deleteCommentEntity(id);
    }

    @DeleteMapping("/card/deleteItem/{id}")
    public boolean deleteItem(@PathVariable Long id) {
        return cardService.deleteItemEntity(id);
    }
}
