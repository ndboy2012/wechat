package com.ddmt.service.main;

import org.jeecgframework.core.common.service.CommonService;

public interface CommonMainServiceI extends CommonService{
  
	  public int  queryLeaveMessage(String districtCode);
	  
	  public int  queryLeaveMessage();
}
