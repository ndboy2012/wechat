package com.weimeng.dao;

public interface AdminMainDao {
      
	  /*获取全部的登记数目*/
	  public Integer getTotalRegisterCount() throws Exception;
	  
	  /*获取今日签到数目*/
	  public Integer getTodayRegisterCount() throws Exception;
}
