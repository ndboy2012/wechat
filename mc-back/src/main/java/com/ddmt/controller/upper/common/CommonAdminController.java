package com.ddmt.controller.upper.common;

import java.util.Date;

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
import org.jeecgframework.core.util.List2Replace;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.ddmt.constant.Constant;
import com.ddmt.entity.commonadmin.CommonAdminEntity;
import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.user.UserEntity;
import com.ddmt.service.commonadmin.CommonAdminServiceI;
import com.ddmt.util.MD5Util;

/**
 * @Title: Controller
 * @Description: 二级管理员基本信息
 * @author yelp
 * @date 2015-01-16 13:34:36
 * @version V1.0
 * 
 */
@Controller("upper_CommonAdminController")
@RequestMapping("commonController")
public class CommonAdminController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CommonAdminController.class);

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
		request.setAttribute("statusReplace", List2Replace.listToReplaceStr(
				DictionaryBean.allDicts.get("admin_status"), "name", "value"));
		request.setAttribute("roleReplace", List2Replace.listToReplaceStr(
				DictionaryBean.allDicts.get("admin_role"), "name", "value"));
		request.setAttribute("districtReplace", List2Replace.listToReplaceStr(
				commonAdminService.queryAllDistrict(), "districtName",
				"districtCode"));
		return new ModelAndView("upperadmin/common/commonAdminList");
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
	public void datagrid(CommonAdminEntity commonAdmin,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CommonAdminEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				commonAdmin, request.getParameterMap());
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
	public AjaxJson del(CommonAdminEntity commonAdmin,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		commonAdmin = systemService.getEntity(CommonAdminEntity.class,
				commonAdmin.getId());
		message = "二级管理员基本信息删除成功";
		commonAdminService.delete(commonAdmin);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

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
	public AjaxJson save(CommonAdminEntity commonAdmin,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Date curDate = new Date(System.currentTimeMillis());
		if (StringUtil.isNotEmpty(commonAdmin.getId())) {
			message = "二级管理员基本信息更新成功";
			CommonAdminEntity t = commonAdminService.get(
					CommonAdminEntity.class, commonAdmin.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(commonAdmin, t);
				commonAdmin.setUpdateTime(curDate); // 设置更新时间
				commonAdminService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "二级管理员基本信息更新失败";
			}
		} else {
			message = "二级管理员基本信息添加成功";
			commonAdmin.setRole(DictionaryBean.keyDict.get("admin_role_common")
					.getValue()); // 设置角色
			commonAdmin.setDistrict(commonAdminService
					.findDistrictInfoByCode(commonAdmin.getDistrict()
							.getDistrictCode()));
			commonAdmin.setCreateTime(curDate);
			commonAdmin.setUpdateTime(curDate);
			UserEntity user = new UserEntity();
			user.setPassword(MD5Util.getMD5String(Constant.COMMON_INIT_PASSWORD));
			user.setRole(DictionaryBean.keyDict.get("admin_role_common").getValue());
			user.setStatus(commonAdmin.getStatus());
			user.setUsername(commonAdmin.getUsername());
			commonAdminService.save(commonAdmin);
			user.setUserId(commonAdmin.getId());
			commonAdminService.save(user);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
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
	public ModelAndView addorupdate(CommonAdminEntity commonAdmin,
			HttpServletRequest req) {
		req.setAttribute("districtList", commonAdminService.queryAllDistrict()); // 查询出所有地区
		req.setAttribute("statusList",
				DictionaryBean.allDicts.get("admin_status"));
		if (StringUtil.isNotEmpty(commonAdmin.getId())) {
			commonAdmin = commonAdminService.getEntity(CommonAdminEntity.class,
					commonAdmin.getId());
			System.out.println(commonAdmin.getContent());
			req.setAttribute("commonAdminPage", commonAdmin);
		}
		return new ModelAndView("upperadmin/common/commonAdmin");
	}

	@RequestMapping(params = "forbid")
	@ResponseBody
	public AjaxJson forbid(String id) {
		AjaxJson j = new AjaxJson();
		if (commonAdminService.forbidAdminById(id)) {
			j.setMsg("用户禁止成功");
			j.setSuccess(true);
		} else {
			j.setSuccess(false);
		}
		return j;
	}

	@RequestMapping(params = "allow")
	@ResponseBody
	public AjaxJson allow(String id) {
		AjaxJson j = new AjaxJson();
		if (commonAdminService.allowAdminById(id)) {
			j.setMsg("用户启用成功");
			j.setSuccess(true);
		} else {
			j.setSuccess(false);
		}
		return j;
	}
	
	@RequestMapping(params="reset")
	@ResponseBody
	public AjaxJson resetPwd(CommonAdminEntity admin) {
		AjaxJson j = new AjaxJson();
		commonAdminService.changePwd(admin, Constant.COMMON_INIT_PASSWORD);
		j.setMsg("密码重置成功");
		return j;
	}
}
