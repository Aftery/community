package top.afery.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.afery.community.dto.AccessTockenDTO;
import top.afery.community.dto.GithubUser;
import top.afery.community.provider.GitubProvider;

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

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam("state") String state) {
        AccessTockenDTO accessTockenDTO = new AccessTockenDTO();
        accessTockenDTO.setClient_id(clienid);
        accessTockenDTO.setClient_secret(clientSecret);
        accessTockenDTO.setCode(code);
        accessTockenDTO.setRedirect_uri(redirectUri);
        accessTockenDTO.setState(state);
        String accessTocken = provider.getAccessTocken(accessTockenDTO);
        GithubUser user = provider.getUser(accessTocken);
        log.info("\n {}", user);

        return "index";
    }

}

