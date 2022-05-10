package com.xdoj.demo.service;

import com.xdoj.demo.dao.UserDao;
import com.xdoj.demo.domain.User;
import com.xdoj.demo.exception.GlobalException;
import com.xdoj.demo.result.CodeMsg;
import com.xdoj.demo.util.UUIDUtil;
import com.xdoj.demo.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(long id) {
        return userDao.getById(id);
    }

    public void login(LoginVo loginVo) {
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
        //登录成功
        //生成cookie
        String token = UUIDUtil.uuid();
    }

    public User test(){
        List<User> list = userDao.queryUserList();
        return list.get(0);
    }

}
