package top.aftery.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.aftery.community.dto.ResultDTO;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.model.Comment;
import top.aftery.community.model.User;
import top.aftery.community.service.CommentService;

import javax.servlet.http.HttpSession;

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
        System.out.println("输出：{}" + comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }


}
