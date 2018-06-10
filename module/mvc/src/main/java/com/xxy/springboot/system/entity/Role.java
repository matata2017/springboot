package com.xxy.springboot.system.entity;

import com.xxy.springboot.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by xxy on 2018/1/12.
 * 角色表
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    @Column
    private  String roleName;

    /**
     * 一个角色对应多个用户
      */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_user",joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")})
    private List<User> userList;

    /**
     * 一个角色对应多个权限
     */
    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private List<Permission> permissionList;



    /**
     * Gets 一个角色对应多个用户.
     *
     * @return Value of 一个角色对应多个用户.
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Sets new 一个角色对应多个用户.
     *
     * @param userList New value of 一个角色对应多个用户.
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * Gets 一个角色对应多个权限.
     *
     * @return Value of 一个角色对应多个权限.
     */
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    /**
     * Sets new 一个角色对应多个权限.
     *
     * @param permissionList New value of 一个角色对应多个权限.
     */
    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    /**
     * Gets 角色名称.
     *
     * @return Value of 角色名称.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets new 角色名称.
     *
     * @param roleName New value of 角色名称.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
