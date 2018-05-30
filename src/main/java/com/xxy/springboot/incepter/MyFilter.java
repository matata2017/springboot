package com.xxy.springboot.incepter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by xxy on 2018/5/16.
 */
public class MyFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);
        WebUtils.redirectToSavedRequest(request,response,this.getSuccessUrl());
        WebUtils.issueRedirect(request,response,this.getSuccessUrl());
        return false;
    }
    @Override
    protected void setFailureAttribute(ServletRequest request,
                                       AuthenticationException ae) {
            request.setAttribute(getFailureKeyAttribute(), "用户名或密码错误");
    }

}
