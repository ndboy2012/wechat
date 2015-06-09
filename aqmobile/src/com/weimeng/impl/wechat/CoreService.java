package com.weimeng.impl.wechat;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.weimeng.util.ParseXml;

@Service("coreService")
public class CoreService {
   
	private Logger logger = Logger.getLogger(CoreService.class);
	
	@Resource(name="respMessage")
	private  RespMessage respMessage;
	
	public  String processRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String respmessage = null;
		try {		
			//
			Map<String, String> mapXml = ParseXml.parse(request);
			logger.info("***********"+mapXml.get("MsgType")+"*********");
			//
			respmessage = respMessage.respTextMessage(mapXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respmessage;
	}
}
