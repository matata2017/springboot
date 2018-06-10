package com.xxy.springboot.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by xxy on 2018/1/12.
 */

/**
 * 标注为@MappedSuperclass的类将不是一个完整的实体类，
 * 他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @GenericGenerator(
            name = "idGenerator",
            strategy = "uuid"
    )
    @GeneratedValue(
            generator = "idGenerator"
    )
    @Id
    @Column(
            length = 32
    )
    private String id;


    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public void setId(String id) {
        this.id = id;
    }
}
