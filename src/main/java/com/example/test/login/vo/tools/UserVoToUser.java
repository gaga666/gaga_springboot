package com.example.test.login.vo.tools;

import com.example.test.login.entity.User;
import com.example.test.login.vo.UserVo;

public class UserVoToUser {
    public static User toUser(UserVo userVo){

        User user = new User();

        user.setUsername(userVo.getUsername());
        user.setPassword(userVo.getPassword());
        user.setEmail(userVo.getEmail());

        return user;
    }
}
