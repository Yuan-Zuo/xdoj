package com.xdoj.demo.service;

import com.xdoj.demo.controller.LoginController;
import com.xdoj.demo.dao.QuestionDao;
import com.xdoj.demo.domain.Question;
import com.xdoj.demo.exception.GlobalException;
import com.xdoj.demo.result.CodeMsg;
import com.xdoj.demo.vo.QuestionListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    QuestionDao questionDao;

    public void addQuestion(Question question){
        if(question == null)throw new GlobalException(CodeMsg.QUESTION_EMPTY);
        questionDao.addQuestion(question);
    }

    public Question getQuestion(long id){
        Question question = questionDao.getQuestion(id);
        return  question;
    }

    public List<QuestionListVo> getQuestionListVo() {
        List<QuestionListVo> questionListVo = questionDao.getQuestionListVo();
        return questionListVo;
    }
}
