package com.example.demo.service.user;

import com.example.demo.dto.CardDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ListToCardRepository listToCardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BoardToCardRepository boardToCardRepository;

    @Transactional
    public Card getCardEntityByIdOrThrow(Long cardId) throws Exception {
        Optional<Card> card = cardRepository.findById(cardId);
        return card.orElseThrow(() -> new Exception("card Entity doesn't exist"));
    }

    @Transactional
    public Comment getCommentEntityByIdOrThrow(Long commentId) throws Exception {
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment.orElseThrow(() -> new Exception("comment Entity doesn't exist"));
    }

    @Transactional
    public Item getItemEntityByIdOrThrow(Long itemId) throws Exception {
        Optional<Item> item = itemRepository.findById(itemId);
        return item.orElseThrow(() -> new Exception("item Entity doesn't exist"));
    }

    @Transactional
    public Card createCardEntity(CardDTO.createCardDTO createCardDTO) {
        Long userId = createCardDTO.getUserId();
        Card card = new Card();
        card.setName(createCardDTO.getName());
        card.setDescription(card.getDescription());
        card.setCreatedBy(userId);
        card.setUpdatedBy(userId);

        Card newCard = cardRepository.save(card);

        ListToCard listToCard = new ListToCard();

        listToCard.setCardId(newCard.getId());
        listToCard.setListId(createCardDTO.getListId());
        listToCard.setCreatedBy(userId);
        listToCard.setUpdatedBy(userId);

        listToCardRepository.save(listToCard);

        return newCard;
    }

    @Transactional
    public boolean updateCardEntity(CardDTO.updateCardDTO updateCardDTO) {
        Long userId = updateCardDTO.getUserId();
        try {
            Card card = this.getCardEntityByIdOrThrow(updateCardDTO.getCardId());
            card.setName(updateCardDTO.getCardName());
            card.setDescription(updateCardDTO.getDescription());
            card.setUpdatedBy(userId);
            card.setCreatedBy(userId);

            cardRepository.save(card);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public Comment createCommentEntity(CardDTO.createCommentDTO createCommentDTO) {
        Long userId = createCommentDTO.getUserId();
        Comment comment = new Comment();
        comment.setCardId(createCommentDTO.getCardId());
        comment.setMessage(createCommentDTO.getMessage());
        comment.setCreatedBy(userId);
        comment.setUpdatedBy(userId);

        commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public boolean updateCommentEntity(CardDTO.updateCommentDTO updateCommentDTO) {
        Long userId = updateCommentDTO.getUserId();
        try {
            Comment comment = this.getCommentEntityByIdOrThrow(updateCommentDTO.getCommentId());
            comment.setMessage(updateCommentDTO.getMessage());
            comment.setUpdatedBy(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateItemEntity(CardDTO.updateItemDTO updateItemDTO) {
        Long userId = updateItemDTO.getUserId();
        try {
            Item item = this.getItemEntityByIdOrThrow(updateItemDTO.getItemId());
            item.setContents(updateItemDTO.getContents());
            item.setState(updateItemDTO.getState());
            item.setUpdatedBy(userId);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Transactional
    public Item createItemEntity(CardDTO.createItemDTO createItemDTO) {
        Long userId = createItemDTO.getUserId();
        Item item = new Item();
        item.setCardId(createItemDTO.getCardId());
        item.setContents(createItemDTO.getContents());
        item.setState(createItemDTO.getState());
        item.setCreatedBy(userId);
        item.setUpdatedBy(userId);

        itemRepository.save(item);

        return item;
    }

    @Transactional
    public boolean upsertBoardToCardEntity(CardDTO.updateBoardCardDTO updateBoardCardDTO) {
        try {
            BoardToCard boardToCard = new BoardToCard();
            boardToCard.setSourceBoardId(updateBoardCardDTO.getSourceBoardId());
            boardToCard.setTargetBoardId(updateBoardCardDTO.getTargetBoardId());
            boardToCard.setCardId(updateBoardCardDTO.getCardId());
            boardToCard.setState(updateBoardCardDTO.getStatus());
            boardToCard.setUpdatedBy(updateBoardCardDTO.getUserId());
            boardToCardRepository.save(boardToCard);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Transactional
    public boolean upsertListToCardEntity(CardDTO.updateListCardDTO updateListCardDTO) {
        try{
            ListToCard listToCard = new ListToCard();
            listToCard.setListId(updateListCardDTO.getListId());
            listToCard.setCardId(updateListCardDTO.getCardId());
            listToCard.setUpdatedBy(updateListCardDTO.getUserId());

            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Transactional
    public List<Comment> getCommentEntitiesByCardId(Long cardId){
        return commentRepository.findCommentsByCardId(cardId);
    }

    @Transactional
    public List<Item> getItemEntitiesByCardId(Long cardId){
        return itemRepository.findItemsByCardId(cardId);
    }

    @Transactional
    public boolean deleteCardEntity(Long cardId) {
        try {
            listToCardRepository.deleteListToCardByCardId(cardId);
            try{
                cardRepository.deleteById(cardId);
            } catch (Exception e) {
                return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Transactional
    public boolean deleteCommentEntity(Long commentId) {
        try{
            commentRepository.deleteById(commentId);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Transactional
    public boolean deleteItemEntity(Long itemId) {
        try{
            itemRepository.deleteById(itemId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public List<Card> getCards(Long listId) {
        return cardRepository.findCardsByListToCardListId(listId);
    }
}
