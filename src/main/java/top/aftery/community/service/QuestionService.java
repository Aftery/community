package top.aftery.community.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.exception.CustomizeException;
import top.aftery.community.mapper.QuestionDAO;
import top.aftery.community.mapper.QuestionExtDAO;
import top.aftery.community.mapper.QuestionuserDAO;
import top.aftery.community.model.Question;
import top.aftery.community.model.Questionuser;
import top.aftery.community.model.QuestionuserExample;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author Aftery
 * @Date 2019/11/21 20:52
 * @Version 1.0
 **/
@Slf4j
@Service
@SuppressWarnings("all")
public class QuestionService {

    /** 搜索词里需要转义的正则元字符（不含 |，那是多关键词或的分隔符） */
    private static final Pattern REGEX_META = Pattern.compile("([.\\\\+*?\\[\\](){}|^$])");

    @Autowired
    private QuestionuserDAO questionuserDAO;

    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private QuestionExtDAO extDAO;


    public PageInfo<Questionuser> list(String search,Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Questionuser> list = extDAO.selectSearch(toRegexKeyword(search));
        PageInfo<Questionuser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 把搜索词转成 SQL regexp 的多关键词或匹配模式。
     * 先按空白分词，再转义正则元字符——否则用户搜 "(" 之类残缺正则会让整条 SQL 报错。
     * MySQL REGEXP 不支持 {@code \Q...\E}，所以手工加反斜杠。
     */
    static String toRegexKeyword(String search) {
        if (StrUtil.isBlank(search)) {
            return null;
        }
        return Arrays.stream(search.trim().split("\\s+"))
                .map(word -> REGEX_META.matcher(word).replaceAll("\\\\$1"))
                .collect(Collectors.joining("|"));
    }

    public PageInfo<Questionuser> listUser(Integer userId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        QuestionuserExample questionuserExample = new QuestionuserExample();
        questionuserExample.createCriteria().andUserIdEqualTo(userId);
        List<Questionuser> list = questionuserDAO.selectByExample(questionuserExample);
        PageInfo<Questionuser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public Questionuser getById(Long id) {
        QuestionuserExample questionuserExample = new QuestionuserExample();
        questionuserExample.createCriteria().andIdEqualTo(id);
        List<Questionuser> list = questionuserDAO.selectByExample(questionuserExample);

        if (CollUtil.isNotEmpty(list)) {
            Questionuser questionuser = list.get(0);
            return questionuser;
        }
        return null;
    }

    public void saveOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionDAO.insertSelective(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            int rows = questionDAO.updateByPrimaryKeySelective(question);
            // 无匹配行时返回 0 而不是负数
            if (rows == 0) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    /**
     * 累加阅读数
     *
     * @param id
     */
    public void incView(Long id) {
        Question record = new Question();
        record.setId(id);
        record.setViewCount(1);
        extDAO.incView(record);
    }

    public List<Question> selectRelated(Questionuser questionuser) {
        if (StrUtil.isEmpty(questionuser.getTag())) {
            return Collections.emptyList();
        }
        String replace = StrUtil.replace(questionuser.getTag(), ",", "|");
        Question question = new Question();
        question.setTag(replace);
        question.setId(questionuser.getId());
        List<Question> questions = extDAO.selectRelated(question);
        return questions;
    }
}
