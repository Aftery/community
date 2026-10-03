package top.aftery.community;

import org.junit.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 配置装载的回归测试。
 * 背景：账号密码放在 application-local.yml（不入库），由 application.yml 的
 * {@code spring.profiles.active: local} 加载。文件名或位置一旦写错，应用会静默地
 * 拿不到连接信息，这个测试用来兜住那条链路。
 */
public class ConfigProfileTest {

    @Test
    public void 本地配置文件被加载并覆盖公共配置() {
        // 不以 CommunityApplication 为源，避开 @MapperScan 对 SqlSessionFactory 的依赖
        ConfigurableEnvironment env;
        try (ConfigurableApplicationContext ctx = new SpringApplicationBuilder()
                .web(WebApplicationType.NONE)
                .sources(ConfigProfileTest.class)
                .run()) {
            env = ctx.getEnvironment();
        }

        assertEquals("local", env.getActiveProfiles()[0]);

        // 来自 application-local.yml
        assertNotNull("application-local.yml 未被加载", env.getProperty("spring.datasource.username"));
        assertNotNull("application-local.yml 未被加载", env.getProperty("spring.datasource.password"));

        // 仍来自 application.yml，验证两个文件是合并而非覆盖
        assertEquals("jdbc:mysql:///community?useUnicode=true&characterEncoding=utf-8&useSSL=false",
                env.getProperty("spring.datasource.url"));
    }
}