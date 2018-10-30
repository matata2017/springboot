package com.xxy.springboot.service.ManagerImpl;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Version;
import org.springframework.stereotype.Service;
import xxy.dubbo.api.service.DubboSpiderManager;

/**
 * Created by Shinelon on 2018/10/29.
 */
@Service
public class DubboSpiderImpl implements DubboSpiderManager {
    private static final Logger log = Logger.getLogger(DubboSpiderImpl.class);
    @Override
    public void spider() {
        System.out.print("=================开始调用");
        log.info("=======================dubbo调用服务成功");
    }
}
