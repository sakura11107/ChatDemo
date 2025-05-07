package com.example.chatdemo.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private int code;
    private String message;
    private String data;

    public RegisterResponse(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
