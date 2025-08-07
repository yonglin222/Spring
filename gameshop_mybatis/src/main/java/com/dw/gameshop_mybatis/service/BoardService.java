package com.dw.gameshop_mybatis.service;

import com.dw.gameshop_mybatis.dto.BoardDTO;
import com.dw.gameshop_mybatis.exception.ResourceNotFoundException;
import com.dw.gameshop_mybatis.mapper.BoardMapper;
import com.dw.gameshop_mybatis.mapper.UserMapper;
import com.dw.gameshop_mybatis.model.Board;
import com.dw.gameshop_mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    UserMapper userMapper;

    public List<BoardDTO> getAllBoards() {
        // 객체지향 프로그래밍 방식
//        List<BoardDTO> boardDTOList = new ArrayList<>();
//        List<Board> boardList = boardMapper.getAllBoards();
//        for (Board board : boardList) {
//            boardDTOList.add(board.toDto());
//        }
//        return boardDTOList;
        // 함수형 프로그래밍 방식
        return boardMapper.getAllBoards().stream()
                .map(Board::toDto)
                // or  .map(b->b.toDto())
                     .toList();
        /*
        1) 재료준비 (재료는 컬렉션이 일반적임) boardMapper.getAllBoards()
        2) 컨베이어벨트역할 stream()
        3) 재료의 재포장 (=수정, 변형) map()
        4) 새로운 리스트에 담기 toList()
         */
    }
    public int saveBoard(BoardDTO boardDTO) {
        User user = userMapper.getUserByUserName(boardDTO.getAuthorName());
        if (user == null) {
            throw new ResourceNotFoundException(
                    "User not found with name: " +boardDTO.getAuthorName()
            );
        }
        Board newBoard = new Board(
                null, // id는 자동생성
                boardDTO.getTitle(),
                boardDTO.getContent(),
                user,
                LocalDateTime.now(),
                LocalDateTime.now(),
                true
        );
        return boardMapper.saveBoard(newBoard);
    }
    public int updateBoard(BoardDTO boardDTO) {
        Board board = boardMapper.getBoardByid(boardDTO.getId());
        if (board == null) {
            throw new ResourceNotFoundException("No Board Exist");
        }
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setModifiedDate(LocalDateTime.now());
        return boardMapper.updateBoard(board);
    }
    public String deleteBoard(long id) {
        if (boardMapper.deleteBoard(id) > 0) {
            return "게시글 " + id + "가 삭제되었습니다";
        } else {
            return "게시글 " + id + "가 존재하지 않습니다";
        }
    }
}
