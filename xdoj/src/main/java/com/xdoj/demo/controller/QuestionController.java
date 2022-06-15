package com.xdoj.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdoj.demo.domain.CodeDTO;
import com.xdoj.demo.domain.Question;
import com.xdoj.demo.domain.ResultDTO;
import com.xdoj.demo.domain.User;
import com.xdoj.demo.result.Result;
import com.xdoj.demo.service.QuestionService;
import com.xdoj.demo.vo.QuestionListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {
    private static final String judgeUrl = "";
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
        RestTemplate restTemplate = new RestTemplate();
        ResultDTO judgeResult = restTemplate.postForObject(judgeUrl, new CodeDTO(content, questionId), ResultDTO.class);
        String result = "回答正确！";
        if (judgeResult == null) {
            return Result.success("你的代码成功使判题服务器出错！");
        }
        if (judgeResult.getCode() != 200){
            result = judgeResult.getMessage();
        }


        return Result.success(result);
    }

    @RequestMapping("/add_question")
    public Result<String> addQuestion(Question question){
        questionService.addQuestion(question);
        return Result.success("成功");
    }
}
