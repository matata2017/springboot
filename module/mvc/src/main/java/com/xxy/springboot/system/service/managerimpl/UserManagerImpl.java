package com.xxy.springboot.system.service.managerimpl;

import com.xxy.springboot.dao.UserDao;
import com.xxy.springboot.system.entity.User;
import com.xxy.springboot.system.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Shinelon on 2018/4/19.
 */
@Service
public class UserManagerImpl implements UserManager {
    @Autowired
    private UserDao userDao;
    @Override
    public User findById(String id) {
        return userDao.findOne(id);
    }
    @Override
    public User findByName(String name) {
        return userDao.findByUserName(name);
    }
}
