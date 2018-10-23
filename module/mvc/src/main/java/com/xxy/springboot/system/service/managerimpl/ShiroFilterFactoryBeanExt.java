package com.xxy.springboot.system.service.managerimpl;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by xxy on 2018/4/19.
 */
public class ShiroFilterFactoryBeanExt extends ShiroFilterFactoryBean implements Filter {

    private static final transient Logger log = LoggerFactory.getLogger(ShiroFilterFactoryBean.class);

    public ShiroFilterFactoryBeanExt() {
    }

    @Override
    protected AbstractShiroFilter createInstance() throws Exception {
        log.debug("Creating Shiro Filter instance.");
        SecurityManager securityManager = this.getSecurityManager();
        String manager1;
        if(securityManager == null) {
            manager1 = "SecurityManager property must be set.";
            throw new BeanInitializationException(manager1);
        } else if(!(securityManager instanceof WebSecurityManager)) {
            manager1 = "The security manager does not implement the WebSecurityManager interface.";
            throw new BeanInitializationException(manager1);
        } else {
            FilterChainManager manager = createFilterChainManager();
            //最重要的作用是：当请求url来的时候，他担任匹配工作（调用该类的getChain方法做匹配
            PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
            chainResolver.setFilterChainManager(manager);
            return new SpringShiroFilter((WebSecurityManager) securityManager, chainResolver);

        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
    @Override
    protected FilterChainManager createFilterChainManager() {
        return null;
    }

    private static final class SpringShiroFilter extends AbstractShiroFilter {

        protected SpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
            super();
            if (webSecurityManager == null) {
                throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
            }
            setSecurityManager(webSecurityManager);
            if (resolver != null) {
                setFilterChainResolver(resolver);
            }
        }
    }
}
