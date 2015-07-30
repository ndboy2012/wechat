package com.ddmt.service.impl.upperadmin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddmt.entity.upperadmin.UpperAdminEntity;
import com.ddmt.entity.user.UserEntity;
import com.ddmt.service.upperadmin.UpperAdminServiceI;
import com.ddmt.util.MD5Util;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("upperAdminService")
@Transactional
public class UpperAdminServiceImpl extends CommonServiceImpl implements
		UpperAdminServiceI {

	@Override
	public boolean changePwd(UpperAdminEntity admin, String newPwd) {
		int count = 0;
		boolean result = false;
		String newPassword = MD5Util.getMD5String(newPwd);
		String hql = "update tb_mctax_user_info set password='" + newPassword
				+ "' where user_id='" + admin.getId() + "'";
		count = updateBySqlString(hql);
		if (count > 0) {
			result = true;
			;
		}
		return result;
	}

	@Override
	public boolean checkPwd(UpperAdminEntity admin,String password) {
		int count = 0;
		boolean result = false;

		String passwordold = MD5Util.getMD5String(password);
		
		String hql = "select count(*) from UserEntity where password = '"
				+ passwordold + "' and userId='" + admin.getId() + "'";
		count = Integer.parseInt(singleResult(hql).toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

}