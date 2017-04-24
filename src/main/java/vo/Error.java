package vo;

/**
 * Created by Administrator on 24-01-2017.
 */
public class Error {
    private String errorCode;
    private String message;
    private String stackTrace;

    public Error(String code, String message){
        this.setErrorCode(code);
        this.setMessage(message);
    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
