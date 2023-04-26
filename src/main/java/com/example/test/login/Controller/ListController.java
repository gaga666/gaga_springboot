package com.example.test.login.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class Controller {
    @GetMapping("/test1")
    public String test() {
        return "test111";
    }
}
