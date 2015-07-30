package com.ddmt.service.user;

import org.jeecgframework.core.common.service.CommonService;

import com.ddmt.entity.commonadmin.CommonAdminEntity;
import com.ddmt.entity.upperadmin.UpperAdminEntity;
import com.ddmt.entity.user.UserEntity;

public interface UserServiceI extends CommonService{
    
	 public UserEntity checkUserEntity(UserEntity user);
	 
	 public CommonAdminEntity findCommonAdminByUserName(UserEntity user);
	 
	 public UpperAdminEntity findUpperAdminByUserName(UserEntity user);
}
