package com.xdoj.demo.controller;

import com.xdoj.demo.domain.User;
import com.xdoj.demo.redis.UserKey;
import com.xdoj.demo.service.UserService;
import com.xdoj.demo.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/to_list")
    public String toLogin(Model model, User user){
        model.addAttribute(user);
        return "goods_list";
    }




}
