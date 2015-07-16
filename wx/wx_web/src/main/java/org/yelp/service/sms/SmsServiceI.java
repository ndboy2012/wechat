package org.yelp.service.sms;

import java.util.Map;

public interface SmsServiceI {
    
	public Map<String,String> SendRegisterMessage(String mobile);
}
