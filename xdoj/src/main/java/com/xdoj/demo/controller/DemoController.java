package com.xdoj.demo.controller;

import com.xdoj.demo.domain.User;
import com.xdoj.demo.result.CodeMsg;
import com.xdoj.demo.result.Result;
import com.xdoj.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {


    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("hello,imooc");
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> getById(){
        return Result.success(userService.getById(1));
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> helloError(){

        userService.insert();

        return Result.success(true);
    }

    @RequestMapping("/th")
    public String th(Model model){
        model.addAttribute("name", "yuanzuo");
        return "hello";
    }
}
