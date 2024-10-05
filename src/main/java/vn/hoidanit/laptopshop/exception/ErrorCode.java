package vn.hoidanit.laptopshop.exception;

public enum ErrorCode {
    USER_EXISTED(400, "User already exists"),
    USER_PASSWORD_NOT_EXACTLY(400, "User and password are not exactly");

    private int code;
    private String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
