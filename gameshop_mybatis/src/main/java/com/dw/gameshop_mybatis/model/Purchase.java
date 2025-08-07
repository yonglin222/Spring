package com.dw.gameshop_mybatis.model;

import com.dw.gameshop_mybatis.dto.BoardDTO;
import com.dw.gameshop_mybatis.dto.PurchaseDTO;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Purchase {
    private long id;
    private Game game;
    private User user;
    private LocalDateTime purchaseTime;


    public PurchaseDTO toDto() {
        return new PurchaseDTO(
                this.id,
                this.game.toDto(),
                this.user.toDto(),
                this.purchaseTime
        );
    }
}
