package com.ltsh.wx.web.common.enums;

/**
 * Created by Random on 2017/11/23.
 */
public enum ResultCodeEnum {
    SUCCEED("000000", "成功"),
    FAIL("990001", "%s失败"),
    PARAM_ERROR("990002", "参数校验错误"),
    NOT_BIND_FAIL("980001", "用户未绑定"),
    USER_PASS_ERROR("980002", "用户名或密码错误"),
//    USER_BIND_FAIL("980001", "绑定用户失败"),
//    USER_BIND_FAIL("980002", "用户已绑定,绑定失败"),
    ;

    /** 编码 */
    private String code;
    /** 信息 */
    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
