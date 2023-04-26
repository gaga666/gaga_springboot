package com.example.test.login.vo.tools;

import com.example.test.login.entity.Cost;
import com.example.test.login.vo.CostVo;

public class CostVoToCost {
    public static Cost toCost(CostVo costVo){
        Cost cost = new Cost();
        String email = costVo.getEmail();
        cost.setIn(costVo.getIn());
        cost.setOut(costVo.getOut());
        cost.setInput(costVo.getInput());
        cost.setOutput(costVo.getOutput());
        cost.setEmail(email);
        return cost;
    }
}
