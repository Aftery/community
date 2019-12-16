package top.aftery.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.aftery.community.enums.NotificationStatusEnum;
import top.aftery.community.exception.CustomizeErrorCode;
import top.aftery.community.exception.CustomizeException;
import top.aftery.community.mapper.NotificationDAO;
import top.aftery.community.model.Notification;
import top.aftery.community.model.NotificationExample;
import top.aftery.community.model.User;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationDAO notificationDAO;

    public PageInfo<Notification> list(Integer id, Integer page, Integer size) {

        PageHelper.startPage(page, size);

        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(Long.valueOf(id));
        example.setOrderByClause("gmt_create desc");
        List<Notification> list = notificationDAO.selectByExample(example);
        PageInfo<Notification> pageInfo = new PageInfo<Notification>(list);
        return pageInfo;
    }


    public Long unreadCount(Integer id) {
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(Long.valueOf(id))
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        long count = notificationDAO.countByExample(example);
        return count;
    }

    public Notification read(Long id, User user) {
        Notification notification = notificationDAO.selectByPrimaryKey(Long.valueOf(id));
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFCATION_NOT_FAIL);
        }
        if (notification.getReceiver() != Long.valueOf(user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationDAO.updateByPrimaryKeySelective(notification);
        return notification;

    }
}
