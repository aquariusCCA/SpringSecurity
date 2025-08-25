package com.test.testspringsecurity2.service;

import com.test.testspringsecurity2.pojo.ResponseResult;
import com.test.testspringsecurity2.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginServcie {
    ResponseResult login(User user,
                         HttpServletRequest request,
                         HttpServletResponse response);
}