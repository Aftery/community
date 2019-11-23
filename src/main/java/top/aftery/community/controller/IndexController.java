package top.aftery.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.aftery.community.dto.QuestionDTO;
import top.aftery.community.mapper.UserMapper;
import top.aftery.community.model.User;
import top.aftery.community.service.QuestionService;
import javax.servlet.http.Cookie;
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
    public String index(HttpServletRequest request, Model model) {
        log.info("{/n {}}", "进来了");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = mapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        }
        List<QuestionDTO> questionDTOList = service.list();
        questionDTOList.forEach(s -> log.info("\n toString:{}", s));
        model.addAttribute("questions", questionDTOList);
        return "Index";
    }

}
