package top.aftery.community.interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.aftery.community.mapper.UserDAO;
import top.aftery.community.model.User;
import top.aftery.community.model.UserExample;
import top.aftery.community.service.NotificationService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author Aftery
 * @Date 2019/11/24 14:37
 * @Version 1.0
 **/
@Service
@SuppressWarnings("all")
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserDAO mapper;

    @Autowired
    private NotificationService notificationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    UserExample example = new UserExample();
                    example.createCriteria().andTokenEqualTo(token);
                    List<User> users = mapper.selectByExample(example);
                    boolean notEmpty = CollUtil.isNotEmpty(users);
                    if (notEmpty) {
                        User user = users.get(0);
                        if (user != null) {
                            request.getSession().setAttribute("user", user);
                            Long unreadCount = notificationService.unreadCount(user.getId());
                            request.getSession().setAttribute("unreadCount", unreadCount);
                        }
                    }

                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
