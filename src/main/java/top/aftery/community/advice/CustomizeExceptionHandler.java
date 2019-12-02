package top.aftery.community.advice;

import cn.hutool.json.JSONUtil;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.regexp.internal.REUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.aftery.community.dto.ResultDTO;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    ModelAndView handle(HttpServletRequest request, Exception e, Model model, HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO = null;
            if (e instanceof CustomizeException) {
                resultDTO = resultDTO.errorOf(((CustomizeException) e));
            } else {
                resultDTO = resultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            PrintWriter writer = null;
            try {
                response.setCharacterEncoding("utf-8");
                response.setStatus(200);
                response.setContentType("application/json");
                writer = response.getWriter();
                writer.write(JSONUtil.toJsonStr(resultDTO));
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        } else {
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
                return new ModelAndView("error/400");
            } else {
                model.addAttribute("message", "哎呀，可能访问人数过多，请稍后再试!");
                return new ModelAndView("error/500");
            }
        }

    }


}
