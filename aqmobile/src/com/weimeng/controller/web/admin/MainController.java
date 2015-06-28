package com.weimeng.controller.web.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.weimeng.dao.AdminMainDao;
import com.weimeng.entity.web.Admin.AdminBaseInfo;
import com.weimeng.util.ConstantStr;

@Controller("mainController")
@RequestMapping("mainController")
public class MainController {

	private Logger logger = Logger.getLogger(MainController.class);

	@Resource(name = "adminMainImpl")
	private AdminMainDao adminMainImpl;

	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		AdminBaseInfo admin = (AdminBaseInfo) request.getSession()
				.getAttribute(ConstantStr.SESSION_ADMIN);
		if (admin != null) {
			try {
				int totalCount = adminMainImpl.getTotalRegisterCount();
				request.getSession().setAttribute("totalRegisterCount", totalCount);
				int todayCount = adminMainImpl.getTodayRegisterCount();
				System.out.println(todayCount);
				request.getSession().setAttribute("todayRegisterCount", todayCount);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ModelAndView("web/admin/home");
		} else {
			logger.info("强制登入");
			return new ModelAndView("web/admin/login");
		}
	}
}
