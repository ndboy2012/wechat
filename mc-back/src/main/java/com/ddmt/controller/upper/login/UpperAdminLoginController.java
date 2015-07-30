/*package com.ddmt.controller.upper.login;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ddmt.bean.UpperAdminSession;
import com.ddmt.constant.Constant;
import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.upperadmin.UpperAdminEntity;
import com.ddmt.service.upperadmin.UpperAdminServiceI;

@Controller
@RequestMapping("upperAdminLoginController")
public class UpperAdminLoginController {

	@Autowired
	private UpperAdminServiceI upperAdminService;

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "login")
	public ModelAndView login(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constant.UPPER_ADMIN_SESSION) != null) {
			return new ModelAndView(new RedirectView(
					"upperMainController.do?main"));
		}
		return new ModelAndView("upperadmin/login/login");
	}

	@RequestMapping(params = "checkuser")
	@ResponseBody
	public AjaxJson checkUser(UpperAdminEntity admin,
			@RequestParam(required = true) String randCode,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//UpperAdminEntity adminEntity = upperAdminService.checkUpperAdmin(admin);
		String code = (String) request.getSession().getAttribute(
				Constant.SESSION_KEY_OF_RAND_CODE);
		if (!randCode.equalsIgnoreCase(code)) {
			j.setMsg("验证码错误");
			j.setSuccess(false);
			return j;
		}
		if (adminEntity != null) {
			if (!adminEntity.getStatus().equals(
					DictionaryBean.keyDict.get("admin_status_normal")
							.getValue())) {
				j.setMsg("该账号已经停用");
				j.setSuccess(false);
				return j;
			}
			systemService.addLog("用户" + admin.getUsername() + "登入成功",
					Globals.Log_Type_LOGIN, Globals.Log_Leavel_INFO);
			UpperAdminSession adminSession = new UpperAdminSession();
			adminSession.setId(adminEntity.getId());
			adminSession.setUsername(adminEntity.getUsername());
			adminSession.setRole(DictionaryBean.keyDict.get(Constant.ADMIN_ROLE_UPPER).getName());
			request.getSession().setAttribute(Constant.UPPER_ADMIN_SESSION,
					adminSession);
			j.setSuccess(true);
		} else {
			j.setMsg("用户名或者密码错误");
			j.setSuccess(false);
		}
		return j;
	}

	@RequestMapping(params = "logout")
	public ModelAndView loginout(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constant.UPPER_ADMIN_SESSION) != null) {
			request.getSession().removeAttribute(Constant.UPPER_ADMIN_SESSION);
		}
		return new ModelAndView("upperadmin/login/login");
	}

}
*/