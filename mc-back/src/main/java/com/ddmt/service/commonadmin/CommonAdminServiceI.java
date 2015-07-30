package com.ddmt.service.commonadmin;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.ddmt.entity.commonadmin.CommonAdminEntity;
import com.ddmt.entity.district.DistrictEntity;
import com.ddmt.entity.upperadmin.UpperAdminEntity;

public interface CommonAdminServiceI extends CommonService{
	    
	public List<DistrictEntity> queryAllDistrict();
	
	public DistrictEntity findDistrictInfoByCode(String code);
	
	public boolean forbidAdminById(String id);
	
	public boolean allowAdminById(String id);
	
	public boolean checkPwd(CommonAdminEntity admin,String password);
	
	public boolean changePwd(CommonAdminEntity admin, String newPwd);

}
