package com.weimeng.controller.wechat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weimeng.impl.wechat.CoreService;
import com.weimeng.util.SignUtil;

@Controller("wechatController")
@RequestMapping("weichatController")
public class WechatControlller {
   
    private Logger logger = Logger.getLogger(WechatControlller.class);
	
	@RequestMapping(method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public void checkIn(HttpServletRequest request, HttpServletResponse respone)
			throws ServletException, IOException {

		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = respone.getWriter();
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	
	@Resource(name="coreService")
	private CoreService coreService;
	
	@RequestMapping(method = { RequestMethod.POST }, produces = "application/xml;charset=UTF-8")
	public void receceivedMessage(HttpServletRequest request,
			HttpServletResponse respone) throws ServletException, IOException,
			Exception {		
		/**
		 * 先验证用户是否已经绑定了号码，如果没有绑定则
		 */		
		logger.info("接收到用户消息请求");
		request.setCharacterEncoding("UTF-8");
		respone.setCharacterEncoding("UTF-8");
		String respMessage = coreService.processRequest(request);
		PrintWriter out = respone.getWriter();
		out.print(respMessage);
		out.close();
		out = null;
	}
}
