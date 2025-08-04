package com.dw.gameshop_mybatis.mapper;

import com.dw.gameshop_mybatis.model.Game;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface GameMapper {
    List<Game> getAllGames();
    Game getGameById(@Param("id") long id);
}
