package com.ddmt.controller.common.commonadmin;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.ddmt.bean.CommonAdminSession;
import com.ddmt.constant.Constant;
import com.ddmt.entity.commonadmin.CommonAdminEntity;
import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.dictionary.DictionaryEntity;
import com.ddmt.entity.upperadmin.UpperAdminEntity;
import com.ddmt.service.commonadmin.CommonAdminServiceI;

/**   
 * @Title: Controller
 * @Description: 二级管理员基本信息
 * @author yelp
 * @date 2015-01-16 13:34:36
 * @version V1.0   
 */
@Controller
@RequestMapping("commonAdminController")
public class CommonAdminController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CommonAdminController.class);

	@Autowired
	private CommonAdminServiceI commonAdminService;
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
	 * 二级管理员基本信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "commonAdmin")
	public ModelAndView commonAdmin(HttpServletRequest request) {
		return new ModelAndView("com/ddmt/commonadmin/commonAdminList");
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
	public void datagrid(CommonAdminEntity commonAdmin,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CommonAdminEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commonAdmin, request.getParameterMap());
		this.commonAdminService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除二级管理员基本信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(CommonAdminEntity commonAdmin, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		commonAdmin = systemService.getEntity(CommonAdminEntity.class, commonAdmin.getId());
		message = "二级管理员基本信息删除成功";
		commonAdminService.delete(commonAdmin);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加二级管理员基本信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(CommonAdminEntity commonAdmin, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(commonAdmin.getId())) {
			message = "二级管理员基本信息更新成功";
			CommonAdminEntity t = commonAdminService.get(CommonAdminEntity.class, commonAdmin.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(commonAdmin, t);
				commonAdminService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "二级管理员基本信息更新失败";
			}
		} else {
			message = "二级管理员基本信息添加成功";
			commonAdminService.save(commonAdmin);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 二级管理员基本信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(CommonAdminEntity commonAdmin, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(commonAdmin.getId())) {
			commonAdmin = commonAdminService.getEntity(CommonAdminEntity.class, commonAdmin.getId());
			req.setAttribute("commonAdminPage", commonAdmin);
		}
		return new ModelAndView("com/ddmt/commonadmin/commonAdmin");
	}
	
	@RequestMapping(params = "info")
	public ModelAndView info(CommonAdminEntity commonAdmin,HttpServletRequest req) {
		CommonAdminSession common = (CommonAdminSession) req.getSession().getAttribute(Constant.COMMON_ADMIN_SESSION);
		commonAdmin = commonAdminService.getEntity(CommonAdminEntity.class, common.getId());
		List<DictionaryEntity> allStatus = DictionaryBean.allDicts.get("admin_status");
		for(int i=0;i<allStatus.size();i++) {
			DictionaryEntity dic = allStatus.get(i);
			if(dic.getValue().equals(commonAdmin.getStatus())) {
				req.setAttribute("status", dic.getName());
			}
		}
		req.setAttribute("role", common.getRole());
		req.setAttribute("commonAdminPage", commonAdmin);
		return new ModelAndView("commonadmin/commonadmin/commonAdmin");
	}
	
	@RequestMapping(params = "changePwd")
	public ModelAndView changePwd(UpperAdminEntity upperAdmin,
			HttpServletRequest req) {
       return new ModelAndView("commonadmin/commonadmin/changePwd");
	}
	
	@RequestMapping(params="changePassword")
	@ResponseBody
	public AjaxJson chagePassword(CommonAdminEntity admin,String password,String newPassword,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (!commonAdminService.checkPwd(admin,password)) {
			j.setMsg("旧密码不正确，请重新输入");
			j.setSuccess(false);
			return j;
		}
		if(commonAdminService.changePwd(admin, newPassword)) {
			j.setMsg("密码修改成功");
			j.setSuccess(true);
			request.getSession().removeAttribute(Constant.COMMON_ADMIN_SESSION);
		} else {
			j.setMsg("修改密码失败");
			j.setSuccess(false);
		}
		return j;
	}
	
}
