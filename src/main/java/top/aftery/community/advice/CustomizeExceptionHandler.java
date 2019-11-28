package top.aftery.community.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.aftery.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
/*
 *
 * @Author Aftery
 * @Date 2019/11/27 21:59
 * @Version 1.0
 **/

@Slf4j
@ControllerAdvice
public class CustomizeExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    ModelAndView handle(HttpServletRequest request, Exception e, Model model) {
        if (e instanceof CustomizeException) {
            model.addAttribute("message", e.getMessage());
            return new ModelAndView("error/400");
        } else {
            model.addAttribute("message", "哎呀，可能访问人数过多，请稍后再试!");
            return new ModelAndView("error/500");
        }
    }


}
