package com.wmsoft.web.service.impl.employee;


import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;

import com.wmsoft.web.entity.employee.EmployeeEntity;
import com.wmsoft.web.service.employee.EmployeeServiceI;


@Service
public class EmployeeServiceImpl extends CommonServiceImpl implements EmployeeServiceI{

	@Override
	public void saveEmployee(EmployeeEntity e) throws Exception {
		// TODO Auto-generated method stub
		save(e);
	}

	@Override
	public boolean hasApplicated(String telephone) throws Exception {
		boolean result = false;
		int count = 0;
		count = findByProperty(EmployeeEntity.class, "telephone", telephone).size();
		if(count>0) {
			result = true;
		} 
		return result;
 	}
        
}
