package top.aftery.community.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Aftery
 * @Date 2019/11/21 20:49
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
@SuppressWarnings("all")
public class QuestionUserDTO {
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
    private Integer userId;
    private String userName;
    private String userAccountId;
    private String userToken;
    private Long userGmtCreate;
    private Long userGmtModified;
    private String userAvatarUrl;

}
