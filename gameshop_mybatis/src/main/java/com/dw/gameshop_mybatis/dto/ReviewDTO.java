package com.dw.gameshop_mybatis.dto;

import com.dw.gameshop_mybatis.enums.GameRating;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ReviewDTO {
    private long id;
    private GameDTO game;
    private String userName;
    private GameRating point;
    private String reviewText;
    private LocalDateTime createdAt;
}
