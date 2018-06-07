package com.xxy.springboot.service.ManagerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.xxy.springboot.Utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class IdCardInfoImpl  {
	private static final Logger log = LoggerFactory.getLogger(IdCardInfoImpl.class);
	/**
	 *
     */
	//@Value("${idcardurl}")
	private  final String IDCARD_URL="http://182.254.148.132/yunshi/v1/idcardreturnstring";
	//@Value("${businessurl}")
	private  final String BUSINESS_URL="http://182.254.148.132/dev/yunshi/v1/businesslicensereturnstring";
	//@Value("${idcardcheckurl}")
	private  final String IDCARDCHECK_URL="http://182.254.148.132/dev/yunshi/v1/idcardreturnphoto";

	/**
	 * 身份证识别接口
	 * @param
	 * @return
     */
	public Map<String, Object> queryByIdCard(String fileName) {
			return getMap(fileName);
	}
	/***
	 * 根据图片查询身份证信息
	 * @param  fileName
	 * @return
	 */
	private Map<String ,Object>getMap(String fileName){
		String base64 = null;
		try {
			Map<String, String> header = Maps.newHashMap();
			Map<String, String> request = Maps.newHashMap();
			//String [] suffix =filePath.split(".");
			//设置要传的参数
			request.put("suffix_name", "");
			request.put("idcard_photo_base64", base64);
			HttpResponse response = null;
			response = HttpUtils.doPost(IDCARD_URL, null, "POST", header, null, request);
			org.apache.http.HttpEntity entity = response.getEntity();
			String janson = EntityUtils.toString(entity);
			ObjectMapper objectMapper = new ObjectMapper();
			Map car = objectMapper.readValue(janson, Map.class);
			if ("success".equals(car.get("code"))) {
				Map maps =(Map<String, Object>) car.get("data");
				return maps;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),"调用身份证识别接口失败");
		}
		return Maps.newHashMap();
	}

}
