package top.aftery.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.aftery.community.dto.CommentDto;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.exception.CustomizeException;
import top.aftery.community.model.Comment;
import top.aftery.community.model.Questionuser;
import top.aftery.community.service.CommentService;
import top.aftery.community.service.QuestionService;

import java.util.List;

/**
 * @Author Aftery
 * @Date 2019/11/24 16:05
 * @Version 1.0
 **/
@Slf4j
@Controller
@SuppressWarnings("all")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(value = "id") Long id, Model model) {
        Questionuser questionuser = service.getById(id);
        if (null == questionuser) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        List<CommentDto> comments =commentService.listByQuestionId(id);
        //累计阅读列表
        service.incView(id);
        log.info("\n questiobnuser:{}", questionuser);
        model.addAttribute("question", questionuser);
        model.addAttribute("comments", comments);
        return "question";
    }

}
