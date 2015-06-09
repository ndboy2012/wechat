package com.wmsoft.common.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wmsoft.web.service.lotty.LotteryServiceI;
import com.wmsoft.wechat.constant.WechatConstant;


public class InitListener implements javax.servlet.ServletContextListener{
    
	private Logger logger = Logger.getLogger(InitListener.class);
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		logger.info("***********listener init**********");
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		
		logger.info("*********开始初始化抢红包奖品信息业务******");
		LotteryServiceI read = (LotteryServiceI) webApplicationContext
				.getBean("lotteryServiceImpl");
		try {
			read.storeLottyInfo("C://hf_envlopsDetilConfig.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("*********红包初始化完成***************");
		
		logger.info("开始初始化合肥移动登记信息相关");
		Properties props = new Properties();
		InputStream in = WechatConstant.class.getResourceAsStream("/wechatConstant.properties");
		try {
			props.load(in);
			WechatConstant.validate_success = props.getProperty("Register_validate_success");
			WechatConstant.validate_failed = props.getProperty("Register_validate_failed");
			WechatConstant.register_success = props.getProperty("Register_success");
			WechatConstant.register_describe = props.getProperty("Register_describe");
            WechatConstant.SmsContent = props.getProperty("Message_content");			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
