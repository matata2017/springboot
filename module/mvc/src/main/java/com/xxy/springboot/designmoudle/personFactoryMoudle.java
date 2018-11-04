package com.xxy.springboot.designmoudle;

/**
 * Created by Shinelon on 2018/11/3.
 * @desicription 工厂模式
 * 还有抽象工厂模式
 * 提供抽象类 personFacory 和抽象方法 getman() getwoman()
 *
 */
public  class personFactoryMoudle {
    public static person getMan(){
        return new  man();
    }
    public static  person getWoman(){
        return  new woman();
    }
}
