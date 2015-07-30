package com.ddmt.service.upperadmin;

import org.jeecgframework.core.common.service.CommonService;

import com.ddmt.entity.upperadmin.UpperAdminEntity;

public interface UpperAdminServiceI extends CommonService {


	public boolean changePwd(UpperAdminEntity admin, String newPwd);

	public boolean checkPwd(UpperAdminEntity admin,String password);
}
