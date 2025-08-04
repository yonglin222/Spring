package com.dw.gameshop_mybatis.service;

import com.dw.gameshop_mybatis.dto.GameDTO;
import com.dw.gameshop_mybatis.mapper.GameMapper;
import com.dw.gameshop_mybatis.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    @Autowired
    GameMapper gameMapper;

    public List<GameDTO> getAllGames() {
        List<GameDTO> gameDTOList = new ArrayList<>();
        List<Game> gameList = gameMapper.getAllGames();
        for (Game game : gameList) {
            gameDTOList.add(game.toDTO());
        }
        return gameDTOList;
    }

    public GameDTO getGameById(long id) {
        Game game = gameMapper.getGameById(id);
        if (game != null) {
            return game.toDTO();
        } else {
            throw new RuntimeException("해당 Game이 없습니다. ID : " + id);
        }
    }
}
