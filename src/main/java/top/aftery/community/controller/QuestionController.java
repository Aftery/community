package top.aftery.community.controller;

import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.aftery.community.dto.QuestionUserDTO;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.exception.CustomizeException;
import top.aftery.community.service.QuestionService;

/**
 * @Author Aftery
 * @Date 2019/11/24 16:05
 * @Version 1.0
 **/
@Controller
@SuppressWarnings("all")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(value = "id") Integer id, Model model) {
        QuestionUserDTO questionUserDTO = service.getById(id);
        if(null==questionUserDTO){
            throw new  CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        model.addAttribute("question", questionUserDTO);
        return "question";
    }

}
