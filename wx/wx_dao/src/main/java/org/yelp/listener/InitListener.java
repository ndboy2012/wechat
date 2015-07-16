package org.yelp.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.yelp.constant.CommonConstant;
import org.yelp.constant.WechatConstant;

public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				WebApplicationContext webApplicationContext = WebApplicationContextUtils
						.getWebApplicationContext(event.getServletContext());
				Properties props = new Properties();
				InputStream in = InitListener.class.getResourceAsStream("/wechatConstant.properties");
				try {
					props.load(in);
					CommonConstant.MESSAGE_CONTENT = props.getProperty("message_content");	
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	     /*******************初始化秒杀**************************************************/
				in = InitListener.class.getResourceAsStream("/seckill.properties");
				try {
					props.load(in);
					WechatConstant.TimeInvalid = props.getProperty("TimeInvalid");
					WechatConstant.OutTime = props.getProperty("OutTime");
					WechatConstant.HasGotOne = props.getProperty("HasGetOne");
					WechatConstant.SoldOut = props.getProperty("SoldOut");
					WechatConstant.SeckillPub = props.getProperty("SeckillPub");
					WechatConstant.KillKeyWordAsk = props.getProperty("KillKeyWordAsk");
					WechatConstant.KillKeyWordRep = props.getProperty("KillKeyWordRep");
					WechatConstant.KillTimeDay = props.getProperty("KillTimeDay");
				    WechatConstant.KillTimeHourBef = props.getProperty("KillTimeHourBef");
				    WechatConstant.KillTimeHourAft = props.getProperty("KillTimeHourAft");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
