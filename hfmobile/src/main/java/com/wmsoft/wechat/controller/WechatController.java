package com.wmsoft.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wmsoft.wechat.service.MessageProcessI;
import com.wmsoft.wechat.service.RequestProcessI;
import com.wmsoft.wechat.util.SignUtil;


@Controller
@RequestMapping("wechatController")
public class WechatController {
     
	@Autowired
	private RequestProcessI requestProcessImpl;

	@Autowired
	private MessageProcessI messageProcessImpl;
	
	private Logger logger = Logger.getLogger(WechatController.class);

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

	@RequestMapping(method = { RequestMethod.POST }, produces = "application/xml;charset=UTF-8")
	public void messageReceive(HttpServletRequest request,
			HttpServletResponse respone) throws ServletException, IOException,
			Exception {
		
		request.setCharacterEncoding("UTF-8");
		respone.setCharacterEncoding("UTF-8");
		
		logger.info("接收到合肥移动用户消息");
		String message = messageProcessImpl.getRespMessage(requestProcessImpl
				.praseRequest(request));
		PrintWriter out = respone.getWriter();
		out.print(message);
		out.close();
		out = null;
	}
	 
} 
