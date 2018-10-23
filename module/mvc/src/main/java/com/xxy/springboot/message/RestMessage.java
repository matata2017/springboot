package com.xxy.springboot.message;

/**
 * rest返回数据
 * Created by Shinelon on 2018/5/30.
 */
public class RestMessage<T> {
    String message;
    int code;
    T data;
    boolean success;
    public RestMessage(){};

    public RestMessage(int code,String message){
        this.message=message;
        this.code=code;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }



    /**
     * Sets new success.
     *
     * @param success New value of success.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Gets success.
     *
     * @return Value of success.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets new data.
     *
     * @param data New value of data.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets data.
     *
     * @return Value of data.
     */
    public T getData() {
        return data;
    }

    /**
     * Sets new code.
     *
     * @param code New value of code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets code.
     *
     * @return Value of code.
     */
    public int getCode() {
        return code;
    }
}
