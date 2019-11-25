package top.aftery.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.aftery.community.dto.QuestionUserDTO;
import top.aftery.community.model.User;
import top.aftery.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Aftery
 * @Date 2019/11/24 10:56
 * @Version 1.0
 **/
@Slf4j
@Controller
@SuppressWarnings("all")
public class ProfileController {


    @Autowired
    private QuestionService service;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(value = "action") String action, Model model, HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size) {

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

        PageInfo<QuestionUserDTO> pageInfo = service.listUser(user.getId(), page, size);
        model.addAttribute("pageInfo", pageInfo);
        return "profile";
    }

}
