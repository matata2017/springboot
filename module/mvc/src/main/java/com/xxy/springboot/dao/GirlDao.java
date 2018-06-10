package com.xxy.springboot.dao;

import com.xxy.springboot.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Shinelon on 2017/11/28.
 */
@Component
public interface GirlDao extends JpaRepository<Girl,Integer> {
}
