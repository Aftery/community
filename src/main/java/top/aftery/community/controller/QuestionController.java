package top.aftery.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.aftery.community.dto.QuestionUserDTO;
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
        model.addAttribute("question", questionUserDTO);
        return "question";
    }

}
