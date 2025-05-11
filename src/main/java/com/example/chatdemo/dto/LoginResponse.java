package com.example.chatdemo.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private int code;
    private String message;
    private String token;
    private String username;

    public LoginResponse(int code, String message, String token, String username) {
        this.code = code;
        this.message = message;
        this.token = token;
        this.username = username;
    }
}
