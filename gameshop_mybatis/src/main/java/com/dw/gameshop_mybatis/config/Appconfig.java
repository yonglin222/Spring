package com.dw.gameshop_mybatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Appconfig {
    /*
    @Bean 어노테이션이 붙은 메서드의 이름은 기본적으로 스프링컨테이터에 등록될 Bean의 이름이 됨.
    즉, 스프링컨테이너에게 passwordEncoder()을 호출하여
    passwordEncoder라는 이름의 빈을 하나 만들어달라는 설정코드임.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
