package top.aftery.community.exception;

/**
 * @Author Aftery
 * @Date 2019/11/28 21:42
 * @Version 1.0
 **/
@SuppressWarnings("all")
public enum CustomizeErrorCode implements ICustomizeErrorCode {


    QUESTION_NOT_FOUND(2001, "你找的问题不在了,要不要换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002, "为未选择任何问题或者评论进行回复"),
    NO_LOGIN(2003, "未登录不能进行评论，请先登录"),
    SYS_ERROR(2004, "服务器冒烟了，要不然你稍后再试试!!!"),
    TYPE_PARAM_WRONG(2005, "评论错误或者不存在"),
    COMMINT_NOT_FOUND(2006, "你回复的评论不存在了,要不换个试试"),
    CONTENT_IS_EMPTY(2007, "回复的评论不能为空"),

    ;

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
