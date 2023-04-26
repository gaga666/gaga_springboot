package com.example.test.login.Controller;

import com.example.test.login.entity.Cost;
import com.example.test.login.entity.tools.Result;
import com.example.test.login.service.CostService;
import com.example.test.login.vo.CostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cost")
public class CostController {

    @Autowired
    CostService costService;

    @PostMapping("/queryIn")
    public double queryIn(String email){
         return costService.queryIn(email);
    }
    @PostMapping("/queryOut")
    public double queryOut(String email){
        return costService.queryOut(email);
    }
    @PostMapping("/queryInput")
    public double queryInput(String email){
        return costService.queryInput(email);
    }
    @PostMapping("/queryOutput")
    public double queryOutput(String email){
        return costService.queryOutput(email);
    }

    @PostMapping("/query")
    public Cost query(String email){
        return costService.query(email);
    }

    @PostMapping("/update")
    public Result<Cost> update(CostVo costVo){
        return costService.update(costVo);
    }
}
