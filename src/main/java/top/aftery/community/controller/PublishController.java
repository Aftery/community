package top.aftery.community.controller;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.aftery.community.cache.TagCache;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.exception.CustomizeException;
import top.aftery.community.model.Question;
import top.aftery.community.model.Questionuser;
import top.aftery.community.model.User;
import top.aftery.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Aftery
 * @Date 2019/11/18 22:32
 * @Version 1.0
 **/
@Slf4j
@Controller
@SuppressWarnings("all")
public class PublishController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String editPubish(@PathVariable(name = "id") Long id, Model model, HttpServletRequest request) throws Exception {
        Questionuser questionuser = questionService.getById(id);
        if (null == questionuser) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        if (!isOwner(questionuser, request)) {
            throw new CustomizeException(CustomizeErrorCode.EDIT_QUESTION_NO_PERMISSION);
        }
        model.addAttribute("title", questionuser.getTitle());
        model.addAttribute("des", questionuser.getDescription());
        model.addAttribute("tag", questionuser.getTag());
        model.addAttribute("id", questionuser.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest request, Model model) {
        log.info("\n tostring{}", question);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("des", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("tags", TagCache.get());

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        //编辑时只允许改自己的问题，插入时 creator 由当前登录用户决定
        if (question.getId() != null && !isOwner(questionService.getById(question.getId()), request)) {
            model.addAttribute("error", "你只能编辑自己发布的问题");
            return "publish";
        }
        if (StringUtils.isEmpty(question.getTitle())) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (question.getTitle().length() > 50) {
            model.addAttribute("error", "标题不能超过 50 字");
            return "publish";
        }
        if (StringUtils.isEmpty(question.getDescription())) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (question.getDescription().length() > 16000) {
            model.addAttribute("error", "问题补充过长，请精简到 1.6 万字以内");
            return "publish";
        }
        if (StringUtils.isEmpty(question.getTag())) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        if (question.getTag().length() > 256) {
            model.addAttribute("error", "标签总长不能超过 256 字");
            return "publish";
        }
        String invalid = TagCache.filterInvalid(question.getTag());
        if (StrUtil.isNotEmpty(invalid)) {
            model.addAttribute("error", "包含非法标签:：" + invalid);
            return "publish";
        }
        question.setCreator(user.getId());

        questionService.saveOrUpdate(question);
        return "redirect:/";
    }

    /**
     * 校验当前登录用户是否为该问题的作者。未登录一律视为无权。
     */
    private boolean isOwner(Questionuser questionuser, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return questionuser != null && user != null
                && questionuser.getCreator() != null
                && questionuser.getCreator().intValue() == user.getId().intValue();
    }

}
