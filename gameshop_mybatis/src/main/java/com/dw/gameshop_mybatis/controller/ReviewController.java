package com.dw.gameshop_mybatis.controller;

import com.dw.gameshop_mybatis.dto.ReviewDTO;
import com.dw.gameshop_mybatis.model.User;
import com.dw.gameshop_mybatis.service.ReviewService;
import com.dw.gameshop_mybatis.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Integer> saveReview(@RequestBody ReviewDTO reviewDTO) {
        return new ResponseEntity<>(
                reviewService.saveReview(reviewDTO),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getReviewsByGameId(@RequestParam long gameId) {
        return new ResponseEntity<>(
                reviewService.getReviewsByGameId(gameId),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable long id, HttpServletRequest request) {
        User currentUser = userService.getCurrentUser(request);
        return new ResponseEntity<>(
                reviewService.deleteReview(id, currentUser),
                HttpStatus.OK);
    }
}
