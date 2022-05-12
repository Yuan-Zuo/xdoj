package com.xdoj.demo.service;

import com.xdoj.demo.controller.LoginController;
import com.xdoj.demo.dao.UserDao;
import com.xdoj.demo.domain.User;
import com.xdoj.demo.exception.GlobalException;
import com.xdoj.demo.redis.RedisService;
import com.xdoj.demo.redis.UserKey;
import com.xdoj.demo.result.CodeMsg;
import com.xdoj.demo.util.UUIDUtil;
import com.xdoj.demo.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    public static final String COOKIE_NAME_TOKEN = "token";
    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    public User getById(long id) {
        return userDao.getById(id);
    }

    public void login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //用户号是否存在
        User user = getById(Long.parseLong(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_ERROR);
        }
        String dbPassword = user.getPassword();
        if(!dbPassword.equals(password)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        addCookie(response, user);
    }

//    public 方法第一步要进行参数校验
//    更新cookie时间 : 设置一个新cookie
    public User getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        User user = redisService.get(UserKey.token, token, User.class);
//        延长有效期
        if(user != null){
            addCookie(response, user);
        }
        return user;
    }

    private void addCookie(HttpServletResponse response, User user){
        //登录成功
        //生成cookie
        String token = UUIDUtil.uuid();
        log.info(token);
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        //设置cookie 有效期 和 redis 过期时间一致
        cookie.setMaxAge(UserKey.token.expireSecond());
        //显示cookie生命周期
//        因此cookie.setPath("/");之后，可以在webapp文件夹下的所有应用共享cookie，
//        而cookie.setPath("/webapp_b/")是指设置的cookie只能在webapp_b应用下的获得，
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
