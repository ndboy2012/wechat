package com.ddmt.service.leave;

import org.jeecgframework.core.common.service.CommonService;

public interface LeaveServiceI extends CommonService{
    
	   public boolean bookHandler(String id);
}
