package com.example.test.login.service;

import com.example.test.login.entity.List;
import com.example.test.login.entity.tools.CodeConstant;
import com.example.test.login.entity.tools.Result;
import com.example.test.login.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService {

    @Autowired
    UserMapper userMapper;

    private Result<List> mResult = new Result<>();

    public List[] query(String email){
        List[] lists = new List[]{};
        lists = userMapper.listQueryByEmail(email);
        return lists;
    }

    public Result<List> insert(List list){
        userMapper.listInsert(list);
        mResult.setCode(CodeConstant.SUCCESS);
        mResult.setMsg("插入成功");
        return mResult;
    }

    public Result<List> delete(List list){
        userMapper.listDelete(list);
        mResult.setCode(CodeConstant.SUCCESS);
        mResult.setMsg("删除成功");
        return mResult;
    }

}
