package com.wmsoft.web.service.sms;


import java.util.Map;

public interface SmsServiceI {
   
	  public Map<String,Object> SendRegisterMessage(String mobile);
}
