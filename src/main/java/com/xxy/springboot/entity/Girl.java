package com.xxy.springboot.entity;

import javax.persistence.*;

/**
 * Created by Shinelon on 2017/11/28.
 */
@Entity
@Table(name = "sp_girl")
public class Girl  extends BaseEntity{
//
    @Column(name = "cupeSize",length = 10)
    private  String cupeSize;
    @Column(name = "age",length = 10)
    private  Integer age;
    public String getCupeSize() {
        return cupeSize;
    }

    public void setCupeSize(String cupeSize) {
        this.cupeSize = cupeSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
