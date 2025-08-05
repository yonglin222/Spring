package com.dw.gameshop_mybatis.model;

import com.dw.gameshop_mybatis.dto.UserDTO;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String userName;
    private String password;
    private String email;
    private String realName;
    private Authority authority;
    private LocalDateTime createdAt;

    public UserDTO toDto() {
        return new UserDTO(
                this.userName,
                null,
                this.email,
                this.realName,
                authority.getAuthorityName()
        );
    }
}
