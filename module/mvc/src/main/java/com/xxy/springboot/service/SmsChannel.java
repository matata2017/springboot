package com.xxy.springboot.service;

import java.util.Map;

/**
 * 短信通道
 * @author Administrator
 *
 */
public interface SmsChannel {

	/**
	 * 发送模板短信
	 * @param phone 电话号码
	 * @param templateId 模板id
	 */
	void send(String phone, String templateId, Map<String, Object> params);
	/**
	 * 发送验证码
	 * @param phone
	 * @param numberCode
	 * @param min 有效时间,分钟
	 */
	void sendValidCode(String phone, String numberCode, String min);

	/**
	 *发送短信
	 * @param phone
	 * @param content
	 */
	void sendSms(String phone, String content);
}
