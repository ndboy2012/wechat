package com.wmsoft.web.service.impl.sms;



import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wmsoft.common.tools.GenerateRandCode;
import com.wmsoft.common.tools.XmlPraseUtil;
import com.wmsoft.web.constant.MessageConstant;
import com.wmsoft.web.service.sms.SmsServiceI;
import com.wmsoft.web.util.HttpClientUtil;
import com.wmsoft.wechat.constant.WechatConstant;


@Service
public class SmsServiceImpl implements SmsServiceI {
   
	private Logger logger = Logger.getLogger(SmsServiceImpl.class);
	@Override
	public Map<String, Object> SendRegisterMessage(String mobile) {
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		String validateCode = GenerateRandCode.generateCode();
		String content = WechatConstant.SmsContent.replace("messageContene",validateCode);
		String statusCode = sendMessage(mobile,content);
		dataMap.put("validateCode", validateCode);
		dataMap.put("statusCode", statusCode);
		return dataMap;
	}
	
	public String sendMessage(String mobile,String content) {
		Map<String,String> data = new HashMap<String,String>();
		Map<String,String> rtnMessage = new HashMap<String,String>();
		data.put(MessageConstant.smsContent, content);
		data.put(MessageConstant.mobile, mobile);
		try {
		    String result = HttpClientUtil.getInstance().postMethod(MessageConstant.url, data);
			String resultMap = XmlPraseUtil.praseString(result);
			rtnMessage = XmlPraseUtil.parseString2Map(resultMap);
			logger.info(mobile+":"+rtnMessage.get("DETAIL"));
			return XmlPraseUtil.parseString2Map(resultMap).get("CODE");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
   
}
