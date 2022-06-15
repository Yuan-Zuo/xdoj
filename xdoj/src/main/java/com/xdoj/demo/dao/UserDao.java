package com.xdoj.demo.dao;

import com.xdoj.demo.domain.User;
import com.xdoj.demo.vo.LoginVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    @Select("select * from user where user_id = #{id}")
    public User getById(@Param("id") long id);

    @Insert("insert into user(user_id, nickName)values(#{id}, #{nickName}")
    public void insert(User user);

    @Delete("delete from uer where user_id = #{id}")
    public void deleteById(@Param("id") long id);

    public void updateById(User user);

    public List<User> queryUserList();

    @Insert("insert into user (user_id, password) values(#{mobile}, #{password})")
    boolean registerUser( LoginVo loginVo);
}
