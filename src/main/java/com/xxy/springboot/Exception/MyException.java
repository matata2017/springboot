package com.xxy.springboot.Exception;

/**
 * Created by xxy on 2018/4/18.
 */
public class MyException extends RuntimeException {
    public MyException(){};

    public MyException(String message){
        super(message);
    };


}
