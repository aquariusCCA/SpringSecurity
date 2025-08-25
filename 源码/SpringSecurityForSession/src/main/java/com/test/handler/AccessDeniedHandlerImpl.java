package com.test.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.pojo.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public AccessDeniedHandlerImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper; // 直接用 Spring Boot 預設的 ObjectMapper
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException {

        // 準備回傳內容
        ResponseResult<?> body = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "權限不足");

        // 正確設定 HTTP 響應
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 由 Jackson 輸出 JSON（避免中間轉字串造成額外拷貝）
        objectMapper.writeValue(response.getWriter(), body);
    }
}