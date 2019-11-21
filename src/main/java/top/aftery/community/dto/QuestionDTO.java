package top.aftery.community.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.aftery.community.model.User;

/**
 * @Author Aftery
 * @Date 2019/11/21 20:49
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;

}
