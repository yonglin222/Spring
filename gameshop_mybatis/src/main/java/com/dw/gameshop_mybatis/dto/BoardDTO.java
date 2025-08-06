package com.dw.gameshop_mybatis.dto;

import com.dw.gameshop_mybatis.model.User;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime modifiedDate;
}
