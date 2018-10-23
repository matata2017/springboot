package com.xxy.springboot.incepter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import com.xxy.springboot.message.RestMessage;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class RestUserLoginFormFilter extends FormAuthenticationFilter {
    private ObjectMapper objectMapper = new ObjectMapper();
    private String loginFaildUrl = "/rest/login/faild";

    public RestUserLoginFormFilter() {

    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        try {
            Map e = (Map)this.objectMapper.readValue(request.getInputStream(), Map.class);
            UsernamePasswordToken ut = new UsernamePasswordToken();
            ut.setHost(this.getHost(request));
            ut.setPassword(e.get(this.getPasswordParam()).toString().toCharArray());
            ut.setUsername((String)e.get(this.getUsernameParam()));
            ut.setRememberMe(((Boolean)e.getOrDefault(this.getRememberMeParam(), Boolean.valueOf(false))).booleanValue());
            System.out.print("123");
            return ut;
        } catch (Exception var5) {
            var5.printStackTrace();
             return null;
        }
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        RestMessage rm = new RestMessage("990101", "登录失败");
        HttpServletResponse res = (HttpServletResponse)response;
        res.setStatus(401);
        try {
            response.setContentType("application/json;charset=utf-8");
            this.objectMapper.writeValue(response.getOutputStream(), rm);
        } catch (IOException var8) {

        }
        return true;
    }

    public void setLoginFaildUrl(String loginFaildUrl) {
        this.loginFaildUrl = loginFaildUrl;
    }

    public String getLoginFaildUrl() {
        return this.loginFaildUrl;
    }
}
