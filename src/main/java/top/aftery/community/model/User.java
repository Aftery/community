package top.aftery.community.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Aftery
 * @Date 2019/11/18 20:52
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;

}
