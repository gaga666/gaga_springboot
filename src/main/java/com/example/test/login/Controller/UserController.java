package com.example.test.login.Controller;

import com.example.test.login.entity.tools.Result;
import com.example.test.login.entity.User;
import com.example.test.login.service.MailService;
import com.example.test.login.vo.UserChangeEmail;
import com.example.test.login.vo.UserChangePassword;
import com.example.test.login.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MailService mailService;

    @PostMapping("/sendEmail")
    @ResponseBody
    public Result<User> sendEmail(String email){
        return mailService.sendMimeMail(email);
    }

    @PostMapping("/register")
    @ResponseBody
    public Result<User> register(UserVo userVo){
        return mailService.registered(userVo);
    }

    @PostMapping("/login")
    @ResponseBody
    public Result<User> login(String username, String password){
        return mailService.loginIn(username,password);
    }

    @PostMapping("/change")
    @ResponseBody
    public Result<User> changePw(UserChangePassword userChangePassword){
        return mailService.changePassword(userChangePassword);
    }

    @PostMapping("/changeemail")
    @ResponseBody
    public Result<User> changeEmail(UserChangeEmail userChangeEmail){
        return mailService.changeEmail(userChangeEmail);
    }
}
