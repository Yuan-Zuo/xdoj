package com.xdoj.demo.dao;

import com.xdoj.demo.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") long id);

    @Insert("insert into user(id, nickName)values(#{id}, #{nickName}")
    public void insert(User user);

    @Delete("delete from uer where id = #{id}")
    public void deleteById(@Param("id") long id);


}
