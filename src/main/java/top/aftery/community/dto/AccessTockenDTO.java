package top.aftery.community.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Aftery
 * @Date 2019/11/17 15:26
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
@SuppressWarnings("all")
public class AccessTockenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
