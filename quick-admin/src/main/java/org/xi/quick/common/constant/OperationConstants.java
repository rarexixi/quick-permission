package org.xi.quick.common.constant;

public enum OperationConstants {

    SYSTEM_ERROR(100000, "系统错误"),
    SERVICE_NOT_AVAILABLE(100100, "服务提供方不存在"),
    HAS_NO_PERMISSION(100200, "权限不足"),
    NOT_LOGIN(100210, "未登录"),
    VALIDATE_FAILURE(100211, "账号或密码错误"),
    TOKEN_EXPIRED(100212, "token 过期"),
    CAPTCHA_ERROR(100213, "验证码过期或不正确"),
    NOT_NULL_OR_EMPTY(200101, "不能为空"),
    DUPLICATE_KEY(200201, "数据已存在"),
    PARAMETER_VALIDATION_FAILED(200102, "参数验证不通过");

    private String message;
    private int code;

    OperationConstants(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "OperationConstants{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
