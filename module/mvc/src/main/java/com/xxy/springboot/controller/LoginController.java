package com.xxy.springboot.controller;

import com.xxy.springboot.Utils.UserUtils;
import com.xxy.springboot.system.entity.User;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xxy on 2018/2/6.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger log = Logger.getLogger(LoginController.class);
    @RequestMapping("/in")
    public String login(){
        log.trace("in");
        return "login/login";
    }

    @RequestMapping("/sus")
    public String sus(){
        log.trace("sus");
        return "login/sus";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        log.info("------没有权限-------"); return "403"; }

    @RequestMapping("/user")
    public String userLogin(@RequestBody User user){
        String username = user.getUserName();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,
            // 并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,
            // 具体验证方式详见此方法
            log.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            log.info("对用户[" + username + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
        }
        if(UserUtils.isLogin()){
           // return UserUtils.getCurrentUser();
            log.info("对用户[" + username + "]跳转");
            return "redirect:/login/sus";
        }else{
            token.clear();
        }
        return null;
    }
}
