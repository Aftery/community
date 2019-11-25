package top.aftery.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.aftery.community.dto.QuestionUserDTO;
import top.aftery.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@SuppressWarnings("all")
public class IndexController {

    @Autowired
    private QuestionService service;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size) {
        log.info("{/n {}}", "进来了");
        PageInfo<QuestionUserDTO> pageInfo = service.list(page, size);
        model.addAttribute("pageInfo", pageInfo);
        return "Index";
    }

}
