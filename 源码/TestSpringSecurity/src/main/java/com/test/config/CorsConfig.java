package com.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("http://localhost:5173")
                // 设置允许的请求方式
                .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
                // 设置允许的header属性
                .allowedHeaders("Content-Type","Accept","Authorization","token","X-Requested-With")
                // 是否允许cookie
                .allowCredentials(true)
                // 跨域允许时间
                .maxAge(3600);
    }
}
