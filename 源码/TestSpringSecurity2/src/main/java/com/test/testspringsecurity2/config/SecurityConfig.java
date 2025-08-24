package com.test.testspringsecurity2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class SecurityConfig {

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST, "/api/login", "/api/public/**").permitAll()
                    .anyRequest().authenticated()
            )

            // 默認登入頁面
            .formLogin(Customizer.withDefaults())

            //配置注销成功处理器
            .logout(logout -> logout
                    .logoutSuccessHandler(logoutSuccessHandler)
            );

        return http.build();
    }
}