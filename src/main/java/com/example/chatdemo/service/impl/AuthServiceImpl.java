package com.example.chatdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatdemo.common.Result;
import com.example.chatdemo.dto.LoginRequest;
import com.example.chatdemo.dto.LoginResponse;
import com.example.chatdemo.dto.RegisterRequest;
import com.example.chatdemo.dto.RegisterResponse;
import com.example.chatdemo.mapper.UserMapper;
import com.example.chatdemo.model.User;
import com.example.chatdemo.service.AuthService;
import com.example.chatdemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result<RegisterResponse> register(RegisterRequest request){
        if (userMapper.selectOne(new QueryWrapper<User>().eq("username", request.getUsername())) != null) {
            return Result.error(400,"用户名已存在");
        }
        if (userMapper.selectOne(new QueryWrapper<User>().eq("email", request.getEmail())) != null) {
            return Result.error(400,"邮箱已被使用");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setOnline(false);

        userMapper.insert(user);
        RegisterResponse response = new RegisterResponse(200,"注册成功","success");

        return Result.success(response);
    }

    @Override
    public Result<LoginResponse> login(LoginRequest request) {

        if (request == null || request.getUsername() == null || request.getPassword() == null) {
            return Result.error(400, "用户名或密码不能为空");
        }
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", request.getUsername()));
        if (user == null) {
            return Result.error(400,"用户名不存在或密码错误");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return Result.error(400,"用户名不存在或密码错误");
        }

        user.setOnline(true);
        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userMapper.updateById(user);

        String token = jwtUtil.generateToken(user.getId(),user.getUsername());
        String username=user.getUsername();
        LoginResponse response = new LoginResponse(200,"登录成功",token,username);

        return Result.success(response);
    }
}
