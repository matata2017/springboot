package com.xxy.springboot.system.service;

import com.xxy.springboot.system.entity.User;

/**
 * Created by Shinelon on 2018/4/19.
 */
public interface UserManager {
    User findById(String id);

    User findByName(String name);
}
