package com.wmsoft.web.service.impl.stupreferential;

import java.util.Date;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmsoft.web.entity.anonymous.AnonymousEntity;
import com.wmsoft.web.service.register.WechatRegisterServiceI;
import com.wmsoft.web.service.stupreferential.StuPreferentialServiceI;

@Service
public class StuPreferentialServiceImpl extends CommonServiceImpl implements StuPreferentialServiceI{

	@Autowired
	private WechatRegisterServiceI wechatRegisterService;
	
	
	/**
	 * 提交
	 */
	@Override
	public void saveAnonymous(AnonymousEntity ae) throws Exception {
		ae.setCreateDate(new Date());
		save(ae);
	}
	
	/**
	 * 判断用户是否重复提交
	 */
	@Override
	public Boolean hasApplicated(String openId) {
		Boolean result = true;
		int count = 0;
		count = findByProperty(AnonymousEntity.class, "openId", openId).size();
		if(count>0) {
			result = false;
		} 
		return result;
	}
	
	
	/**
	 * 判断用户是否是目标客户
	 */
	@Override
	public Boolean hasIn(String telephone) {
		String hql = "select phone from StudentPhone where phone='"+telephone+"'";
//		StringBuffer sb = new StringBuffer();
//		sb.append("select phone from StudentPhone where phone='");
//		sb.append(telephone);
//		sb.append("'");
		//String exist = singleResult(hql).toString();
		Object exist = singleResult(hql);
		if(null==exist)
			return false;
		
		return true;
	}
	
}
