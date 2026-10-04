package top.aftery.community.util;


import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class MarkdownUtil {
    private  static  final Parser PARSER= Parser.builder().build();

    private static  final HtmlRenderer RENDERER= HtmlRenderer.builder()
            .build();

    private static final Safelist SAFELIST = Safelist.relaxed().addAttributes(":all", "class");


    public  static  String reader(String markdown) {
        if (markdown == null) {
            return ""
                    ;
        }
        String html = RENDERER.render(PARSER.parse(markdown));
        return Jsoup.clean(html,SAFELIST);

    }
}
