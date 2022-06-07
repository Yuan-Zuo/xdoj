package com.xdoj.demo.controller;

import com.xdoj.demo.result.CodeMsg;
import com.xdoj.demo.result.Result;
import com.xdoj.demo.service.UserService;
import com.xdoj.demo.util.ValidatorUtil;
import com.xdoj.demo.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/to_login")
    public String toLogin(Model model){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        userService.login(response, loginVo);
        return Result.success("成功");
    }

    @PostMapping("/do_register")
    @ResponseBody
    public Result<String> doRegister(HttpServletResponse response, LoginVo loginVo){
        log.info(loginVo.toString());
        userService.register(response, loginVo);
        return Result.success("注册成功");
    }
    @RequestMapping ("to_register")
    public String toRegister(){
        return "register";
    }
}
