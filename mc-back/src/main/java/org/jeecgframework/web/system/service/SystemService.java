package org.jeecgframework.web.system.service;

import java.util.Set;


import org.jeecgframework.core.common.service.CommonService;

/**
 * 
 * @author  张代浩
 *
 */
public interface SystemService extends CommonService{

	public void addLog(String LogContent, Short loglevel,Short operatetype);
	
	public void initAllDict();
}
