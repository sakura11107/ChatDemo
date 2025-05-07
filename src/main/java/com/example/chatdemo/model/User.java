package com.example.chatdemo.model;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;


@Data
@TableName("user")
public class User {
    @TableId(type = IdType.NONE)
    private String id;
    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;
    private Boolean online;
    private Timestamp lastLogin;
}
