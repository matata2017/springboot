package com.xxy.springboot.service.ManagerImpl;


import com.xxy.springboot.service.SmsChannel;
import com.xxy.springboot.service.SmsService;

import java.util.Map;

public class SmsServiceImpl implements SmsService {
	private Map<String,SmsChannel> channels;

	public Map<String, SmsChannel> getChannels() {
		return channels;
	}

	public void setChannels(Map<String, SmsChannel> channels) {
		this.channels = channels;
	}

	@Override
	public void send(String phone, String templateId, String channel, Map<String,Object> map) {
		channels.get(channel).send(phone, templateId, map);
	}

	@Override
	public void sendValidCode(String phone, String code, Integer min, String channel) {
		channels.get(channel).sendValidCode(phone, code, min.toString());
	}

	@Override
	public void sendSms(String phone, String content, String channel) {
		channels.get(channel).sendSms(phone,content);
	}
}
