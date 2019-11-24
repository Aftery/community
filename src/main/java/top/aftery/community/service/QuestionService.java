package top.aftery.community.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.aftery.community.dto.QuestionDTO;
import top.aftery.community.mapper.QuestionMapper;
import top.aftery.community.mapper.UserMapper;
import top.aftery.community.model.Question;
import top.aftery.community.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Aftery
 * @Date 2019/11/21 20:52
 * @Version 1.0
 **/
@Service
@SuppressWarnings("all")
public class QuestionService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private QuestionMapper questionMapper;


    public PageInfo<QuestionDTO> list(Integer page, Integer size) {
        PageInfo<QuestionDTO> pageInfo=new PageInfo<>();

        List<Question> questions = questionMapper.list(page,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = mapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageInfo.setList(questionDTOList);
        return pageInfo;
    }

    public List<QuestionDTO> listUser(Integer userId) {
        List<Question> questions = questionMapper.listUser(userId);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = mapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public QuestionDTO getById(Integer id) {
        Question question=questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = mapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return  questionDTO;
    }
}
