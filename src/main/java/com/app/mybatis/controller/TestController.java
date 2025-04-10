package com.app.mybatis.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/test/*")
public class TestController {

//    Get방식
    @GetMapping("ex")
//    Post 방식
//    @PostMapping
    public String ex(){
        return "first";
    }

    @GetMapping("/ex01")
    public void ex01(HttpServletResponse response)throws IOException {
        response.getWriter().write("Hello World! 💪🏻");
    }
}
