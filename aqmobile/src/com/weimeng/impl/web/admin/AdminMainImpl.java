package com.weimeng.impl.web.admin;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weimeng.dao.AdminMainDao;
import com.weimeng.dao.CommonToolsDao;
import com.weimeng.util.DateProcess;

@Service("adminMainImpl")
public class AdminMainImpl implements AdminMainDao {
    
	@Resource(name="commonToolsImpl")
	private CommonToolsDao commonToolsImpl;
	
	public Integer getTotalRegisterCount() throws Exception{
		String sql = "select count(*) from WechatBaseInfo";
		int count = Integer.parseInt(commonToolsImpl.queryUnique(sql, null).toString());
		return count;
	}

	@Override
	public Integer getTodayRegisterCount() throws Exception {
		String sql = "select count(*) from WechatBaseInfo where bindDate between ? and ?";
		String datebefore = DateProcess.getSpecifiedDayBefore(new Date().toLocaleString());
		Date dd = DateProcess.convertString2date(datebefore);
		Timestamp timestamp = new Timestamp(dd.getTime());
		Object[] parameters = {timestamp,DateProcess.geTimestamp(),};
		int count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters).toString());
		return count;
	}
  
}
