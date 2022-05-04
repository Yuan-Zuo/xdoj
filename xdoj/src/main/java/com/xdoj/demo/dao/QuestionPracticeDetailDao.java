package com.xdoj.demo.dao;

import com.xdoj.demo.domain.QuestionPracticeDetail;
import com.xdoj.demo.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface QuestionPracticeDetailDao {
    @Select("select * from question_practice_detail where user_id = #{id}")
    public User getByUserId(@Param("id") long id);

    @Insert("insert into question_practice_detail(user_id, question_id, result)values(#{userId},#{questionId},#{result}")
    public void insert(QuestionPracticeDetail questionPracticeDetail);

    @Delete("delete from question_practice_detail where user_id = #{id}")
    public void deleteByUserId(@Param("id") long id);
}
