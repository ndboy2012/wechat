package com.ddmt.service.impl.commonadmin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddmt.entity.commonadmin.CommonAdminEntity;
import com.ddmt.entity.district.DistrictEntity;
import com.ddmt.entity.upperadmin.UpperAdminEntity;
import com.ddmt.service.commonadmin.CommonAdminServiceI;
import com.ddmt.util.MD5Util;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("commonAdminService")
@Transactional
public class CommonAdminServiceImpl extends CommonServiceImpl implements CommonAdminServiceI {

	@Override
	public List<DistrictEntity> queryAllDistrict() {
		return loadAll(DistrictEntity.class);
	}

	@Override
	public DistrictEntity findDistrictInfoByCode(String code) {
		return findUniqueByProperty(DistrictEntity.class, "districtCode", code);
	}

	@Override
	public boolean forbidAdminById(String id) {
		int count = 0;
		String sql = "update tb_mctax_common_admin_info set status='-1' where id='"+id+"'";
		String sql2 = "update tb_mctax_user_info set status='-1' where user_id='"+id+"'";
	    count = updateBySqlString(sql);
	    updateBySqlString(sql2);
		if(count>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean allowAdminById(String id) {
		int count = 0;
		String sql = "update tb_mctax_common_admin_info set status='1' where id='"+id+"'";
		String sql2 = "update tb_mctax_user_info set status='1' where user_id='"+id+"'";
	    count = updateBySqlString(sql);
	    updateBySqlString(sql2);
		if(count>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkPwd(CommonAdminEntity admin, String password) {
		int count = 0;
		boolean result = false;
		String passwordold = MD5Util.getMD5String(password);
		
		String hql = "select count(*) from UserEntity where password = '"
				+ passwordold + "' and userId='" + admin.getId() + "'";
		count = Integer.parseInt(singleResult(hql).toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean changePwd(CommonAdminEntity admin, String newPwd) {
		int count = 0;
		boolean result = false;
		String newPassword = MD5Util.getMD5String(newPwd);
		String hql = "update tb_mctax_user_info set password='" + newPassword
				+ "' where user_id='" + admin.getId() + "'";
		count = updateBySqlString(hql);
		if (count > 0) {
			result = true;
			;
		}
		return result;
	}
	
}