package com.xxy.springboot.rest;

import com.xxy.springboot.Utils.HouseSubscribeStatus;
import com.xxy.springboot.Utils.UserUtils;
import com.xxy.springboot.config.ShiroConfig;
import com.xxy.springboot.message.RestMessage;
import com.xxy.springboot.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xxy on 2018/1/15.
 */
@RestController
@RequestMapping("/rest/user")
public class UserLogInRest {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserLogInRest.class);

    @GetMapping("/pager")
    public Page<User> getAllUser() {
        return null;
    }

    @PostMapping("/login")
    public RestMessage<User> userLogin(@RequestBody User user) {
        String username = user.getUserName();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        RestMessage<User> r = new RestMessage();
        if (UserUtils.isLogin()) {
            r.setCode(HouseSubscribeStatus.FINISH.getValue());
            r.setMessage("已经登陆");
            r.setData(UserUtils.getCurrentUser());
            return r;
        }
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,
            // 并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,
            // 具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            token.clear();
            currentUser.logout();
        }
        r.setMessage("登陆成功");
        r.setCode(HouseSubscribeStatus.FINISH.getValue());
        r.setData(UserUtils.getCurrentUser());
        return r;
    }

}
