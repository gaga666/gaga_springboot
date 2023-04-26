package com.example.test.login.vo;

import com.example.test.login.entity.User;

public class UserVoToUser {
    public static User toUser(UserVo userVo){

        User user = new User();

        user.setUsername(userVo.getUsername());
        user.setPassword(userVo.getPassword());
        user.setEmail(userVo.getEmail());

        return user;
    }
}
