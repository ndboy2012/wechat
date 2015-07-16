package org.yelp.service.impl.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yelp.dao.register.RegisterDaoI;
import org.yelp.service.register.WechatRegisterServiceI;


@Service
public class WechatRegisterService implements WechatRegisterServiceI{
    
	@Autowired
	private RegisterDaoI registerDao;
	
	@Override
	public boolean isRegister(String open_id) {
		try {
			if(registerDao.checkOpenIdExit(open_id)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
   
}
