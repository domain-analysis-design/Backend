package com.example.demo.service.user;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.BoardToUser;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.BoardToUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardToUserRepository boardToUserRepository;

    @Transactional
    public boolean checkBoardOwnerPermission(Long boardId, Long userId){
        try{
            Board board = this.getBoardEntityByIdOrThrow(boardId);
            return board.getCreatedBy().equals(userId);
        }
        catch (Exception e){
            return false;
        }
    }

    @Transactional
    public Board createBoardEntity(BoardDTO.createBoardDTO createBoardDTO) {
        Long userId = createBoardDTO.getUserId();
        Board board = new Board();
        board.setName(createBoardDTO.getName());
        board.setCreatedBy(userId);
        board.setUpdatedBy(userId);
        Board newBoard = boardRepository.save(board);

        BoardToUser boardToUser = new BoardToUser();

        boardToUser.setBoardId(newBoard.getId());
        boardToUser.setUserId(userId);
        boardToUser.setCreatedBy(userId);
        boardToUser.setUpdatedBy(userId);

        boardToUserRepository.save(boardToUser);

        return newBoard;
    }

    @Transactional
    public Board getBoardEntityByIdOrThrow(Long boardId) throws Exception{
        Optional<Board> board = boardRepository.findById(boardId);
        return board.orElseThrow(() -> new Exception("board Entity doesn't exist"));
    }

    @Transactional
    public boolean updateBoardEntity(BoardDTO.updateBoardDTO updateBoardDTO) throws Exception{
        try{
            Board board = this.getBoardEntityByIdOrThrow(updateBoardDTO.getId());
            board.setName(updateBoardDTO.getName());
            boardRepository.save(board);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean deleteBoardEntity(BoardDTO.deleteBoardDTO deleteBoardDTO) {
        if (!this.checkBoardOwnerPermission(deleteBoardDTO.getBoardId(), deleteBoardDTO.getUserId())){
            return false;
        }
        boardRepository.deleteById(deleteBoardDTO.getBoardId());
        return true;
    }

    @Transactional
    public List<Board> getBoardEntitiesByUserId (Long userId) {
        List<Board> boardEntities = boardRepository.findBoardsByBoardToUser_UserId(userId);

        return boardEntities.isEmpty() ? null : boardEntities;
    }

    @Transactional
    public List<Board> getBoardEntitiesByBoardName (String keyword) {
        return boardRepository.findBoardsByNameLike(keyword);
    }

    @Transactional
    public boolean upsertBoardToUserEntity(BoardDTO.inviteUserDTO inviteUserDTO) {
        try{
            BoardToUser boardToUser = new BoardToUser();
            boardToUser.setBoardId(inviteUserDTO.getBoardId());
            boardToUser.setUserId(inviteUserDTO.getInvitedUserId());
            boardToUser.setCreatedBy(inviteUserDTO.getInvitingUserId());
            boardToUser.setUpdatedBy(inviteUserDTO.getInvitingUserId());
            boardToUserRepository.save(boardToUser);

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Transactional
    public boolean deleteBoardToUserEntity(BoardDTO.kickUserDTO kickUserDTO){
        if (!this.checkBoardOwnerPermission(kickUserDTO.getBoardId(), kickUserDTO.getKickingUserId())){
            return false;
        }
        return boardToUserRepository.deleteBoardToUserByUser_UserNameAndBoardId(kickUserDTO.getKickedUserName(), kickUserDTO.getBoardId());
    }
}
