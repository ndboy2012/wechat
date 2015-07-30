package com.ddmt.controller.upper.leave;

import java.util.Date;
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
import org.jeecgframework.core.util.List2Replace;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.ddmt.bean.UpperAdminSession;
import com.ddmt.constant.Constant;
import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.district.DistrictEntity;
import com.ddmt.entity.leave.LeaveEntity;
import com.ddmt.service.leave.LeaveServiceI;

/**
 * @Title: Controller
 * @Description: 留言信息
 * @author yelp
 * @date 2015-01-17 16:44:18
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/upperLeaveController")
public class LeaveController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(LeaveController.class);

	@Autowired
	private LeaveServiceI leaveService;
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
	 * 留言信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "leave")
	public ModelAndView leave(HttpServletRequest request) {
		request.setAttribute("districtList", List2Replace.listToReplaceStr(
				leaveService.loadAll(DistrictEntity.class), "districtName",
				"districtCode"));
		request.setAttribute("propertyReplace", List2Replace.listToReplaceStr(
				DictionaryBean.allDicts.get("leave_property"), "name", "value"));
		request.setAttribute("handlerReplace", List2Replace.listToReplaceStr(
				DictionaryBean.allDicts.get("leave_handler"), "name", "value"));
		return new ModelAndView("upperadmin/leave/leaveList");
	}

	@RequestMapping(params = "myleave")
	public ModelAndView myleave(HttpServletRequest request) {
		request.setAttribute("districtList", List2Replace.listToReplaceStr(
				leaveService.loadAll(DistrictEntity.class), "districtName",
				"districtCode"));
		request.setAttribute("propertyReplace", List2Replace.listToReplaceStr(
				DictionaryBean.allDicts.get("leave_property"), "name", "value"));
		request.setAttribute("handlerReplace", List2Replace.listToReplaceStr(
				DictionaryBean.allDicts.get("leave_handler"), "name", "value"));
		return new ModelAndView("upperadmin/leave/myleaveList");
	}
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "mydatagrid")
	public void myDatagrid(LeaveEntity leave, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LeaveEntity.class, dataGrid);
		UpperAdminSession upperAdmin =(UpperAdminSession) request.getSession().getAttribute(Constant.UPPER_ADMIN_SESSION);
		leave.setSources(upperAdmin.getId());
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				leave, request.getParameterMap());
		this.leaveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
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
	public void datagrid(LeaveEntity leave, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LeaveEntity.class, dataGrid);
		leave.setProperty(DictionaryBean.keyDict.get("leave_property_upper").getValue());
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				leave, request.getParameterMap());
		this.leaveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除留言信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LeaveEntity leave, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		leave = systemService.getEntity(LeaveEntity.class, leave.getId());
		message = "留言信息删除成功";
		leaveService.delete(leave);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加留言信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LeaveEntity leave, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Date curDate = new Date(System.currentTimeMillis());
		UpperAdminSession upperAdmin = (UpperAdminSession) request.getSession()
				.getAttribute(Constant.UPPER_ADMIN_SESSION);
		if (StringUtil.isNotEmpty(leave.getId())) {
			message = "留言信息更新成功";
			LeaveEntity t = leaveService.get(LeaveEntity.class, leave.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(leave, t);
				leaveService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "留言信息更新失败";
			}
		} else {
			message = "留言信息添加成功";
			if(!StringUtil.isNotEmpty(leave.getTarget())) {
				leave.setProperty(DictionaryBean.keyDict.get("leave_property_all").getValue());
			} else {
				leave.setProperty(DictionaryBean.keyDict.get("leave_property_district").getValue());
			}
			leave.setSources(upperAdmin.getId());
			leave.setIsHandle(DictionaryBean.keyDict.get("leave_handler_no")
					.getValue());
			leave.setCreateTime(curDate);
			leaveService.save(leave);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 留言信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LeaveEntity leave, HttpServletRequest req) {
		req.setAttribute("msgType", "district");
		if (StringUtil.isNotEmpty(leave.getId())) {
			leave = leaveService.getEntity(LeaveEntity.class, leave.getId());
			if(!StringUtil.isNotEmpty(leave.getTarget())) {
				req.setAttribute("msgType", "public");
			}
			req.setAttribute("leavePage", leave);
		}
		req.setAttribute("districtList",
				leaveService.loadAll(DistrictEntity.class));
		return new ModelAndView("upperadmin/leave/myleave");
	}
	
	@RequestMapping(params = "publicMsg")
	public ModelAndView publicMsg(LeaveEntity leave, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(leave.getId())) {
			leave = leaveService.getEntity(LeaveEntity.class, leave.getId());
			req.setAttribute("leavePage", leave);
		}
		req.setAttribute("msgType", "public");
		req.setAttribute("districtList",
				leaveService.loadAll(DistrictEntity.class));
		return new ModelAndView("upperadmin/leave/myleave");
	}
	
	@RequestMapping(params="mark")
	@ResponseBody
	public AjaxJson hanlder(String id) {
	   AjaxJson j = new AjaxJson();
	   leaveService.bookHandler(id);
	   j.setMsg("标记成功");
	   return j;
	}
	
}
