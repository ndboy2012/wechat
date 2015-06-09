package com.wmsoft.web.service.stupreferential;

import org.jeecgframework.core.common.service.CommonServiceI;

import com.wmsoft.web.entity.anonymous.AnonymousEntity;

public interface StuPreferentialServiceI extends CommonServiceI{

	
	/**
	 * 提交
	 * @param e
	 * @throws Exception
	 */
	public void saveAnonymous(AnonymousEntity ae) throws Exception;
	
	
	/**
	 * 判断用户是否重复提交
	 * @param openId
	 * @return
	 */
	public Boolean hasApplicated(String openId);
	
	
	/**
	 * 判断用户是否是目标客户
	 * @param phone
	 * @return
	 */
	public Boolean hasIn(String telephone);
	
}
