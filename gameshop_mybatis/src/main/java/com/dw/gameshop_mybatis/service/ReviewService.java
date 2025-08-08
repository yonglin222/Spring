package com.dw.gameshop_mybatis.service;

import com.dw.gameshop_mybatis.dto.ReviewDTO;
import com.dw.gameshop_mybatis.dto.UserDTO;
import com.dw.gameshop_mybatis.enums.GameRating;
import com.dw.gameshop_mybatis.exception.PermissionDeniedException;
import com.dw.gameshop_mybatis.exception.ResourceNotFoundException;
import com.dw.gameshop_mybatis.mapper.GameMapper;
import com.dw.gameshop_mybatis.mapper.PurchaseMapper;
import com.dw.gameshop_mybatis.mapper.ReviewMapper;
import com.dw.gameshop_mybatis.mapper.UserMapper;
import com.dw.gameshop_mybatis.model.Game;
import com.dw.gameshop_mybatis.model.Review;
import com.dw.gameshop_mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    GameMapper gameMapper;
    @Autowired
    PurchaseMapper purchaseMapper;
    @Autowired
    ReviewMapper reviewMapper;

    @Transactional
    public int saveReview(ReviewDTO reviewDto) {
        // 1. 리뷰작성자가 해당 게임의 구매내역이 있는지 확인
        // int purchaseCount = purchaseMapper.countPurchaseByUserNameAndGameId(
        return 0;
    }

    public List<ReviewDTO> getReviewsByGameId(Long gameId) {
        return null;
    }

    public String deleteReview(Long reviewId, User currentUser) {
        // 현재 세션의 유저가 리뷰작성자가 맞는지 확인해야함
        return null;
    }
}
