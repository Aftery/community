package top.aftery.community.dto;

import lombok.Data;
import top.aftery.community.model.User;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private String notifier;
    private String notifieNamer;
    private String outerTile;
    private String type;
}
