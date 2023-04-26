package com.example.test.login.service;

import com.example.test.login.entity.tools.CodeConstant;
import com.example.test.login.entity.tools.Result;
import com.example.test.login.entity.User;
import com.example.test.login.mapper.UserMapper;
import com.example.test.login.vo.UserChangeEmail;
import com.example.test.login.vo.UserChangePassword;
import com.example.test.login.vo.UserVo;
import com.example.test.login.vo.tools.UserVoToUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Qualifier("stringRedisTemplate")
    RedisTemplate<String,String> redis;

    private Result<User> mResult = new Result<>();

    @Value("${spring.mail.username}")
    private String from;


    /**
     * 给前端输入的邮箱，发送验证码
     * @param email
     * @return
     */
    public Result<User> sendMimeMail( String email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setSubject("验证码邮件");//主题
            //生成随机数
            String code = randomCode();

            //验证码存入redis数据库
            ValueOperations<String,String> valueOperations = redis.opsForValue();
            valueOperations.set(email,code);
            redis.expire(email,5, TimeUnit.MINUTES);

            mailMessage.setText("(有效时长5分钟)您收到的验证码是："+code);//内容

            mailMessage.setTo(email);//发给谁

            mailMessage.setFrom(from);//你自己的邮箱

            javaMailSender.send(mailMessage);//发送

            mResult.setCode(CodeConstant.MAIL_SUCCESS);
            mResult.setMsg("邮件发送成功");
            return mResult;
        }catch (Exception e){
            e.printStackTrace();
            mResult.setCode(CodeConstant.MAIL_ERROR);
            mResult.setMsg("邮件发送失败");
            return mResult;
        }
    }

    /**
     * 随机生成6位数的验证码
     * @return String code
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * 注册
     * @param userVo
     * @return
     */
    public Result<User> registered(UserVo userVo) {
        //获取session中的验证信息
        String email = userVo.getEmail();
        String username = userVo.getUsername();
        ValueOperations<String, String> valueOperations = redis.opsForValue();
        User user = userMapper.queryByEmail(email);
        User user1 = userMapper.queryByUsername(username);
        String code = valueOperations.getAndDelete(email);
        //获取前端的提交的验证信息
        String voCode = userVo.getCode();
        if (code == null) {
            mResult.setCode(CodeConstant.CODE_ERROR);
            mResult.setMsg("验证码已过期");
        }else if(!voCode.equals(code)){
            mResult.setCode(CodeConstant.CODE_ERROR);
            mResult.setMsg("验证码错误");
        } else if (user != null) {
            mResult.setCode(CodeConstant.ALREADY_EXISTS);
            mResult.setMsg("该邮箱已被注册");
        } else if (user1 != null) {
            mResult.setCode(CodeConstant.ALREADY_EXISTS);
            mResult.setMsg("该用户名已被注册");
        } else if (voCode.equals(code)) {
            userMapper.insertUser(UserVoToUser.toUser(userVo));
            mResult.setCode(CodeConstant.REGISTER_SUCCESS);
            mResult.setMsg("注册成功");
        }else {
            mResult.setCode(CodeConstant.FAIL);
            mResult.setMsg("注册流程出现未知错误");
        }

        System.out.println("code-------"+code+"--------");
        System.out.println("vocode-------"+voCode+"--------");
        //跳转成功页面
        return mResult;
    }

    /**
     * 通过输入email查询password，然后比较两个password，如果一样，登录成功
     * @param username
     * @param password
     * @return
     */

    public Result<User> loginIn(String username, String password){

        User user = userMapper.queryByUsername(username);

        if(user == null){
            mResult.setCode(CodeConstant.NO_USER);
            mResult.setMsg("用户不存在");
        } else if (user.getPassword().equals(password)) {
            mResult.setCode(CodeConstant.SUCCESS);
            mResult.setMsg("登录成功");
        } else if (!user.getPassword().equals(password)) {
            mResult.setCode(CodeConstant.PASSWORD_ERROR);
            mResult.setMsg("密码错误");
        }else {
            mResult.setCode(CodeConstant.FAIL);
            mResult.setMsg("登陆环节出现未知错误");
        }
        return mResult;
    }


    public Result<User> changePassword(UserChangePassword userChangePassword){

        ValueOperations<String ,String > valueOperations = redis.opsForValue();
        String code = valueOperations.getAndDelete(userChangePassword.getEmail());
        User user = userMapper.queryByEmail(userChangePassword.getEmail());
        String password = user.getPassword();
        if (code == null){
            mResult.setCode(CodeConstant.CODE_ERROR);
            mResult.setMsg("验证码错误");
        } else if (user == null){
            mResult.setCode(CodeConstant.NO_USER);
            mResult.setMsg("用户不存在");
        } else if (!code.equals(userChangePassword.getCode())) {
            mResult.setCode(CodeConstant.CODE_ERROR);
            mResult.setMsg("验证码错误");
        } else if (password.equals(userChangePassword.getPassword())) {
            mResult.setCode(CodeConstant.PASSWORD_SAME);
            mResult.setMsg("新密码与原密码相同");
        }else if(code.equals(userChangePassword.getCode())){
            userMapper.updateByEmail(userChangePassword);
            mResult.setCode(CodeConstant.CHANGE_SUCCESS);
            mResult.setMsg("密码修改成功");
        }else {
            mResult.setCode(CodeConstant.FAIL);
            mResult.setMsg("未知错误");
        }
        return mResult;
    }

    public Result<User> changeEmail(UserChangeEmail userChangeEmail){

        ValueOperations<String ,String > valueOperations = redis.opsForValue();
        String code = valueOperations.getAndDelete(userChangeEmail.getEmail_1());
        User user = userMapper.queryByEmail(userChangeEmail.getEmail_1());
        String email = user.getEmail();
        if (code == null){
            mResult.setCode(CodeConstant.CODE_ERROR);
            mResult.setMsg("验证码错误");
        } else if (user == null){
            mResult.setCode(CodeConstant.NO_USER);
            mResult.setMsg("用户不存在");
        } else if (!code.equals(userChangeEmail.getCode())) {
            mResult.setCode(CodeConstant.CODE_ERROR);
            mResult.setMsg("验证码错误");
        } else if (user.getEmail().equals(userChangeEmail.getEmail_2())) {
            mResult.setCode(CodeConstant.EMAIL_SAME);
            mResult.setMsg("新邮箱与原邮箱相同");
        }else if(code.equals(userChangeEmail.getCode())){
            userMapper.updateForEmail(userChangeEmail);
            mResult.setCode(CodeConstant.EMAIL_SUCCESS);
            mResult.setMsg("邮箱修改成功");
        }else {
            mResult.setCode(CodeConstant.FAIL);
            mResult.setMsg("未知错误");
        }
        return mResult;
    }
}
