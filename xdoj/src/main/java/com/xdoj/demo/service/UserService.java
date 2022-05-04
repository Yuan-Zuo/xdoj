package com.xdoj.demo.service;

import com.xdoj.demo.dao.UserDao;
import com.xdoj.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(long id) {
        return userDao.getById(1);
    }

    public Boolean insert(){

        User test2 = new User();
        test2.setId(2);
        test2.setNickName("222");
        userDao.insert(test2);

        return true;
    }

}
