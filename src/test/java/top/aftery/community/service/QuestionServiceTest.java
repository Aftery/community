package top.aftery.community.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * 搜索词转正则的回归测试。
 * 背景：搜索词直接拼进 SQL 的 regexp，残缺正则（如 "("）会让整条查询报错。
 */
public class QuestionServiceTest {

    @Test
    public void 空格分词转或匹配() {
        assertEquals("java|spring", QuestionService.toRegexKeyword("java spring"));
        assertEquals("a|b|c", QuestionService.toRegexKeyword("  a   b  c  "));
    }

    @Test
    public void 转义正则元字符() {
        assertEquals("a\\(b", QuestionService.toRegexKeyword("a(b"));
        assertEquals("c\\+\\+", QuestionService.toRegexKeyword("c++"));
        assertEquals("1\\.5", QuestionService.toRegexKeyword("1.5"));
        assertEquals("\\[x\\]", QuestionService.toRegexKeyword("[x]"));
        // 反斜杠自身也要被转义，否则会吃掉后面的元字符
        assertEquals("a\\\\b", QuestionService.toRegexKeyword("a\\b"));
    }

    @Test
    public void 空搜索返回null() {
        assertNull(QuestionService.toRegexKeyword(null));
        assertNull(QuestionService.toRegexKeyword(""));
        assertNull(QuestionService.toRegexKeyword("   "));
    }
}
