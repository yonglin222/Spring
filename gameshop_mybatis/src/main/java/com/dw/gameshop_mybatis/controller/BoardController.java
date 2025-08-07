package com.dw.gameshop_mybatis.controller;

import com.dw.gameshop_mybatis.dto.BoardDTO;
import com.dw.gameshop_mybatis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping ("/all")
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        return new ResponseEntity<>(
                boardService.getAllBoards(),
                HttpStatus.OK
        );
    }

    @PostMapping ("/save")
    public ResponseEntity<Integer> saveBoard(
            @RequestBody BoardDTO boardDTO) {
        return new ResponseEntity<>(
                boardService.saveBoard(boardDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping ("update")
    public ResponseEntity<Integer> updateBoard(
            @RequestBody BoardDTO boardDTO) {
        return new ResponseEntity<>(
                boardService.updateBoard(boardDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(
            @PathVariable long id) {
        return new ResponseEntity<>(
                boardService.deleteBoard(id),
                HttpStatus.OK);
    }
}
