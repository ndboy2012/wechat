package com.weimeng.controller.web.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weimeng.dao.AdminBaseDao;
import com.weimeng.entity.web.Admin.AdminBaseInfo;
import com.weimeng.util.AjaxJson;
import com.weimeng.util.ConstantStr;

@Controller("adminLoginController")
@RequestMapping("adminLoginController")
public class AdminLoginController {
    
	private Logger logger = Logger.getLogger(AdminLoginController.class);
	
	@Resource(name="adminBaseImpl")
	private AdminBaseDao adminBaseImpl;

	/*
	 * 用户登入验证
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	private ModelAndView login(AdminBaseInfo admin) {
		return new ModelAndView("web/admin/login");
	}
	
	@RequestMapping(value="/validateLogin.do",method=RequestMethod.GET)
	@ResponseBody
	public AjaxJson validateLogin(AdminBaseInfo admin,HttpServletRequest request) {
		logger.info(admin.getUsername()+"******"+admin.getPassword());
		AjaxJson ajax = new AjaxJson();
		String msg = "false";
	    try {
			 if(adminBaseImpl.isExitAdmin(admin)) {
				logger.info("验证成功");
				adminBaseImpl.updateLastLoginTime(admin);
				request.getSession().setAttribute(ConstantStr.SESSION_ADMIN, admin);
				msg  = "true";
			 } 
		} catch (Exception e) {
			e.printStackTrace();
		}
	    ajax.setMsg(msg);
		return ajax;
	}
	
	@RequestMapping(value="/loginSuccess.do",method=RequestMethod.GET)
	public ModelAndView loginSuccess(HttpServletRequest request) {
		
		AdminBaseInfo admin = (AdminBaseInfo) request.getSession().getAttribute(ConstantStr.SESSION_ADMIN);
		if(admin!=null) {
		   logger.info("验证成功，准备跳转");
		   return new ModelAndView("web/admin/main");
		} else {
			logger.info("强制登入");
			return new ModelAndView("web/admin/login");
		}
	}
}
