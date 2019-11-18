package top.aftery.community.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Aftery
 * @Date 2019/11/17 16:01
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class GithubUser {
    private String name;
    private Long id;
    private String bio;

}
