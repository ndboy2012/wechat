package com.ddmt.controller.upper.upperadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddmt.bean.UpperAdminSession;
import com.ddmt.constant.Constant;
import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.dictionary.DictionaryEntity;
import com.ddmt.entity.upperadmin.UpperAdminEntity;
import com.ddmt.service.upperadmin.UpperAdminServiceI;

/**
 * @Title: Controller
 * @Description: 超级管理员基本信息
 * @author yelp
 * @date 2015-01-16 10:45:00
 * @version V1.0
 * 
 */
@Controller("upper_UpperAdminController")
@RequestMapping("upperAdminController")
public class UpperAdminController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UpperAdminController.class);

	@Autowired
	private UpperAdminServiceI upperAdminService;
	@Autowired
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 超级管理员基本信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upperAdmin")
	public ModelAndView upperAdmin(HttpServletRequest request) {
		return new ModelAndView("com/ddmt/upperadmin/upperAdminList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(UpperAdminEntity upperAdmin,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(UpperAdminEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				upperAdmin, request.getParameterMap());
		this.upperAdminService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除超级管理员基本信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(UpperAdminEntity upperAdmin, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		upperAdmin = systemService.getEntity(UpperAdminEntity.class,
				upperAdmin.getId());
		message = "超级管理员基本信息删除成功";
		upperAdminService.delete(upperAdmin);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加超级管理员基本信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(UpperAdminEntity upperAdmin, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(upperAdmin.getId())) {
			message = "超级管理员基本信息更新成功";
			UpperAdminEntity t = upperAdminService.get(UpperAdminEntity.class,
					upperAdmin.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(upperAdmin, t);
				upperAdminService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "超级管理员基本信息更新失败";
			}
		} else {
			message = "超级管理员基本信息添加成功";
			upperAdminService.save(upperAdmin);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 超级管理员基本信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(UpperAdminEntity upperAdmin,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(upperAdmin.getId())) {
			upperAdmin = upperAdminService.getEntity(UpperAdminEntity.class,
					upperAdmin.getId());
			req.setAttribute("upperAdminPage", upperAdmin);
		}
		return new ModelAndView("com/ddmt/upperadmin/upperAdmin");
	}

	@RequestMapping(params = "info")
	public ModelAndView info(UpperAdminEntity upperAdmin, HttpServletRequest req) {
		UpperAdminSession adminSession = (UpperAdminSession) req.getSession()
				.getAttribute(Constant.UPPER_ADMIN_SESSION);
		upperAdmin = upperAdminService.getEntity(UpperAdminEntity.class,
				adminSession.getId());

		List<DictionaryEntity> dic = DictionaryBean.allDicts
				.get("admin_status");
		for (int i = 0; i < dic.size(); i++) {
			DictionaryEntity dictionary = dic.get(i);
			if (dictionary.getValue().equals(upperAdmin.getStatus())) {
				req.setAttribute("admin_status", dictionary.getName());
				break;
			}
		}

		req.setAttribute("admin_role",
				DictionaryBean.keyDict.get(Constant.ADMIN_ROLE_UPPER).getName());
		req.setAttribute("upperAdminPage", upperAdmin);

		return new ModelAndView("upperadmin/upperadmin/upperAdmin");
	}
    
	@RequestMapping(params = "changePwd")
	public ModelAndView changePwd(UpperAdminEntity upperAdmin,
			HttpServletRequest req) {
       return new ModelAndView("upperadmin/upperadmin/changePwd");
	}
	
	@RequestMapping(params="changePassword")
	@ResponseBody
	public AjaxJson chagePassword(UpperAdminEntity admin,String password,String newPassword,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (!upperAdminService.checkPwd(admin,password)) {
			j.setMsg("旧密码不正确，请重新输入");
			j.setSuccess(false);
			return j;
		}
		if(upperAdminService.changePwd(admin, newPassword)) {
			j.setMsg("密码修改成功");
			j.setSuccess(true);
			request.getSession().removeAttribute(Constant.UPPER_ADMIN_SESSION);
		} else {
			j.setMsg("修改密码失败");
			j.setSuccess(false);
		}
		return j;
	}
}
