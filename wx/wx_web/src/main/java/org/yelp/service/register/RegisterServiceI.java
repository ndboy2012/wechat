package org.yelp.service.register;

import org.yelp.entity.register.RegisterEntity;


public interface RegisterServiceI {
     
	public void saveRegisterEntity(RegisterEntity register);
	
	public boolean checkIsExit(RegisterEntity register) throws Exception;
	
	public boolean checkTelephoneExit(String telephone) throws Exception;
	
	public boolean checkOpenIdExit(String openId) throws Exception;
	
	public String getOpenIdByTel(String telephone) throws Exception;
}
