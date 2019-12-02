package top.aftery.community.exception;

/**
 * @Author Aftery
 * @Date 2019/11/28 21:19
 * @Version 1.0
 **/
@SuppressWarnings("all")
public class CustomizeException extends RuntimeException {

    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }


    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
