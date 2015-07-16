package org.yelp.dao.impl.register;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yelp.dao.register.RegisterDaoI;
import org.yelp.entity.register.RegisterEntity;


@Service
public class RegisterDao extends CommonServiceImpl implements RegisterDaoI {

	@Override
	@Transactional
	public boolean checkTelephoneExit(String telephone) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(openId) from RegisterEntity where telephone=?";
		Object[] parameters = {telephone};
	    if(Integer.parseInt(queryUnique(sql, parameters).toString())>0) {
	    	return true;
	    }
		return false;
	}

	@Override
	public boolean checkOpenIdExit(String open_id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(openId) from RegisterEntity where open_id=?";
		Object[] parameters = {open_id};
	    if(Integer.parseInt(queryUnique(sql, parameters).toString())>0) {
	    	return true;
	    }
		return false;
	}

	@Override
	public void saveRegisterEntity(RegisterEntity register) {
		// TODO Auto-generated method stub
		save(register);
	}

	@Override
	public String getOpenIdByTel(String telephone) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select openId from RegisterEntity where telephone=?";
		Object[] parameters = {telephone};
		return queryUnique(sql,parameters).toString();
	}

	@Override
	public String getPhoneByOpenId(String openId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select telephone from RegisterEntity where openId=?";
		Object[] parameters = {openId};
		return queryUnique(sql,parameters).toString();
	}
      
}
