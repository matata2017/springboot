package com.xxy.springboot.system.entity;

import com.xxy.springboot.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by xxy on 2018/1/13.
 * 权限
 */
@Entity
@Table(name = "sys_permission")
public class Permission  extends BaseEntity{
    /**
     * 权限名称
     */
    @Column
    private String permisionName;
    /**
     * 一个角色有很多权限
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Role role;


    /**
     * Gets 一个角色有很多权限.
     *
     * @return Value of 一个角色有很多权限.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets new 一个角色有很多权限.
     *
     * @param role New value of 一个角色有很多权限.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Sets new 权限名称.
     *
     * @param permisionName New value of 权限名称.
     */
    public void setPermisionName(String permisionName) {
        this.permisionName = permisionName;
    }

    /**
     * Gets 权限名称.
     *
     * @return Value of 权限名称.
     */
    public String getPermisionName() {
        return permisionName;
    }
}
