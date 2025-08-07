package com.dw.gameshop_mybatis.dto;

import com.dw.gameshop_mybatis.model.Game;
import com.dw.gameshop_mybatis.model.User;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PurchaseDTO {
    private long id;
    private GameDTO game; // JSON의 'game' 객체를 위한 필드
    private UserDTO user; // JSON의 'user' 객체를 위한 필드
    private LocalDateTime purchaseTime;
}
