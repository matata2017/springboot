package com.xxy.springboot.system.entity;

import com.xxy.springboot.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

/**
 * 登录用户
 * Created by xxy on 2018/1/12.
 */
@Entity
@Table(name = "sys_user")
public class User extends BaseEntity {


    /**
     * 多个用户对应多个角色
     */
    @ManyToMany
    @JoinTable(name = "sys_role_user",joinColumns = {
        @JoinColumn(name = "user_id",referencedColumnName = "id")
    },inverseJoinColumns = {
        @JoinColumn(name = "role_id",referencedColumnName = "id")
    })
    private List<Role> roleList;

    /**
     * 用户名
     */
    @Column(length = 30)
    private String userName;

    /**
     * 登录密码
     */
    @Column(length = 20)
    private String passWord;
    /**
     * Gets 用户名.
     *
     * @return Value of 用户名.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets 登录密码.
     *
     * @return Value of 登录密码.
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets new 用户名.
     *
     * @param userName New value of 用户名.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets new 登录密码.
     *
     * @param passWord New value of 登录密码.
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * Sets new 一个用户对应多个角色.
     *
     * @param roleList New value of 一个用户对应多个角色.
     */
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    /**
     * Gets 一个用户对应多个角色.
     *
     * @return Value of 一个用户对应多个角色.
     */
    public List<Role> getRoleList() {
        return roleList;
    }
}
