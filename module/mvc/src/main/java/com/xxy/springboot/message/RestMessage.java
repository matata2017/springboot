package com.xxy.springboot.message;

/**
 * Created by Shinelon on 2018/5/30.
 */
public class RestMessage {
    String message;
    String code;
    public static final RestMessage SUCCESS = new RestMessage("0000", "成功");
    public static final RestMessage UNKNOW_ERROR = new RestMessage("9999", "未知错误");

    public RestMessage(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
