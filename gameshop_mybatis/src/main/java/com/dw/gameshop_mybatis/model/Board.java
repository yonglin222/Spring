package com.dw.gameshop_mybatis.model;

import com.dw.gameshop_mybatis.dto.BoardDTO;
import com.dw.gameshop_mybatis.dto.UserDTO;
import lombok.*;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Board {
    private Long id;
    private String tatle;
    private String content;
    private User user;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Boolean isActive = true;

    public BoardDTO toDto() {
        return new BoardDTO(
                this.id,
                this.tatle,
                this.content,
                this.user.getUserName(),
                this.modifiedDate);
    }
}
