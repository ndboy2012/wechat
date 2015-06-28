package com.weimeng.listener;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.weimeng.dao.lotty.LotteryServiceI;
import com.weimeng.util.ConstantStr;


public class InitListener implements javax.servlet.ServletContextListener {

	private final Logger logger = Logger.getLogger(InitListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		logger.info("initListener init");
		
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
	
		logger.info("*********开始初始化奖品相关信息业务******");
		LotteryServiceI read = (LotteryServiceI) webApplicationContext
				.getBean("lotteryServiceImpl");
		try {
			read.storeLottyInfo("C://aq_envlopsDetilConfig.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("*********奖品详相关信息业务初始化完成***************");
		
		logger.info("*********开始初始化微信业务************");
		
		try {
			Properties props = new Properties();
			InputStream in = ConstantStr.class.getResourceAsStream("");
			props.load(in);
			
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

}
