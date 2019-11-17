package top.afery.community.provider;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.afery.community.dto.AccessTockenDTO;
import top.afery.community.dto.GithubUser;

import java.util.HashMap;

/**
 * @Author Aftery
 * @Date 2019/11/17 15:29
 * @Version 1.0
 **/
@Slf4j
@Component
public class GitubProvider {


    public String getAccessTocken(AccessTockenDTO accessTockenDTO) {
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("client_id", accessTockenDTO.getClient_id());
        map.put("client_secret", accessTockenDTO.getClient_secret());
        map.put("code", accessTockenDTO.getCode());
        map.put("redirect_uri", accessTockenDTO.getRedirect_uri());
        map.put("state", accessTockenDTO.getState());
        String post = HttpUtil.post("https://github.com/login/oauth/access_token", map);
        String[] split = post.split("&");
        String accessTocken = split[0].split("=")[1];
        log.info("/n 请求返回参数是：{}", accessTocken);
        return accessTocken;
    }

    public GithubUser getUser(String accessTocken) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("access_token", accessTocken);
        String json = HttpUtil.get("https://api.github.com/user", paramMap);

        GithubUser githubUser = JSONUtil.toBean(json, GithubUser.class);
        return githubUser;
    }


}
