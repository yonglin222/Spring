package com.dw.gameshop_mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 실제 URL 주소와 파일의 실제위치가 다른경우, 경로를 매핑시켜주는 역할
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/gameshop/**") // 요청 URL 경로
//                .addResourceLocations("classpath:static/"); // 실제위치
//    }
}
