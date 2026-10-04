package top.aftery.community.controller;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.aftery.community.dto.AccessTokenDTO;
import top.aftery.community.dto.GithubUser;
import top.aftery.community.model.User;
import top.aftery.community.provider.GithubProvider;
import top.aftery.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @Author Aftery
 * @Date 2019/11/17 15:01
 * @Version 1.0
 **/
@Slf4j
@SuppressWarnings("all")
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider provider;

    @Value("${github.clienid}")
    private String clientId;

    @Value("${github.client_secret}")
    private String clientSecret;

    @Value("${github.redirect_uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpSession session) throws UnsupportedEncodingException {
        String state = UUID.randomUUID().toString().replace("-","");
        session.setAttribute("oauth_state", state);
        String url = "https://github.com/login/oauth/authorize?client_id=" + clientId
                + "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8")
                + "&scope=user&state=" + state;
        return "redirect:"+url;
    }


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code", required = false) String code,
                           @RequestParam(name = "error", required = false) String error,
                           @RequestParam(name = "state", required = false) String state,
                           HttpServletRequest request, HttpServletResponse response) {
        // 用户在 GitHub 授权页点了拒绝，只会带 error 回来
        if (StrUtil.isNotEmpty(error) || StrUtil.isEmpty(code)) {
            return "redirect:/";
        }
        String expected = (String) request.getSession().getAttribute("oauth_state");
        if (expected == null || !expected.equals(state)) {
            return "redirect:/";
        }
        request.getSession().removeAttribute("oauth_state");
        AccessTokenDTO accessTockenDTO = new AccessTokenDTO();
        accessTockenDTO.setClient_id(clientId);
        accessTockenDTO.setClient_secret(clientSecret);
        accessTockenDTO.setCode(code);
        accessTockenDTO.setRedirect_uri(redirectUri);
        accessTockenDTO.setState(state);
        String accessTocken = provider.getAccessTocken(accessTockenDTO);
        if (StrUtil.isEmpty(accessTocken)) {
            return "redirect:/";
        }
        GithubUser githubUser = provider.getUser(accessTocken);
        log.info("\n {}", githubUser);
        if (githubUser != null) {
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.saveOrUpdate(user);
            User saved = userService.getByAccountId(user.getAccountId());
            // 新用户 token 在 saveOrUpdate 里生成，老用户保留原 token，统一从 DB 读
            response.addCookie(buildTokenCookie(saved.getToken(), -1));
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Object user = request.getSession().getAttribute("user");
        if (user != null) {
            request.getSession().removeAttribute("user");
            // maxAge 必须在 addCookie 之前设置：addCookie 会立刻把 cookie 序列化写入响应头
            response.addCookie(buildTokenCookie("", 0));
        }
        return "redirect:/";
    }

    private Cookie buildTokenCookie(String value, int maxAge) {
        Cookie cookie = new Cookie("token", value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        return cookie;
    }

}

