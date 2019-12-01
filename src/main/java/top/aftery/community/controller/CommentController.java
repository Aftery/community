package top.aftery.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.aftery.community.mapper.CommentDAO;
import top.aftery.community.model.Comment;

@Controller
public class CommentController {

    @Autowired
    private CommentDAO commentDAO;


    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody  Comment comment) {
        System.out.println("输出：{}"+comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(1);
        commentDAO.insert(comment);
        return  null;
    }


}
