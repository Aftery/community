package top.aftery.community.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.aftery.community.enums.CommenTypeEnum;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.exception.CustomizeException;
import top.aftery.community.mapper.CommentDAO;
import top.aftery.community.mapper.QuestionDAO;
import top.aftery.community.mapper.QuestionExtDAO;
import top.aftery.community.mapper.QuestionuserDAO;
import top.aftery.community.model.Comment;
import top.aftery.community.model.Question;
@Slf4j
@Service
public class CommentService {


    @Autowired
    private CommentDAO commentDao;


    @Autowired
    private QuestionDAO questionDao;


    @Autowired
    private QuestionExtDAO questionExtDAO;


    @Transactional
    public  void insert(Comment comment) {
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
}
