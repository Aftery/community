package top.aftery.community.exception;

/**
 * @Author Aftery
 * @Date 2019/11/28 21:19
 * @Version 1.0
 **/
@SuppressWarnings("all")
public class CustomizeException extends RuntimeException {

    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
