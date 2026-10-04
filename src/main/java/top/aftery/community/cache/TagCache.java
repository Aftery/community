package top.aftery.community.cache;

import top.aftery.community.dto.TagDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagCache {

    private static final List<TagDTO> TAGS = build();
    private static final Set<String> ALL_TAGS = new HashSet<>();

    static {
        TAGS.forEach(dto -> ALL_TAGS.addAll(dto.getTags()));
    }

    public static List<TagDTO> get() {
        return TAGS;
    }

    public static String filterInvalid(String tags) {
        if (tags == null || tags.trim().isEmpty()) {
            return "";
        }
        String[] split = tags.split(",");
        StringBuilder sb = new StringBuilder();
        for (String t : split) {
            if (!ALL_TAGS.contains(t.trim())) {
                if (sb.length() > 0) sb.append(",");
                sb.append(t.trim());
            }
        }
        return sb.toString();
    }

    private static List<TagDTO> build() {
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList(
                "javascript", "php", "css", "html", "html5", "java", "node.js", "python",
                "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#",
                "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript",
                "actionscript", "rust", "erlang", "perl"));

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList(
                "laravel", "spring", "express", "django", "flask", "yii",
                "ruby-on-rails", "tornado", "koa", "struts"));

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList(
                "linux", "nginx", "docker", "apache", "ubuntu", "centos", "tomcat",
                "负载均衡", "unix", "hadoop", "windows-server"));

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList(
                "mysql", "redis", "mongodb", "sql", "oracle", "memcached",
                "sqlserver", "postgresql", "sqlite"));

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList(
                "git", "github", "visual-studio-code", "vim", "sublime-text",
                "xcode", "intellij-idea", "eclipse", "maven", "ide", "svn",
                "visual-studio", "emacs", "textmate", "hg"));

        return Arrays.asList(program, framework, server, db, tool);
    }
}
