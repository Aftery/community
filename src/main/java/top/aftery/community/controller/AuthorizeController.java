package top.aftery.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.aftery.community.dto.AccessTockenDTO;
import top.aftery.community.dto.GithubUser;
import top.aftery.community.model.User;
import top.aftery.community.provider.GitubProvider;
import top.aftery.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private GitubProvider provider;

    @Value("${github.clienid}")
    private String clienid;

    @Value("${github.client_secret}")
    private String clientSecret;

    @Value("${github.redirect_uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam("state") String state, HttpServletRequest request, HttpServletResponse response) {
        AccessTockenDTO accessTockenDTO = new AccessTockenDTO();
        accessTockenDTO.setClient_id(clienid);
        accessTockenDTO.setClient_secret(clientSecret);
        accessTockenDTO.setCode(code);
        accessTockenDTO.setRedirect_uri(redirectUri);
        accessTockenDTO.setState(state);
        String accessTocken = provider.getAccessTocken(accessTockenDTO);
        GithubUser githubUser = provider.getUser(accessTocken);
        log.info("\n {}", githubUser);
        if (githubUser != null) {
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.saveOrUpdate(user);
            response.addCookie(new Cookie("token", user.getToken()));
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
            Cookie cookie = new Cookie("token", "");
            response.addCookie(cookie);
            cookie.setMaxAge(0);
        }
        return "redirect:/";
    }

}

