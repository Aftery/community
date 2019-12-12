package top.aftery.community.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.aftery.community.dto.CommentDto;
import top.aftery.community.enums.CommenTypeEnum;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.exception.CustomizeException;
import top.aftery.community.mapper.*;
import top.aftery.community.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {


    @Autowired
    private CommentDAO commentDao;


    @Autowired
    private QuestionDAO questionDao;


    @Autowired
    private QuestionExtDAO questionExtDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CommentExtDAO commentExtDAO;


    @Transactional
    public void insert(Comment comment) {
        if (null == comment.getParentId() || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (null == comment.getType() || !CommenTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommenTypeEnum.COMMENT.getType()) {
            //回复评论

            Comment dbComment = commentDao.selectByPrimaryKey(comment.getParentId());
            if (null == dbComment) {
                throw new CustomizeException(CustomizeErrorCode.COMMINT_NOT_FOUND);
            }
            commentDao.insertSelective(comment);
            //增加评论数
            Comment parentComment=new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtDAO.incView(parentComment);
        } else {
            //回复问题
            Question question = questionDao.selectByPrimaryKey(comment.getParentId());
            if (null == question) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentDao.insertSelective(comment);
            Question record = new Question();
            question.setCommentCount(1);
            questionExtDAO.incView(question);
        }
    }

    public List<CommentDto> listByQuestionId(Long id, CommenTypeEnum commenTypeEnum) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(commenTypeEnum.getType());
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentDao.selectByExample(example);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }

        Set<Integer> commentatoes = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userids = new ArrayList<>();
        userids.addAll(commentatoes);

        //获取用户转换为map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userids);
        List<User> users = userDAO.selectByExample(userExample);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换comment为commentdto
        List<CommentDto> commentDAOS = comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUser(userMap.get(comment.getCommentator()));
            return commentDto;
        }).collect(Collectors.toList());

        return commentDAOS;
    }
}
