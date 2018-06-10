package com.xxy.springboot.dao;

import com.xxy.springboot.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Shinelon on 2018/4/19.
 */
public interface UserDao extends JpaRepository<User,String> {
    User findByUserName(String userName);
}
