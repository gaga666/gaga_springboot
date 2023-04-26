package com.example.test.Controller;

import com.example.test.entity.Result;
import com.example.test.entity.User;
import com.example.test.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 注册接口
     *
     * @param user 客户端传入的user数据
     * @return Result<User> 返回注册成功或者注册失败的UserBean
     */
    @PostMapping(value = "/register")
    public Result<User> register(@RequestBody User user){
        logger.info("调用register,传入的user:" + user.toString());
        return userService.register(user);
    }

    /**
     * 登录接口
     *
     * @param user 客户端传入的user数据
     * @return Result<User> 返回登录成功或者登录失败的UserBean
     */

    @PostMapping(value = "/login")
    public Result<User> login(@RequestBody User user){
        logger.info("调用loginm传入的user:" + user.toString());
        return userService.login(user);
    }
}
