package com.xxy.springboot.service.ManagerImpl;

import com.xxy.springboot.dao.GirlDao;
import com.xxy.springboot.entity.Girl;
import com.xxy.springboot.entity.SpiderMan;
import com.xxy.springboot.service.GirlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * Created by Shinelon on 2017/11/28.
 */
@Service
public class GirlManagerImpl implements GirlManager {
    @Autowired
    private GirlDao girlDao;
    @Autowired
    SpiderMan spiderMan;
    @Override
    public Girl save(Girl girl) {
        return girlDao.save(girl);

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void spiderMan() {
        Spider.create(spiderMan).addUrl("http://my.oschina.net/flashsword/blog")
                .addPipeline(new ConsolePipeline()).run();
    }
}
