package top.aftery.community.exception;

/**
 * @Author Aftery
 * @Date 2019/11/28 21:42
 * @Version 1.0
 **/
@SuppressWarnings("all")
public enum CustomizeErrorCode implements ICustomizeErrorCode {


    QUESTION_NOT_FOUND("你找的问题不在了,要不要换个试试？");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return  message;
    }
}
