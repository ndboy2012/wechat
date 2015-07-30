package com.ddmt.service.impl.leave;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddmt.service.leave.LeaveServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("leaveService")
@Transactional
public class LeaveServiceImpl extends CommonServiceImpl implements LeaveServiceI {

	@Override
	public boolean bookHandler(String id) {
		String sql = "update tb_mctax_leave_message set is_handle='1' where id='"+id+"'";
		updateBySqlString(sql);
		return true;
	}
	
}