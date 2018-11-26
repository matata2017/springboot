package com.xxy.springboot.service;

import java.util.Map;

public interface SmsService {
	/**
	 * 发送短信证码
	 * @param phone
	 * @param code
	 * @param min 有效期，分钟
	 * @param channel
	 */
	public void sendValidCode(String phone, String code, Integer min, String channel);

	/**
	 * 发送模板短信
	 * @param phone 电话
	 * @param templateId 模板id
	 * @param channel 短信通道
	 * @param params 模板参数
	 */
	public void send(String phone, String templateId, String channel, Map<String, Object> map);

	void sendSms(String phone, String content, String channel);
}
