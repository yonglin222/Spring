package com.dw.gameshop_mybatis.mapper;

import com.dw.gameshop_mybatis.dto.UserDTO;
import com.dw.gameshop_mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User getUserByUserName(@Param("username") String username);
    void registerUser(@Param("userDTO") UserDTO userDTO);
}
