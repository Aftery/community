package top.aftery.community.advice;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
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
        if (StrUtil.startWithIgnoreCase(contentType, "application/json")) {
            ResultDTO resultDTO = e instanceof CustomizeException
                    ? ResultDTO.errorOf((CustomizeException) e)
                    : ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            if (!(e instanceof CustomizeException)) {
                log.error("非预期异常", e);
            }
            try {
                response.setCharacterEncoding("utf-8");
                response.setStatus(200);
                response.setContentType("application/json");
                response.getWriter().write(JSONUtil.toJsonStr(resultDTO));
            } catch (IOException ex) {
                log.error("写响应失败", ex);
            }
            return null;
        } else {
            if (e instanceof CustomizeException) {
                return new ModelAndView("error/400", "message", e.getMessage());
            } else {
                log.error("非预期异常", e);
                return new ModelAndView("error/500", "message", "哎呀，可能访问人数过多，请稍后再试!");
            }
        }

    }


}
