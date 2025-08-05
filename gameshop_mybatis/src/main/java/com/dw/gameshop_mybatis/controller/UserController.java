package com.dw.gameshop_mybatis.controller;

import com.dw.gameshop_mybatis.dto.UserDTO;
import com.dw.gameshop_mybatis.exception.UnauthorizedUserException;
import com.dw.gameshop_mybatis.model.User;
import com.dw.gameshop_mybatis.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO,
                                        HttpServletRequest request) {
        String username = userDTO.getUserName();
        String password = userDTO.getPassword();
        if (userService.validateUser(username, password)) {
            HttpSession session = request.getSession(); // 세션정보 획득
            session.setAttribute("username", username); // username 저장
            return new ResponseEntity<>(
                    "Login succcessful",
                    HttpStatus.OK);
        } else {
            throw new UnauthorizedUserException(
                    "아이디 또는 비밀번호가 올바르지 않습니다");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate(); // 세션종료
        return new ResponseEntity<>(
                "성공적으로 로그아웃 되었습니다",
                HttpStatus.OK);
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser(
            HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        return new ResponseEntity<>(user.toDto(), HttpStatus.OK);
    }
}
