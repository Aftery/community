package top.aftery.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.aftery.community.dto.QuestionUserDTO;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.exception.CustomizeException;
import top.aftery.community.mapper.QuestionMapper;
import top.aftery.community.mapper.QuestionUserDTOMapper;
import top.aftery.community.model.Question;

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
    private QuestionUserDTOMapper questionUserMapper;

    @Autowired
    private  QuestionMapper questionMapper;


    public PageInfo<QuestionUserDTO> list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionUserDTO> list = questionUserMapper.list();
        PageInfo<QuestionUserDTO> pageInfo1 = new PageInfo<QuestionUserDTO>(list);
        return pageInfo1;
    }

    public PageInfo<QuestionUserDTO> listUser(Integer userId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionUserDTO> questionUserDTOS = questionUserMapper.listUser(userId);
        PageInfo<QuestionUserDTO> pageInfo = new PageInfo<>(questionUserDTOS);
        return pageInfo;
    }

    public QuestionUserDTO getById(Integer id) {
        QuestionUserDTO questionUserDTO = questionUserMapper.getById(id);
        return questionUserDTO;
    }

    public void saveOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.save(question);
        }else{
            question.setGmtModified(System.currentTimeMillis());
            Integer  sun= questionMapper.saveOrUpdate(question);
            if(sun<0){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
