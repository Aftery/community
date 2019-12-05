package top.aftery.community.dto;

import lombok.Data;
import top.aftery.community.model.User;

@Data
public class CommentDto {

    private Long id;

    private Long parentId;

    private Integer type;

    private Integer commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer likeCount;

    private String content;

    private User user;
}
