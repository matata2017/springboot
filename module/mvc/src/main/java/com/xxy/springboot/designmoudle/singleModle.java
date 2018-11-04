package com.xxy.springboot.designmoudle;

/**
 * Created by xxy on 2018/11/3.
 * 单例模式
 * 恶汉模式
 */
public class singleModle {
    private  singleModle(){}
    static singleModle singleModle = new singleModle();
    public static singleModle getInstance(){
        return singleModle;
    }
}
