package com.example.chatdemo.service;

import com.example.chatdemo.common.Result;
import com.example.chatdemo.dto.LoginRequest;
import com.example.chatdemo.dto.LoginResponse;
import com.example.chatdemo.dto.RegisterRequest;
import com.example.chatdemo.dto.RegisterResponse;

public interface AuthService {
    Result<RegisterResponse> register(RegisterRequest request);

    Result<LoginResponse> login(LoginRequest request);
}
