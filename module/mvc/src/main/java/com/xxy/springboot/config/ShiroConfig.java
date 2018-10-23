package com.xxy.springboot.config;

import com.google.common.collect.Maps;
import com.xxy.springboot.incepter.MyFilter;
import com.xxy.springboot.system.service.managerimpl.MyRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xxy on 2018/4/18.
 */
//@Configuration
public class ShiroConfig {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ShiroConfig.class);


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFactory(SecurityManager s){
//        ShiroFilterFactoryBeanExt factory = new ShiroFilterFactoryBeanExt();
        ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
        factory.setSecurityManager(s);
        HashMap filters = Maps.newHashMap();
        filters.put("perm",new PermissionsAuthorizationFilter());
        filters.put("anon", new AnonymousFilter());
        filters.put("roles", new RolesAuthorizationFilter());
        filters.put("user", new UserFilter());
        filters.put("myfilter",new MyFilter());
        factory.setFilters(filters);
        factory.setLoginUrl("/rest/user/login");//设置登录的URL
        factory.setSuccessUrl("/login");//设置登录成功URL
        factory.setUnauthorizedUrl("/login/403");//设置没有权限的页面
        loadShiroFilterChain(factory);
        return factory;
    }

    /**
     *  注册DelegatingFilterProxy（Shiro）
     * 集成Shiro有2种方法：
     * 1. 按这个方法自己组装一个FilterRegistrationBean（这种方法更为灵活，可以自己定义UrlPattern，
     * 在项目使用中你可能会因为一些很但疼的问题最后采用它，
     * 想使用它你可能需要看官网或者已经很了解Shiro的处理原理了）
     * 2. 直接使用ShiroFilterFactoryBean（这种方法比较简单，
     * 其内部对ShiroFilter做了组装工作，无法自己定义UrlPattern，
     * 默认拦截 /*）
     * @return
     */
    @Bean
    public FilterRegistrationBean secutiryFilter(){
        /**
         * DelegatingFilterProxy就是一个对于servlet filter的代理，
         * 用这个类的好处主要是通过Spring容器来管理servlet filter的生命周期，
         * 还有就是如果filter中需要一些Spring容器的实例，可以通过spring直接注入，
         * 另外读取一些配置文件这些便利的操作都可以通过Spring来配置实现。
         * 自动去查找叫shiroFilter的过滤器
         */
        DelegatingFilterProxy proxy = new DelegatingFilterProxy("shiroFilter");
        proxy.setTargetFilterLifecycle(true);
        /**
         * springboot提供的自定义过滤器
         */
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(proxy);
        registrationBean.setEnabled(true);
        registrationBean.setName("myFilters");
        /*
         REQUEST：当用户直接访问页面时，Web容器将会调用过滤器。如果目标资源是通过RequestDispatcher的include()或forward()方法访问时，那么该过滤器就不会被调用。
        INCLUDE：如果目标资源是通过RequestDispatcher的include()方法访问时，那么该过滤器将被调用。除此之外，该过滤器不会被调用。
        FORWARD：如果目标资源是通过RequestDispatcher的forward()方法访问时，那么该过滤器将被调用，除此之外，该过滤器不会被调用。
        ERROR：如果目标资源是通过声明式异常处理机制调用时，那么该过滤器将被调用。除此之外，过滤器不会被调用
         */
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);

//        registrationBean.setName("shiro");
        //添加不需要忽略的格式信息.
//        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        registrationBean.addUrlPatterns(new String[]{this.getUrlPatterns()});
        return  registrationBean;
    }
    protected String getUrlPatterns() {
        return "/*";
    }//shiroFilter 拦截所有请求
    @Bean("MyRealm")
    public MyRealm getRealm(){
        MyRealm realm = new MyRealm();
        return realm;
    }

    //创建默认的securityManager
    @Bean("SecurityManager")
    public SecurityManager getManger(MyRealm myRealm,EhCacheManager ehCacheManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);// 设置自定义的验证和授权方式
        securityManager.setCacheManager(ehCacheManager);//设置缓存方式
        return securityManager;
    }
    //创建shiroFilter过滤器



    /**
     * shiro bean生命周期的管理
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }



    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // <!--对应路径请求Filter链，代表是filter过滤引用，且是顺序执行，url且从上置下优先匹配，一旦匹配则不往下搜寻-->
        // authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
        filterChainDefinitionMap.put("/rest/user/login", "myfilter");//anon 可以理解为不拦截
        filterChainDefinitionMap.put("/**", "myfilter");//设置所有的请求都要经过验证
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }


    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }

//    public SecurityManager getSecurityManager(){
//        return
//    }


}
