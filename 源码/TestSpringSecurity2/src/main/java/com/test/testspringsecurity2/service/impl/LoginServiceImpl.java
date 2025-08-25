package com.test.testspringsecurity2.service.impl;

import com.test.testspringsecurity2.pojo.LoginUser;
import com.test.testspringsecurity2.pojo.ResponseResult;
import com.test.testspringsecurity2.pojo.User;
import com.test.testspringsecurity2.service.LoginServcie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginServcie {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;


    @Override
    public ResponseResult login(
            User user,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // 驗證帳密（失敗會丟出例外）
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //  建立並設定 SecurityContext
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticate);
        SecurityContextHolder.setContext(context);

        // 明確儲存到 Session（關鍵）
        securityContextRepository.saveContext(context, request, response);

        // 回傳必要的登入資訊（不要回 token）
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", loginUser.getUser().getId());
        payload.put("username", loginUser.getUsername());
        payload.put("roles", loginUser.getAuthorities());

        return new ResponseResult(200, "登录成功", payload);
    }
}