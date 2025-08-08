package com.dw.gameshop_mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // RestAPI를 제어하지 않고 정적페이지 URL을 제어하기 위해 사용
public class ViewController {
    @GetMapping("/gameshop/board.html")
    public String board() {
        return "forward:/board.html";
    }
}
