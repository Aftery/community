package top.aftery.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.aftery.community.enums.NotificationTypeEnum;
import top.aftery.community.model.Notification;
import top.aftery.community.model.User;
import top.aftery.community.service.NotificationService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/notification/{id}")
    public String editPubish(@PathVariable(name = "id") Long id, Model model, HttpServletRequest request) throws Exception {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        Notification notification = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_QUESTION.getType() == notification.getType() || NotificationTypeEnum.REPLY_QUESTION.getType() == notification.getType()) {
            return "redirect:/question/" + notification.getOuterid();
        }
        return "redirect:/";
    }


}
