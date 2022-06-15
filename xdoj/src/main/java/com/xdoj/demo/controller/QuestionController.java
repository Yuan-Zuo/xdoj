package com.xdoj.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdoj.demo.domain.Question;
import com.xdoj.demo.domain.User;
import com.xdoj.demo.result.Result;
import com.xdoj.demo.service.QuestionService;
import com.xdoj.demo.vo.QuestionListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    public Result<List<QuestionListVo>> showQuestion(){
        List<QuestionListVo> questionListVo = questionService.getQuestionListVo();
        return Result.success(questionListVo);
    }
    @RequestMapping("/get_question")
    public Result<Question> getQuestion(long questionId, User user){
        Question question = questionService.getQuestion(questionId);
        return Result.success(question);
    }

    @RequestMapping("/do_question")
    public Result<String> doQuestion(long questionId, String content, User user){
        //接口
        boolean jud = true;
        String result = "回答错误";
        if(jud){
            result = "回答正确";
        }
        return Result.success(result);
    }

    @RequestMapping("/add_question")
    public Result<String> addQuestion(Question question){
        questionService.addQuestion(question);
        return Result.success("成功");
    }
}
