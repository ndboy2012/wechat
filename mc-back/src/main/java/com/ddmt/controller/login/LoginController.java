package com.ddmt.controller.login;

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

import com.ddmt.bean.CommonAdminSession;
import com.ddmt.bean.UpperAdminSession;
import com.ddmt.constant.Constant;
import com.ddmt.entity.commonadmin.CommonAdminEntity;
import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.upperadmin.UpperAdminEntity;
import com.ddmt.entity.user.UserEntity;
import com.ddmt.service.user.UserServiceI;

@Controller
@RequestMapping("loginController")
public class LoginController {

	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params = "login")
	public ModelAndView login(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constant.UPPER_ADMIN_SESSION) != null) {
			return new ModelAndView(new RedirectView(
					"upperMainController.do?main"));
		} else if (request.getSession().getAttribute(
				Constant.COMMON_ADMIN_SESSION) != null) {
			return new ModelAndView(new RedirectView(
					"commonMainController.do?main"));
		}
		return new ModelAndView("login/login");
	}
	
	@RequestMapping(params = "checkuser")
	@ResponseBody
	public AjaxJson checkUser(UserEntity user,
			@RequestParam(required = true) String randCode,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		UserEntity userEntity = userService.checkUserEntity(user);
		String code = (String) request.getSession().getAttribute(
				Constant.SESSION_KEY_OF_RAND_CODE);
		if (!randCode.equalsIgnoreCase(code)) {
			j.setMsg("验证码错误");
			j.setSuccess(false);
			return j;
		}
		if (userEntity != null) {
			if (!userEntity.getStatus().equals(
					DictionaryBean.keyDict.get("admin_status_normal")
							.getValue())) {
				j.setMsg("该账号已经停用");
				j.setSuccess(false);
				return j;
			}
			systemService.addLog("用户" + userEntity.getUsername() + "登入成功",
					Globals.Log_Type_LOGIN, Globals.Log_Leavel_INFO);
			if(userEntity.getRole().equals(DictionaryBean.keyDict.get("admin_role_common").getValue())) {
				   CommonAdminEntity commonAdmin = userService.findCommonAdminByUserName(userEntity);
				   CommonAdminSession commonSession = new CommonAdminSession();
				   commonSession.setDistrict(commonAdmin.getDistrict());
				   commonSession.setId(commonAdmin.getId());
				   commonSession.setFirst(true);
				   commonSession.setRole(DictionaryBean.keyDict.get(Constant.ADMIN_ROLE_COMMON).getName());
				   commonSession.setUsername(commonAdmin.getUsername());
				   request.getSession().setAttribute(Constant.COMMON_ADMIN_SESSION, commonSession);
			} else {
				UpperAdminEntity upperAdmin = userService.findUpperAdminByUserName(userEntity);
				UpperAdminSession adminSession = new UpperAdminSession();
				adminSession.setId(upperAdmin.getId());
				adminSession.setUsername(upperAdmin.getUsername());
				adminSession.setLoginFirst(true);
				adminSession.setRole(DictionaryBean.keyDict.get(Constant.ADMIN_ROLE_UPPER).getName());
				request.getSession().setAttribute(Constant.UPPER_ADMIN_SESSION,
						adminSession);
			}
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
		
		if (request.getSession().getAttribute(Constant.ADMIN_ROLE_COMMON) != null) {
			request.getSession().removeAttribute(Constant.ADMIN_ROLE_COMMON);
		}
		return new ModelAndView("login/login");
	}
	
	
}
