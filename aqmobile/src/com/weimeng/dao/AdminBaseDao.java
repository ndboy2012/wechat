package com.weimeng.dao;

import com.weimeng.entity.web.Admin.AdminBaseInfo;

public interface AdminBaseDao {
     
	public boolean isExitAdmin(AdminBaseInfo admin) throws Exception;
	
	public void updateLastLoginTime(AdminBaseInfo admin) throws Exception;
}  
