package com.test.testspringsecurity2.controller;

import com.test.testspringsecurity2.pojo.ResponseResult;
import com.test.testspringsecurity2.pojo.User;
import com.test.testspringsecurity2.service.LoginServcie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user,
                                HttpServletRequest request,
                                HttpServletResponse response){
        return loginServcie.login(user, request, response);
    }
}