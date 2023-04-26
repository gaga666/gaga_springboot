package com.example.test.login.entity;

import jdk.dynalink.beans.StaticClass;

import java.util.Stack;

public class CodeConstant {

    //登录成功
    public static final int SUCCESS = 1;
    //未知错误
    public static final int FAIL = -1;
    //用户已经存在
    public static final int ALREADY_EXISTS = 100;
    //密码错误
    public static final int PASSWORD_ERROR = -200;
    //用户名为空
    public static final int EMPTY = -100;
    //用户不存在login
    public static final int NO_USER = -300;
    //邮件发送异常
    public static final int MAIL_ERROR = -101;
    //邮件发送成功
    public static final int MAIL_SUCCESS = 101;
    //验证码错误
    public static final int CODE_ERROR = -201;
    //注册成功
    public static final int REGISTER_SUCCESS = 301;
    //修改密码 修改成功
    public static final int CHANGE_SUCCESS = 401;
    //修改密码 与原密码相同
    public static final int PASSWORD_SAME = -401;
    //修改邮箱 与原邮箱相同
    public static final int EMAIL_SAME = -501;
    //修改邮箱
    public static final int EMAIL_SUCCESS = 501;

}
