package com.xdoj.demo.dao;

import com.xdoj.demo.domain.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface QuestionDao {

    @Select("select * from question where question_id = #{id}")
    public Question getQuestion(@Param("id")long id);

    @Insert("insert into question (question_id, question_content, difficult_level) values(#{questionId}, #{questionContent}, #{difficultLevel})")
    public void addQuestion(Question question);
}
