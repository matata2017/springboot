package com.xxy.springboot.service;

import com.xxy.springboot.entity.Girl;
import org.springframework.stereotype.Service;

/**
 * Created by Shinelon on 2017/11/28.
 */

public interface GirlManager {
    public Girl save(Girl girl);

    /**
     * 爬虫爬取网站的数据
     */
    void  spiderMan();

}
