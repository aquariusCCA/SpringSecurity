package com.test.service;

import com.test.domain.ResponseResult;
import com.test.domain.User;

public interface LoginServcie {
    ResponseResult login(User user);

    ResponseResult logout();
}