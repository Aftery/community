package top.aftery.community.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Aftery
 * @Date 2019/11/27 21:59
 * @Version 1.0
 **/
@Slf4j
@ControllerAdvice
public class CustomizeExceptionHandler{

    @ExceptionHandler(Exception.class)
     ModelAndView handle(HttpServletRequest request, Model model){
       // HttpStatus status = getStatus(request);
        log.info("过来了{}");
//        if(status.is4xxClientError()){
//            model.addAttribute("messages","你访问的地址不存在，请确认清楚^-^");
//            return  new ModelAndView("error/400");
//        }else{
//            model.addAttribute("messages","哎呀，可能访问人数过多，请稍后再试!");
//        }
        return  new ModelAndView("error/500");
    }

    /*private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(null==statusCode){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  HttpStatus.valueOf(statusCode);

    }*/

}
