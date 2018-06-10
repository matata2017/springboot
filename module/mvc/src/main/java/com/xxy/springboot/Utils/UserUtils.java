package com.xxy.springboot.Utils;

import com.xxy.springboot.system.entity.User;
import com.xxy.springboot.system.service.UserManager;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 用户工具类
 */
@Configuration
public class UserUtils implements ApplicationContextAware {
    protected static ApplicationContext context;
//    @Bean
//    public UserManager userManager(){
//        userManager = new UserManagerJPA();
//        return userManager;
//    }
    /**
     * 获取当前用户
     * @return
     */
    public static <T extends User>  T getCurrentUser(){
        if(SecurityUtils.getSubject().getPrincipal() instanceof String){
            UserManager userManager = context.getBean(UserManager.class);
            return (T) userManager.findByName((String) SecurityUtils.getSubject().getPrincipal());
        }
        return (T) SecurityUtils.getSubject().getPrincipal();
    }
    public static boolean isLogin(){
        return SecurityUtils.getSubject().isAuthenticated();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
