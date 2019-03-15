package com.xxy.springboot.quartz;

import com.xxy.springboot.entity.SpiderMan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author xxy
 * 定时任务
 */
@Slf4j
@Component
public class quartz {
    @Autowired
    private SpiderMan spiderMan;
    @Scheduled(fixedDelay=1*1000)
    public void  getData(){

    }
}
