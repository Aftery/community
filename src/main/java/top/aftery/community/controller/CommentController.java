package top.aftery.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.aftery.community.dto.CommentDto;
import top.aftery.community.dto.ResultDTO;
import top.aftery.community.enums.CommenTypeEnum;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.model.Comment;
import top.aftery.community.model.User;
import top.aftery.community.service.CommentService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private HttpSession session;

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResultDTO post(@RequestBody Comment comment) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(comment==null || StringUtils.isEmpty(comment.getContent())){
            return  ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        System.out.println("输出：{}" + comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }


    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List> comments(@PathVariable(name ="id") Long id) {
        List<CommentDto> commentDtos = commentService.listByQuestionId(id, CommenTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDtos);
    }

}
