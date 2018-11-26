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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by xxy on 2017/3/29.
 *海南短信平台
 */
public class HNChannel implements SmsChannel,InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(HNChannel.class);
    @Value("${ys.hn.zc.SpCode}")
    private String SpCode;
    @Value("${ys.hn.zc.password}")
    private String password;
    @Value("${ys.hn.zc.LoginName}")
    private String LoginName;
    @Value("${ys.hn.zc.postUrl}")
    private String hnSmsPostUrl;

    private static final Integer LIMIT_HOST_TIMES=30;

    private static final Integer LIMIT_PHONE_TIMES=8;



    private  int SerialNumber=100;

    private final Map<String,String> returnMap = Maps.newHashMap();

    @Override
    public void afterPropertiesSet() throws Exception {
        returnMap.put("0","发送短信成功！");
        returnMap.put("1","提交参数不能为空！");
        returnMap.put("2","账号无效或权限不足！");
        returnMap.put("3","账号密码错误！");
        returnMap.put("4","预约发送时间格式不正确，应为yyyyMMddHHmmss！");
        returnMap.put("5","IP不合法！");
        returnMap.put("6","号码中含有无效号码或不在规定的号段或为免打扰号码（包含系统黑名单号码）！");
        returnMap.put("7","非法关键字！");
        returnMap.put("8","内容长度超过上限，最大402字或字符！");
        returnMap.put("9","接受号码过多，最大1000！");
        returnMap.put("12","您尚未订购[普通短信业务]，暂不能发送该类信息！");
        returnMap.put("13","您的[普通短信业务]剩余数量发送不足，暂不能发送该类信息！");
        returnMap.put("14","流水号格式不正确！");
        returnMap.put("15","流水号重复！");
        returnMap.put("16","超出发送上限（操作员帐户当日发送上限）！");
        returnMap.put("17","余额不足！");
        returnMap.put("18","扣费不成功！");
        returnMap.put("20","系统错误！");
        returnMap.put("21","密码错误次数达到5次！");
        returnMap.put("24","帐户状态不正常！");
        returnMap.put("25","账户权限不足！");
        returnMap.put("26","需要人工审核！");
        returnMap.put("28","发送内容与模板不符！");
        returnMap.put("29","扩展号太长或不是数字！");
        returnMap.put("32","同一号码相同内容发送次数太多（默认24小时内，验证码类发送5次或相同内容3次以上会报此错误。）");
        returnMap.put("33","同一号码验证码提交过快！");

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
//        String content="许乐您好，本会受理的许乐海仲字第123号4444与5555关于666一案，" +
//                "由独任仲裁员,许乐组成仲裁庭，特此通知，仲裁秘书阿萨德联系电话1233。";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date beforDate = DateUtils.getLastDate(new Date(),1);
//        limithost(beforDate);
//        String [] phones=phone.split(",");
//        for (int i=0;i<phones.length;i++){
//            limitPhone(phones[i],beforDate);
//        }
        newSMS(phone,content);
        //oldSMS(phone, numberCode, min);
    }
    private void  limithost(Date beforDate){
        try {
            InetAddress address =InetAddress.getLocalHost();
            String hostName =address.getHostName();
            String hostaddress=address.getHostAddress();
//            Integer count =phoneCodeRecodeManager.getCountHost(hostaddress,beforDate);
//            if (count>LIMIT_HOST_TIMES){
//                throw new BusException("限制单个IP地址24小时内请求次数过多");
//            }
        }catch (Exception e){
            log.error("查询IP请求次数失败");
            e.printStackTrace();
        }
    }

    private void limitPhone(String phoneNumber ,Date beforDate){
//        Integer count=phoneCodeRecodeManager.getCountPhone(phoneNumber,beforDate);
//        if (count>LIMIT_PHONE_TIMES){
//            throw new BusException("限制24小时内，针对同一手机号码发送验证码的总次数过多");
//        }
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
        String SerialNumber=getSerialNumber();
        try {
//            MultiValueMap postMap=new LinkedMultiValueMap<String, String>();
//            postMap.add("SpCode",SpCode);
//            postMap.add("LoginName",LoginName);
//            postMap.add("password",password);
//            postMap.add("MessageContent",java.net.URLEncoder.encode(content,"GBK"));
//            postMap.add("UserNumber",phone);
//            postMap.add("SerialNumber",SerialNumber);
//            postMap.add("f",1);
//            String postData = "SpCode=244435&LoginName=hn_zcwyy&Password=123qwe!@#"+
//                    "&MessageContent="+java.net.URLEncoder.encode(content,"GBK")+"&UserNumber="+phone+"&SerialNumber="+SerialNumber+"&f=1";
            String postData = "SpCode="+SpCode+"&LoginName="+LoginName+"&Password="+password+
                    "&MessageContent="+java.net.URLEncoder.encode(content,"GBK")+"&UserNumber="+phone+"&SerialNumber="+SerialNumber+"&f=1";
            String ret = this.SMS(postData, hnSmsPostUrl);
            String[] result = ret.split("&");
            String results=result[0].split("=")[1];
            log.info("返回结果："+ returnMap.get(results));
            log.info("返回结果："+ result[1]);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private   String getSerialNumber() {
        SerialNumber+=1;
        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");
        //获取时间戳
        String time=dateFormat.format(now);
        String info=now+"";
        int ran=0;
        if(SerialNumber>999){
            SerialNumber=100;
        }
        ran=SerialNumber;
        return time+info.substring(2, info.length())+ran;
    }

//    private Map<String,String> SMSNEW(MultiValueMap<String,String> postData, String postUrl){
//        RestTemplate client = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
////  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
////  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postData, headers);
////  执行HTTP请求
//        ResponseEntity<String> response = client.exchange(postUrl, HttpMethod.POST, requestEntity, String.class);
////  输出结果
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map car=null;
//        try {
//             car = objectMapper.readValue(response.getBody(), Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(response.getBody());
//        return car;
//    }

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
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "GBK");
            out.write(postData);
            out.flush();
            out.close();
            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }

}
