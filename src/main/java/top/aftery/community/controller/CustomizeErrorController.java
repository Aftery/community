package top.aftery.community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Aftery
 * @Date 2019/11/28 22:02
 * @Version 1.0
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping("/error")
public class CustomizeErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, Model model) {
        HttpStatus status = getStatus(request);
        if (status.is4xxClientError()) {
            model.addAttribute("message", "你这个请求找不到了,要不然改改再试试？？？");
            return new ModelAndView("/error/400");
        } else {
            model.addAttribute("message", "服务器冒烟了，要不然你等一下再试试！！！");
            return new ModelAndView("/error/500");
        }
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
