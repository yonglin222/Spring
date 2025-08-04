package com.dw.gameshop_mybatis.model;

import com.dw.gameshop_mybatis.dto.GameDTO;
import com.dw.gameshop_mybatis.enums.GameGenre;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Game {
    private long id;
    private String title;
    private GameGenre genre;
    private int price;
    private String imageUrl;
    private String text;

    public GameDTO toDTO() {
        return new GameDTO(
                this.id,
                this.title,
                this.genre,
                this.price,
                this.imageUrl,
                this.text
        );
    }
}
