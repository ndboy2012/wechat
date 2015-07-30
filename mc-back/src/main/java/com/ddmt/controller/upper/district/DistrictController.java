package com.ddmt.controller.upper.district;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.List2Replace;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.district.DistrictEntity;
import com.ddmt.service.district.DistrictServiceI;

/**
 * @Title: Controller
 * @Description: 地区基本信息
 * @author yelp
 * @date 2015-01-16 13:42:08
 * @version V1.0
 * 
 */
@Controller("upper_DistrictController")
@RequestMapping("upperDistrictController")
public class DistrictController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(DistrictController.class);

	@Autowired
	private DistrictServiceI districtService;
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
	 * 地区基本信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "district")
	public ModelAndView district(HttpServletRequest request) {
		return new ModelAndView("upperadmin/district/districtList");
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
	public void datagrid(DistrictEntity district, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DistrictEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				district, request.getParameterMap());
		this.districtService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除地区基本信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DistrictEntity district, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		district = systemService.getEntity(DistrictEntity.class,
				district.getId());
		message = "地区基本信息删除成功";
		districtService.delete(district);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加地区基本信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DistrictEntity district, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Date curDate = new Date(System.currentTimeMillis());
		if (StringUtil.isNotEmpty(district.getId())) {
			message = "地区基本信息更新成功";
			DistrictEntity t = districtService.get(DistrictEntity.class,
					district.getId());
			try {
				district.setUpdateTime(curDate);
				MyBeanUtils.copyBeanNotNull2Bean(district, t);
				districtService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "地区基本信息更新失败";
			}
		} else {
			message = "地区基本信息添加成功";
			district.setCreateTime(curDate);
			district.setUpdateTime(curDate);
			districtService.save(district);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		district.setUpdateTime(curDate);
		j.setMsg(message);
		return j;
	}

	/**
	 * 地区基本信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DistrictEntity district,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(district.getId())) {
			district = districtService.getEntity(DistrictEntity.class,
					district.getId());
			req.setAttribute("formType", "update");
			req.setAttribute("districtPage", district);
		}
		return new ModelAndView("upperadmin/district/district");
	}
}
