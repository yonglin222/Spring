package com.dw.gameshop_mybatis.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String userName;
    private String password;
    private String email;
    private String realName;
    private String authority;
}
