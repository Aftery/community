package top.aftery.community.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.aftery.community.mapper.QuestionMapper;
import top.aftery.community.model.Question;
import top.aftery.community.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Aftery
 * @Date 2019/11/18 22:32
 * @Version 1.0
 **/
@Slf4j
@Controller
@SuppressWarnings("all")
public class PublishController {


    @Autowired
    private QuestionMapper mapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest request, Model model) {
        log.info("\n tostring{}", question);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("des",question.getDescription());
        model.addAttribute("tag",question.getTag());

        if (null == question.getTitle() || ""==question.getTitle()) {
            model.addAttribute("error","标题不能为空");
            return  "publish";
        }
        if (null == question.getDescription() || ""==question.getDescription()) {
            model.addAttribute("error","问题补充不能为空");
            return  "publish";
        }
        if (null == question.getTag() || ""==question.getTag()) {
            model.addAttribute("error","标签不能为空");
            return  "publish";
        }


        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        mapper.create(question);
        return "redirect:/";
    }

}
