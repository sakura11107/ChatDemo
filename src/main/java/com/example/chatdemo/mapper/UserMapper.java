package com.example.chatdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chatdemo.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
