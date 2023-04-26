package com.example.test.login.service;

import com.example.test.login.entity.Cost;
import com.example.test.login.entity.tools.CodeConstant;
import com.example.test.login.entity.tools.Result;
import com.example.test.login.mapper.UserMapper;
import com.example.test.login.vo.CostVo;
import com.example.test.login.vo.tools.CostVoToCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostService {

    @Autowired
    UserMapper userMapper;

    Result<Cost> mResult = new Result<>();
    public double queryIn(String email){
        Cost cost = userMapper.CostQueryByEmail(email);
        return cost.getIn();
    }
    public double queryOut(String email){
        Cost cost = userMapper.CostQueryByEmail(email);
        return cost.getOut();
    }
    public double queryInput(String email){
        Cost cost = userMapper.CostQueryByEmail(email);
        return cost.getInput();
    }
    public double queryOutput(String email){
        Cost cost = userMapper.CostQueryByEmail(email);
        return cost.getOutput();
    }

    public Cost query(String email){
        Cost cost = userMapper.CostQueryByEmail(email);
        return cost;
    }

    public Result<Cost> update(CostVo costVo){
        Cost cost = userMapper.CostQueryByEmail(costVo.getEmail());
        cost.setOutput(cost.getOutput() + costVo.getOutput());
        cost.setInput(cost.getInput() + costVo.getInput());
        cost.setIn(cost.getIn() + costVo.getIn());
        cost.setOut(cost.getOut() + costVo.getOut());
        userMapper.costUpdate(cost);
        mResult.setCode(CodeConstant.SUCCESS);
        mResult.setMsg("收支记录成功");
        return mResult;
    }
}
