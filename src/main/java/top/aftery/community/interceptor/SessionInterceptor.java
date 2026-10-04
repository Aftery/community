package top.aftery.community.interceptor;

import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
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
        // 已有登录态直接放行，避免每次请求都查库
        if (request.getSession().getAttribute("user") != null) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (cn.hutool.core.util.StrUtil.isBlank(token)) {
                        continue;
                    }
                    UserExample example = new UserExample();
                    example.createCriteria().andTokenEqualTo(token);
                    List<User> users = mapper.selectByExample(example);
                    if (CollUtil.isNotEmpty(users)) {
                        User user = users.get(0);
                        request.getSession().setAttribute("user", user);
                        Long unreadCount = notificationService.unreadCount(user.getId());
                        request.getSession().setAttribute("unreadCount", unreadCount);
                    } else {
                        // token 失效但 cookie 还在，清理残留 session
                        request.getSession().removeAttribute("user");
                        request.getSession().removeAttribute("unreadCount");
                    }
                    break; // 找到 token 就停，不用继续遍历 cookie 数组
                }
            }
        }
        return true;
    }

}
