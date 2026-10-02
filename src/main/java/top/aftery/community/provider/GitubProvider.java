package top.aftery.community.provider;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.aftery.community.dto.AccessTockenDTO;
import top.aftery.community.dto.GithubUser;

import java.util.HashMap;

/**
 * @Author Aftery
 * @Date 2019/11/17 15:29
 * @Version 1.0
 **/
@Slf4j
@Component
@SuppressWarnings("all")
public class GitubProvider {

    public String getAccessTocken(AccessTockenDTO accessTockenDTO) {
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("client_id", accessTockenDTO.getClient_id());
        map.put("client_secret", accessTockenDTO.getClient_secret());
        map.put("code", accessTockenDTO.getCode());
        map.put("redirect_uri", accessTockenDTO.getRedirect_uri());
        map.put("state", accessTockenDTO.getState());
        // hutool 5.0.3 无法设置请求头，这里只能按 GitHub 默认的 urlencoded 格式解析。
        // 原实现直接取第一个 "=" 后的值，失败时会把错误码当成 token 返回。
        String post = HttpUtil.post("https://github.com/login/oauth/access_token", map);
        String accessTocken = parseAccessToken(post);
        if (accessTocken == null) {
            log.error("github 换取 access_token 失败: {}", post);
            return null;
        }
        log.info("\n 请求返回参数是：{}", accessTocken);
        return accessTocken;
    }

    /**
     * 从 GitHub 的 urlencoded 响应中取出 access_token；失败响应（error=xxx）返回 null。
     */
    static String parseAccessToken(String responseBody) {
        for (String pair : responseBody.split("&")) {
            if (pair.startsWith("access_token=")) {
                String token = pair.substring("access_token=".length());
                return token.isEmpty() ? null : token;
            }
        }
        return null;
    }

    public GithubUser getUser(String accessTocken) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("access_token", accessTocken);
        String json = HttpUtil.get("https://api.github.com/user", paramMap);

        // 凭据失效时 GitHub 返回 {"message":"Bad credentials"}，没有 id，直接 toBean 会造出一个全空的 User
        if (!JSONUtil.parseObj(json).containsKey("id")) {
            log.error("github 获取用户信息失败: {}", json);
            return null;
        }
        GithubUser githubUser = JSONUtil.toBean(json, GithubUser.class);
        return githubUser;
    }


}
