package com.dw.gameshop_mybatis.mapper;

import com.dw.gameshop_mybatis.model.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int saveReview(@Param("review") Review review);
    List<Review> findByGameId(@Param("gameId") Long gameId);
    Review findById(@Param("id") Long id);
    int deleteReview(@Param("id") Long id);
}
