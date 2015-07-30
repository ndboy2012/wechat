package com.ddmt.controller.upper.login;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddmt.bean.UpperAdminSession;
import com.ddmt.constant.Constant;
import com.ddmt.service.main.CommonMainServiceI;


@Controller("upper_mainController")
@RequestMapping("upperMainController.do")
public class MainController {
   
    private boolean loginTime=true;
	
	@Autowired
	public CommonMainServiceI commonMainService;
	
	@RequestMapping(params = "main")
	public ModelAndView main() {
		return new ModelAndView("upperadmin/main/main");
	}

	@RequestMapping(params = "left")
	public ModelAndView left(HttpServletRequest request) {
		return new ModelAndView("upperadmin/main/left");
	}

	@RequestMapping(params = "home")
	public ModelAndView home() {
		return new ModelAndView("upperadmin/main/home");
	}
	
	@RequestMapping(params="queryLeave")
	@ResponseBody
	public AjaxJson queryLeave(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		UpperAdminSession admin= (UpperAdminSession) request.getSession().getAttribute(Constant.UPPER_ADMIN_SESSION);
		if(admin.isLoginFirst()) {
			int count = commonMainService.queryLeaveMessage();
			if(count>0) {
				j.setMsg("您有"+count+"条留言未处理");
			} else {
				j.setMsg("暂时未收到留言");
			}
			j.setSuccess(true);
			admin.setLoginFirst(false);
		} else {
			j.setSuccess(false);
		}
		return j;
	}
}
