package org.yelp.service.impl.register;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yelp.dao.register.RegisterDaoI;
import org.yelp.entity.register.RegisterEntity;
import org.yelp.service.register.RegisterServiceI;

@Service
@Transactional
public class RegisterService implements RegisterServiceI {

	public Logger logger = Logger.getLogger(RegisterService.class);

	@Resource(name = "registerDao")
	RegisterDaoI registerDao;

	@Override
	public void saveRegisterEntity(RegisterEntity register) {
		// TODO Auto-generated method stub
		logger.info("save register info");
		registerDao.saveRegisterEntity(register);
	}

	@Override
	public boolean checkIsExit(RegisterEntity register) throws Exception {
		// TODO Auto-generated method stub
		logger.info("check is exit");
		if (registerDao.checkOpenIdExit(register.getOpenId())
				|| registerDao.checkTelephoneExit(register.getTelephone())) {
            return true;
		}
		return false;
	}

	@Override
	public boolean checkTelephoneExit(String telephone) throws Exception {
		// TODO Auto-generated method stub
		if(registerDao.checkTelephoneExit(telephone)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkOpenIdExit(String openId) throws Exception {
		if(registerDao.checkOpenIdExit(openId)) {
			return true;
		}
		return false;
	}

	@Override
	public String getOpenIdByTel(String telephone) throws Exception {
		// TODO Auto-generated method stub
		return registerDao.getOpenIdByTel(telephone);
	}

}
