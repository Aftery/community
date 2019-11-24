package top.aftery.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.aftery.community.dto.QuestionDTO;
import top.aftery.community.model.User;
import top.aftery.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Aftery
 * @Date 2019/11/24 10:56
 * @Version 1.0
 **/
@Controller
@SuppressWarnings("all")
public class ProfileController {


    @Autowired
    private QuestionService service;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(value = "action") String action, Model model, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        List<QuestionDTO> questionDTOList = service.listUser(user.getId());
        model.addAttribute("questions", questionDTOList);
        return "profile";
    }


}
