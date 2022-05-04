package com.xdoj.demo.dao;

import com.xdoj.demo.domain.QuestionDetail;
import com.xdoj.demo.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdministratorDao {
    @Select("select * from administrator where administrator_id = #{id}")
    public User getByAdministratorId(@Param("id") long id);

    @Insert("insert into administrator(administrator_id)values(#{administrator_id}")
    public void insert(QuestionDetail questionDetail);

    @Delete("delete from administrator where administrator_id = #{id}")
    public void deleteByAdministratorId(@Param("id") long id);
}
