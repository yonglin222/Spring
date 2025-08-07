package com.dw.gameshop_mybatis.mapper;

import com.dw.gameshop_mybatis.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    Board getBoardByid(@Param("id") long id);

    List<Board> getAllBoards();

    int saveBoard(@Param("board") Board board);

    int updateBoard(@Param("board") Board board);

    int deleteBoard(@Param("id") long id);
}
