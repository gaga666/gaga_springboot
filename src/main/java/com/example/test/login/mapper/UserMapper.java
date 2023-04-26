package com.example.test.login.mapper;

import com.example.test.login.entity.Cost;
import com.example.test.login.entity.List;
import com.example.test.login.entity.User;
import com.example.test.login.vo.UserChangeEmail;
import com.example.test.login.vo.UserChangePassword;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {

    /**
     * 插入用户信息，注册用
     * @param user
     */

    @Insert("insert into user (username,password,email)   values (#{user.username},#{user.password},#{user.email})")
    void insertUser(@Param("user") User user);

    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    @Select("select *   from user   where email = #{email}")
    User queryByEmail(@Param("email") String email);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Select("select *  from user  where username = #{username}")
    User queryByUsername(@Param("username") String username);

    /**
     * 根据邮箱修改密码
     * @param userChangePassword
     * @return
     */
    @Update("update user  set password = #{userChange.password}  where email = #{userChange.email}")
    boolean updateByEmail(@Param("userChange") UserChangePassword userChangePassword);

    /**
     * 根据邮箱修改邮箱
     * @param userChangeEmail
     * @return
     */
    @Update("update user  set email = #{userChange.email_2}  where email = #{userChange.email_1}")
    boolean updateForEmail(@Param("userChange")UserChangeEmail userChangeEmail);


    /**
     *
     *
     * list 表格相关信息
     *
     * @param email
     * @return
     */
    @Select("select *  from list  where email = #{email}")
    List[] listQueryByEmail(@Param("email") String email);

    @Insert("insert into list (text,title,date,email)   values (#{list.text},#{list.title},#{list.date},#{list.email})")
    void listInsert(@Param("list") List list);

    @Update("delete from list where title = #{list.title} and email = #{list.email} and text = #{list.text}")
    boolean listDelete(@Param("list") List list);


    /**
     *
     *
     * cost表格相关信息
     *
     * @param email
     * @return
     */
    @Select("select *  from cost  where email = #{email}")
    Cost CostQueryByEmail(@Param("email") String email);


    @Insert("update cost set `in` = #{cost.in}, `out` = #{cost.out}, input = #{cost.input}, output = #{cost.output}   where email = #{cost.email}")
    void costUpdate(@Param("cost") Cost cost);


}
