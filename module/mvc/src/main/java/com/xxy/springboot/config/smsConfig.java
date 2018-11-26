package com.xxy.springboot.config;

import com.xxy.springboot.service.ManagerImpl.HNChannel;
import com.xxy.springboot.service.ManagerImpl.SmsServiceImpl;
import com.xxy.springboot.service.ManagerImpl.WHZCChannel;
import com.xxy.springboot.service.SmsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Shinelon on 2018/11/26.
 */
@Configuration
public class smsConfig {
    @Bean
    public WHZCChannel getWuchanel(){
        return new WHZCChannel();
    }

    @Bean
    public HNChannel gethnChanel(){
        return new HNChannel();
    }

    @Bean
    public SmsService getSms(){
        SmsServiceImpl smsService = new SmsServiceImpl();
        smsService.getChannels().put("1",getWuchanel());
        smsService.getChannels().put("2",gethnChanel());
        return smsService;
    }
}
