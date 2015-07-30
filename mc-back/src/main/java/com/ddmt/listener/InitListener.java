package com.ddmt.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ddmt.constant.Constant;
import com.ddmt.entity.dictionary.DictionaryBean;

public class InitListener implements javax.servlet.ServletContextListener {

	private Logger logger = Logger.getLogger(InitListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		SystemService systemService = (SystemService) webApplicationContext
				.getBean("systemService");
		Constant.BASE_PATH = event.getServletContext().getContextPath()+"/";
		System.out.println(Constant.BASE_PATH);
		logger.debug("InitListener-------------------------缓存字典表");
		systemService.initAllDict();
		logger.info("-------------------------字典表初始化成功--------");
		Constant.Item = DictionaryBean.keyDict.get("item_type_button").getValue();
		Constant.Article = DictionaryBean.keyDict.get("item_type_article").getValue();
		Constant.ROOT_URL = event.getServletContext().getContextPath() + "/";
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}
