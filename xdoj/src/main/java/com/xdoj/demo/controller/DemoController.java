package com.xdoj.demo.controller;

import com.xdoj.demo.domain.User;
import com.xdoj.demo.redis.RedisService;
import com.xdoj.demo.redis.UserKey;
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

    @Autowired
    RedisService redisService;

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("hello,imooc");
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> getById(){
        return Result.success(userService.getById(19030100339L));
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> helloError(){

        return Result.success(true);
    }

    @RequestMapping("/th")
    public String th(Model model){
        model.addAttribute("name", "yuanzuo");
        return "hello";
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User user = redisService.get(UserKey.token, 1 + "", User.class);
        return Result.success(user);
    }

//    key1 key2大家都用的话容易覆盖 // 加上 每个人的前缀就好了
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<User> redisSet(){
        User user = new User();
        user.setId(1);
        user.setPassword("123456");
        redisService.set(UserKey.token,"2", user);
        String str = redisService.get(UserKey.token,"2", String.class);
        return Result.success(user);
    }
}
