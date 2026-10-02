package top.aftery.community.provider;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * GitHub OAuth 响应解析的回归测试。
 * 背景：原实现用 split("&")[0].split("=")[1] 取值，GitHub 返回错误时
 * （error=bad_verification_code&error_description=...）会把错误码当成 access_token 返回，
 * 导致登录静默失败；响应为空时还会抛 ArrayIndexOutOfBoundsException。
 */
public class GitubProviderTest {

    @Test
    public void 解析成功响应() {
        assertEquals("abc123", GitubProvider.parseAccessToken(
                "access_token=abc123&scope=&token_type=bearer"));
    }

    @Test
    public void 错误响应不能被当成token() {
        assertNull(GitubProvider.parseAccessToken(
                "error=bad_verification_code&error_description=The+code+passed+is+incorrect"
                        + "&error_uri=https%3A%2F%2Fdocs.github.com"));
    }

    @Test
    public void 空响应和空token都返回null() {
        assertNull(GitubProvider.parseAccessToken(""));
        assertNull(GitubProvider.parseAccessToken("access_token=&scope="));
    }
}
