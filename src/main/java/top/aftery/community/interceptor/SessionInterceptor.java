package top.aftery.community.interceptor;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.aftery.community.model.User;
import top.aftery.community.service.NotificationService;
import top.aftery.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Aftery
 * @Date 2019-11-24 14:37
 * @Version 1.0
 **/
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

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
                    if (StrUtil.isBlank(token)) {
                        continue;
                    }
                    User user = userService.getByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        Long unreadCount = notificationService.unreadCount(user.getId());
                        request.getSession().setAttribute("unreadCount", unreadCount);
                    } else {
                        // token 失效但 session 还有残留（如服务重启后旧 token 被清理场景的反向情况）
                        request.getSession().removeAttribute("user");
                        request.getSession().removeAttribute("unreadCount");
                    }
                    break;
                }
            }
        }
        return true;
    }
}
