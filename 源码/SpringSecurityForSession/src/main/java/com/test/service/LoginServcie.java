package com.test.service;

import com.test.pojo.ResponseResult;
import com.test.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginServcie {
    ResponseResult login(User user,
                         HttpServletRequest request,
                         HttpServletResponse response);
}