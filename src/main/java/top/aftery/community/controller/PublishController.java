package top.aftery.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Aftery
 * @Date 2019/11/18 22:32
 * @Version 1.0
 **/
@Controller
public class PublishController {


    @GetMapping("/publish")
    public String publish(){
        return  "publish";
    }
}
