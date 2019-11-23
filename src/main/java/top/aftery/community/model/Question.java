package top.aftery.community.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Aftery
 * @Date 2019/11/20 20:32
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
@SuppressWarnings("all")
public class Question {

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


}
