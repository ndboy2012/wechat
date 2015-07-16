package org.yelp.service.base;

import java.util.Map;

public interface MessageProcessI {
    
	 //有消息回复的请求
	 public String getRespMessage(Map<String, String> map) throws Exception;   
	 
	 //没有消息回复的请求
	 public void processMessage(Map<String,String> map) throws Exception;
}
