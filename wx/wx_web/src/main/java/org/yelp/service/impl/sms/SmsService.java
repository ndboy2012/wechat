package org.yelp.service.impl.sms;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.yelp.constant.CommonConstant;
import org.yelp.constant.MessageConstant;
import org.yelp.service.sms.SmsServiceI;
import org.yelp.utils.GenerateRandCode;
import org.yelp.utils.HttpClientUtil;
import org.yelp.utils.XmlPraseUtil;


@Service
public class SmsService implements SmsServiceI {

	@Override
	public Map<String, String> SendRegisterMessage(String mobile) {
		Map<String,String> dataMap = new HashMap<String,String>();
		String validateCode = GenerateRandCode.generateCode();
		String content = CommonConstant.MESSAGE_CONTENT.replace("messageContene",validateCode);
		dataMap = sendMessage(mobile,content);
		dataMap.put("validateCode", validateCode);
		return dataMap;
	}
	
	private Map<String,String> sendMessage(String mobile,String content) {
		Map<String,String> data = new HashMap<String,String>();
		data.put(MessageConstant.smsContent, content);
		data.put(MessageConstant.mobile, mobile);
		try {
		    String result = HttpClientUtil.getInstance().postMethod(MessageConstant.url, data);
			String resultMap = XmlPraseUtil.praseString(result);
			return XmlPraseUtil.parseString2Map(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
