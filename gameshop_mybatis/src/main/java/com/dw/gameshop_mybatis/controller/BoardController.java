package com.dw.gameshop_mybatis.controller;

import com.dw.gameshop_mybatis.dto.BoardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    @GetMapping // 이거 없어도 됨 ("/all")
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        return null;
    }

    @PostMapping // 이거 없어도 됨 ("/save")
    public ResponseEntity<Integer> saveBoard(
            @RequestBody BoardDTO boardDTO) {

        return null;
    }

    @PutMapping // 이거 없어도 됨("update")
    public ResponseEntity<Integer> updateBoard(
            @RequestBody BoardDTO boardDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(
            @PathVariable long id) {
        return null;
    }
}
