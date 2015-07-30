package com.ddmt.controller.common.login;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddmt.bean.CommonAdminSession;
import com.ddmt.constant.Constant;
import com.ddmt.service.main.CommonMainServiceI;

@Controller
@RequestMapping("commonMainController")
public class CommonMainController {
    
	@Autowired
	public CommonMainServiceI commonMainService;
	
	@RequestMapping(params = "main")
	public ModelAndView main() {
		return new ModelAndView("commonadmin/main/main");
	}

	@RequestMapping(params = "left")
	public ModelAndView left(HttpServletRequest request) {
		return new ModelAndView("commonadmin/main/left");
	}

	@RequestMapping(params = "home")
	public ModelAndView home() {
		return new ModelAndView("commonadmin/main/home");
	}
	
	@RequestMapping(params="queryLeave")
	@ResponseBody
	public AjaxJson queryLeave(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		CommonAdminSession admin = (CommonAdminSession) request.getSession().getAttribute(Constant.COMMON_ADMIN_SESSION);
		if(admin.isFirst()) {
			int count = commonMainService.queryLeaveMessage(admin.getDistrict().getDistrictCode());
			if(count>0) {
				j.setMsg("您有"+count+"条通告,请注意查看");
			} else {
				j.setMsg("暂未收到任何通告");
			}
			j.setSuccess(true);
			admin.setFirst(false);
		} else {
			j.setSuccess(false);
		}
		return j;
	}
	
	@RequestMapping(params="before")
	@ResponseBody
	public AjaxJson enterBefore(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		CommonAdminSession admin = (CommonAdminSession) request.getSession().getAttribute(Constant.COMMON_ADMIN_SESSION);
		String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/mc-front/firstPageController/first.do?districtCode="+admin.getDistrict().getDistrictCode();
		System.out.println(url);
		j.setMsg(url);
		return j;
	}

}
