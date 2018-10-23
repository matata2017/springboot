package com.xxy.springboot.system.service.managerimpl;

import com.xxy.springboot.dao.UserDao;
import com.xxy.springboot.system.entity.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author XXY
 * Created by xxy on 2018/4/19.
 */
public class MyRealm extends AuthorizingRealm{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private UserDao userDao;
    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken tokens) throws AuthenticationException {
        logger.info("=============in  doGetAuthenticationInfo");
        UsernamePasswordToken token=(UsernamePasswordToken) tokens;
        logger.info("验证当前Subject时获取到token为：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //查出是否有此用户
        User user=userDao.findByUserName(token.getUsername());
        if(user!=null){
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassWord(), getName());
        }
        logger.info("=============in  doGetAuthenticationInfo end");
        return null;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("=============in  doGetAuthorizationInfo");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principals);
        User user = userDao.findByUserName(loginName);
        return null;
    }
}
