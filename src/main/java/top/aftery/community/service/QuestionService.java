package top.aftery.community.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.exception.CustomizeException;
import top.aftery.community.mapper.QuestionDAO;
import top.aftery.community.mapper.QuestionuserDAO;
import top.aftery.community.model.Question;
import top.aftery.community.model.Questionuser;
import top.aftery.community.model.QuestionuserExample;

import java.util.List;

/**
 * @Author Aftery
 * @Date 2019/11/21 20:52
 * @Version 1.0
 **/
@Slf4j
@Service
@SuppressWarnings("all")
public class QuestionService {



    @Autowired
    private QuestionuserDAO questionuserDAO;

    @Autowired
    private QuestionDAO questionDAO;


    public PageInfo<Questionuser> list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Questionuser> list = questionuserDAO.selectByExample(new QuestionuserExample());
        PageInfo<Questionuser> pageInfo = new PageInfo<Questionuser>(list);
        return pageInfo;
    }

    public PageInfo<Questionuser> listUser(Integer userId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        QuestionuserExample questionuserExample = new QuestionuserExample();
        questionuserExample.createCriteria().andUserIdEqualTo(userId);
        List<Questionuser> list = questionuserDAO.selectByExample(questionuserExample);
        PageInfo<Questionuser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public Questionuser getById(Integer id) {
        QuestionuserExample questionuserExample = new QuestionuserExample();;
        questionuserExample.createCriteria().andIdEqualTo(id);
        List<Questionuser> list = questionuserDAO.selectByExample(questionuserExample);
        if(CollUtil.isNotEmpty(list)){
            Questionuser questionuser = list.get(0);
            return questionuser;
        }
        return null;
    }

    public void saveOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionDAO.insert(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            int sun = questionDAO.updateByPrimaryKey(question);
            if (sun < 0) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
