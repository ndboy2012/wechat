package com.wmsoft.web.service.employee;


import org.jeecgframework.core.common.service.CommonServiceI;

import com.wmsoft.web.entity.employee.EmployeeEntity;


public interface EmployeeServiceI extends CommonServiceI{

	public void saveEmployee(EmployeeEntity e) throws Exception;

	public boolean hasApplicated(String telephone) throws Exception;
	
}
