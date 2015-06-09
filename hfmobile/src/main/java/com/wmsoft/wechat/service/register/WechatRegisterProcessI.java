package com.wmsoft.wechat.service.register;



import java.util.Map;

import org.jeecgframework.core.common.service.CommonServiceI;

public interface WechatRegisterProcessI extends CommonServiceI {
    
	public String responeHasNotRegister(Map<String, String> map);
	
	public String responeHasRegister(Map<String,String> map);
}
