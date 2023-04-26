package com.example.test.login.Controller;
import com.example.test.login.entity.List;
import com.example.test.login.entity.tools.Result;
import com.example.test.login.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    ListService listService;

    @PostMapping("/query")
    public List[] query(String email){
        return listService.query(email);
    }

    @PostMapping("/insert")
    public Result<List> insert(List list){
        return listService.insert(list);
    }

    @PostMapping("/delete")
    public Result<List> delete(List list){
        return listService.delete(list);
    }
}
