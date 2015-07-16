package org.yelp.dao.register;

import org.jeecgframework.core.common.service.CommonServiceI;
import org.yelp.entity.register.RegisterEntity;

public interface RegisterDaoI extends CommonServiceI{
       
	public void saveRegisterEntity(RegisterEntity register);
	
	//检查电话号码是否已经登记过了
	public boolean checkTelephoneExit(String telephone) throws Exception; 
	
	//检查open_id是否已经是登记过了
	public boolean checkOpenIdExit(String open_id) throws Exception;
	
	public String getOpenIdByTel(String telephone) throws Exception;
	
	public String getPhoneByOpenId(String openId) throws Exception;
}
