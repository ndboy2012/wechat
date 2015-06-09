package com.weimeng.controller.web.register;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weimeng.dao.WechatRegisterDao;
import com.weimeng.entity.web.register.WechatBaseInfo;
import com.weimeng.util.AjaxJson;
import com.weimeng.util.DateProcess;
import com.weimeng.util.GenerateRandCode;

@Controller("registerController")
@RequestMapping("web/register/registerController")
public class RegisterController {
     
	private Logger logger = Logger.getLogger(RegisterController.class);
	
	@Resource(name="wechatRegisterImpl")
	private WechatRegisterDao registerImpl;
	
	@RequestMapping(value = "/verifyCode.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson verifyCode(String telephone, String openid,HttpServletRequest request) {
		logger.info(telephone+"***"+openid);
		AjaxJson ajaxJson = new AjaxJson();
		String msg = null;
		String randCode = null;
		try {
			//只要电话号码或者openid存在就无法登记
			if (registerImpl.isExitTel(telephone)||registerImpl.isExitOpenid(openid)) {
				msg = "您已经登记过了，不需要再次登记";
				logger.info("********" + msg + "*********");
			} else {
				randCode = GenerateRandCode.generateCode();
				request.getSession().setAttribute(telephone, randCode);
				msg = "请输入验证码:" + randCode;
				logger.info("********" + msg + "*********");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajaxJson.setMsg(msg);
		return ajaxJson;
	}  
	
	@RequestMapping(value = "/bindInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson bindInfo(String telephone, String openid,
			@RequestParam(required = true, defaultValue = "") String verify,
			HttpServletRequest request) {
		
		AjaxJson ajaxJson = new AjaxJson();
		String msg = null;
		String verifyCode = (String) request.getSession().getAttribute(telephone);
		WechatBaseInfo weiChatInfo = new WechatBaseInfo();
		weiChatInfo.setTelephone(telephone);
		weiChatInfo.setOpenId(openid);
		weiChatInfo.setBindDate(DateProcess.geTimestamp());

		try {
			if (verify.equals(verifyCode)) {   
				registerImpl.saveWeiChatInfo(weiChatInfo);
				msg = "success";
				logger.info("********" + msg + "********");			
			} else {
				msg = "验证码输入错误，请重新获取";
			    logger.info("********" + msg + "********");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajaxJson.setMsg(msg);
		request.getSession().removeAttribute(telephone);
		return ajaxJson;
	  }
  }
