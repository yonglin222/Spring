package com.dw.gameshop_mybatis.model;

import com.dw.gameshop_mybatis.dto.ReviewDTO;
import com.dw.gameshop_mybatis.enums.GameRating;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Review {
    private long id;
    private Game game; // Game 객체 포함
    private User user; // User 객체 포함
    private GameRating point;
    private String reviewText;
    private LocalDateTime createdAt;

    public ReviewDTO toDto() {
        return new ReviewDTO(
                this.id,
                this.game.toDto(),
                this.user.getUserName(),
                this.point,
                this.reviewText,
                this.createdAt
        );
    }
}
