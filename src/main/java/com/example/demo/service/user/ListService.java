package com.example.demo.service.user;

import com.example.demo.dto.ListDTO;
import com.example.demo.entity.BoardToList;
import com.example.demo.entity.List;
import com.example.demo.repository.BoardToListRepository;
import com.example.demo.repository.ListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ListService {
    @Autowired
    private ListRepository listRepository;

    @Autowired
    private BoardToListRepository boardToListRepository;

    @Transactional
    public java.util.List<List> getLists(Long boardId) {
        return listRepository.findListsByBoardToListListId(boardId);
    }

    @Transactional
    public List getListEntityByIdOrThrow(Long listId) throws Exception{
        Optional<List> list = listRepository.findById(listId);
        return list.orElseThrow(() -> new Exception("list Entity doesn't exist"));
    }

    @Transactional
    public List createListEntity(ListDTO.createListDTO createListDTO){
        Long userId = createListDTO.getUserId();
        List list = new List();
        list.setName(createListDTO.getName());
        list.setCreatedBy(userId);
        list.setUpdatedBy(userId);

        List newList = listRepository.save(list);

        BoardToList boardToList = new BoardToList();
        boardToList.setBoardId(createListDTO.getBoardId());
        boardToList.setListId(newList.getId());
        boardToList.setCreatedBy(userId);
        boardToList.setUpdatedBy(userId);

        boardToListRepository.save(boardToList);

        return newList;
    }

    @Transactional
    public boolean updateListEntity(ListDTO.updateListDTO updateListDTO) {
        try{
            List list = getListEntityByIdOrThrow(updateListDTO.getId());
            list.setName(updateListDTO.getName());
            list.setUpdatedBy(updateListDTO.getUserId());

            return false;
        }
        catch (Exception e){
            return false;
        }
    }

    @Transactional
    public boolean deleteListEntity(ListDTO.deleteListDTO deleteListDTO) {
        try {
            boardToListRepository.deleteBoardToListByBoardIdAndListId(deleteListDTO.getBoardId(), deleteListDTO.getListId());
            try{
                listRepository.deleteById(deleteListDTO.getListId());
            } catch (Exception e){
                return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
