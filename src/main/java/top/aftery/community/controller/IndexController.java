package top.aftery.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.aftery.community.dto.QuestionDTO;
import top.aftery.community.mapper.UserMapper;
import top.aftery.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@SuppressWarnings("all")
public class IndexController {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private QuestionService service;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size) {
        log.info("{/n {}}", "进来了");
        PageInfo<QuestionDTO> list = service.list(page, size);

        model.addAttribute("questions", list);
        return "Index";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageInfo<QuestionDTO> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size) {
        log.info("{/n {}}", "进来了");
        PageInfo<QuestionDTO> list = service.list(page, size);
        return list;
    }

}
