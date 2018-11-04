package com.xxy.springboot.designmoudle;

/**
 * Created by Shinelon on 2018/11/3.
 */
public class man implements person {
    int id;
    String name;
    public man(){};
    public  man(int id,String name){
        this.id=id;
        this.name=name;
    }
    public void calls(int i){
        new man(i,"name"+i);
    }

    @Override
    public void call() {
        System.out.print("===============i am  a  man");
    }
}
