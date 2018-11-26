package com.xxy.springboot.service.ManagerImpl;

import com.google.common.collect.Maps;
import com.xxy.springboot.service.SmsChannel;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by xxy on 2017/3/29.
 *短信平台
 */
public class WHZCChannel implements SmsChannel,InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(WHZCChannel.class);
    @Value("${ys.wh.zc.username}")
    private String username;
    @Value("${ys.wh.zc.password}")
    private String password;
    private final Map<String,String> returnMap = Maps.newHashMap();

    @Override
    public void afterPropertiesSet() throws Exception {
        returnMap.put("0","发送成功！");
        returnMap.put("-1","uid或密码为空！");
        returnMap.put("-2","下发号码为空！");
        returnMap.put("-4","内容超长！");
        returnMap.put("-5","下发号码超长！");
        returnMap.put("-6","uid或密码不正确！");
        returnMap.put("-7","余额不足！");
        returnMap.put("-8","IP地址鉴权不对！");
        returnMap.put("-9","协议类型不对！");
        returnMap.put("-99","系统异常！");
    }

    @Override
    public void send(String phone, String templateId, Map<String, Object> params) {}

    @Override
    public void sendSms(String phone, String content) {
        newSMS(phone,content);
    }

    @Override
    public void sendValidCode(String phone, String numberCode, String min) {
        String content = "尊敬的用户，您的验证码为"+numberCode+"，本验证码"+min+"分钟内有效，感谢您使用";
        newSMS(phone,content);
        //oldSMS(phone, numberCode, min);
    }

    /**
     * @description 将xml字符串转换成map
     * @param xml
     * @return Map
     */
    public Map readStringXmlOut(String xml) {
        Map<String,String> map = Maps.newHashMap();
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            map.put("resultcode",rootElt.elementTextTrim("resultcode"));
            map.put("taskcode",rootElt.elementTextTrim("taskcode"));
            map.put("errordescription",rootElt.elementTextTrim("errordescription"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private void newSMS(String phone, String content){
        try {
            String postData = "uid=280&pw=318178&mb="+phone+"&ms="+java.net.URLEncoder.encode(content,"utf-8");
            String ret = this.SMS(postData, "http://139.196.144.25:18000/dingxu/send.do");
            String[] result = ret.split(",");
            log.info("返回结果："+ returnMap.get(result[0]));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String SMS(String postData, String postUrl) {
        //请自己反序列化返回的字符串并实现自己的逻辑
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();
            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
