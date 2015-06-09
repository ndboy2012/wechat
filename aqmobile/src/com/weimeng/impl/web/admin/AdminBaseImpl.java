package com.weimeng.impl.web.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weimeng.dao.AdminBaseDao;
import com.weimeng.dao.CommonToolsDao;
import com.weimeng.entity.web.Admin.AdminBaseInfo;
import com.weimeng.util.DateProcess;

@Service("adminBaseImpl")
public class AdminBaseImpl implements AdminBaseDao {
    
	@Resource(name="commonToolsImpl")
	private CommonToolsDao commonToolsImpl;
	
	@Override
	public boolean isExitAdmin(AdminBaseInfo admin) throws Exception {
		boolean result = false;
		int count = 0;
		String sql = "select count(*) from AdminBaseInfo where username=? and password=?";
		Object[] parameters = {admin.getUsername(),admin.getPassword()};
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters).toString());
		if(count>0) {
			result = true;
		}
		return result;
	}
	@Override
	public void updateLastLoginTime(AdminBaseInfo admin) throws Exception {
		String sql = "update tb_admin_base_info set last_login_time=? where username=?";
		Object[] parameters = {DateProcess.geTimestamp(),admin.getUsername()};
		commonToolsImpl.executeUpdate(sql, parameters);
	}
}
