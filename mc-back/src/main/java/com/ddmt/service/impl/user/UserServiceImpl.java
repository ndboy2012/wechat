package com.ddmt.service.impl.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddmt.entity.commonadmin.CommonAdminEntity;
import com.ddmt.entity.upperadmin.UpperAdminEntity;
import com.ddmt.entity.user.UserEntity;
import com.ddmt.service.user.UserServiceI;
import com.ddmt.util.MD5Util;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("userService")
@Transactional
public class UserServiceImpl extends CommonServiceImpl implements UserServiceI {

	@Override
	public UserEntity checkUserEntity(UserEntity user) {
		String password = MD5Util.getMD5String(user.getPassword());
		String hql = "from UserEntity where username='" + user.getUsername()
				+ "' and password='" + password + "'";
		return singleResult(hql);
	}

	@Override
	public CommonAdminEntity findCommonAdminByUserName(UserEntity user) {
		return findUniqueByProperty(CommonAdminEntity.class, "id", user.getUserId());
		/*String password = MD5Util.getMD5String(user.getPassword());
		String hql = "from CommonAdminEntity where username='" + user.getUsername()
				+ "' and password='" + password + "'";
		return singleResult(hql);*/
	}

	@Override
	public UpperAdminEntity findUpperAdminByUserName(UserEntity user) {
		/*return findUniqueByProperty(UpperAdminEntity.class, "id", user.getUserId());*/
	    return getEntity(UpperAdminEntity.class, user.getUserId());
		/*String password = MD5Util.getMD5String(user.getPassword());
		String hql = "from UpperAdminEntity where username='" + user.getUsername()
				+ "' and password='" + password + "'";
		return singleResult(hql);*/
	}
	
}