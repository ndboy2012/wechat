package com.wmsoft.web.service.register;


import java.util.Map;

import org.jeecgframework.core.common.service.CommonServiceI;

import com.wmsoft.web.entity.register.RegisterEntity;
import com.wmsoft.web.entity.register.WechatRegHasget;



public interface WechatRegisterServiceI extends CommonServiceI {
    
	public boolean hasRegister(String open_id) throws Exception;
	
	public String responeHasNotRegister(Map<String, String> map) throws Exception;
	
	public int getRegisterNums() throws Exception;
	
	public RegisterEntity findRegisterInfoByTel(String telephone) throws Exception;
	
	public RegisterEntity findRegisterInfoByOpenId(String openId) throws Exception;
	
	public boolean validateRegisterBytel(String telephone) throws Exception;
	
	public boolean hasGet(String telephone) throws Exception;
	
	public void saveHasGet(WechatRegHasget get) throws Exception;
	
	public void saveRegisterInfo(RegisterEntity register) throws Exception;
}
