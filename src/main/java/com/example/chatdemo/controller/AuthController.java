package com.example.chatdemo.controller;

import com.example.chatdemo.common.Result;
import com.example.chatdemo.dto.LoginRequest;
import com.example.chatdemo.dto.LoginResponse;
import com.example.chatdemo.dto.RegisterRequest;
import com.example.chatdemo.dto.RegisterResponse;
import com.example.chatdemo.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "用户相关接口")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ApiOperation(value = "注册",notes = "注册")
    public Result<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录",notes = "登录")
    public  Result<LoginResponse> login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
}
