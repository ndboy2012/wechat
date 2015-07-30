package com.ddmt.controller.common.leave;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
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

import com.ddmt.bean.CommonAdminSession;
import com.ddmt.bean.UpperAdminSession;
import com.ddmt.constant.Constant;
import com.ddmt.controller.upper.leave.LeaveController;
import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.district.DistrictEntity;
import com.ddmt.entity.leave.LeaveEntity;
import com.ddmt.service.leave.LeaveServiceI;

@RequestMapping("commonLeaveController")
@Controller
public class CommonLeaveController {

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

	@RequestMapping(params = "leave")
	public ModelAndView leave(HttpServletRequest request) {
		request.setAttribute("propertyReplace", List2Replace.listToReplaceStr(DictionaryBean.allDicts.get("leave_property"),"name","value"));
		return new ModelAndView("commonadmin/leave/leaveList");
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
		CommonAdminSession commonAdmin = (CommonAdminSession) request
				.getSession().getAttribute(Constant.COMMON_ADMIN_SESSION);
		CriteriaQuery cq = new CriteriaQuery(LeaveEntity.class, dataGrid);
		cq.add(Restrictions.or(Restrictions.in("target", new String[] { commonAdmin
				.getDistrict().getDistrictCode() }), Restrictions.in("property", new String[]{"2"})));
		
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				leave, request.getParameterMap());
		this.leaveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 留言信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LeaveEntity leave, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(leave.getId())) {
			leave = leaveService.getEntity(LeaveEntity.class, leave.getId());
			if(!StringUtil.isNotEmpty(leave.getTarget())) {
				req.setAttribute("msgType", "public");
			}
			req.setAttribute("leavePage", leave);
		}
		return new ModelAndView("commonadmin/leave/myleave");
	}
	
	@RequestMapping(params = "myleave")
	public ModelAndView myleave(HttpServletRequest request) {
		request.setAttribute("handlerReplace", List2Replace.listToReplaceStr(
				DictionaryBean.allDicts.get("leave_handler"), "name", "value"));
		return new ModelAndView("commonadmin/leave/myleaveList");
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
	public void mydatagrid(LeaveEntity leave, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CommonAdminSession commonAdmin = (CommonAdminSession) request
				.getSession().getAttribute(Constant.COMMON_ADMIN_SESSION);
		CriteriaQuery cq = new CriteriaQuery(LeaveEntity.class, dataGrid);
		System.out.println(commonAdmin.getDistrict().getDistrictCode());
		leave.setSources(commonAdmin.getDistrict().getDistrictCode());
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				leave, request.getParameterMap());
		this.leaveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
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
		CommonAdminSession commonAdmin = (CommonAdminSession) request.getSession()
				.getAttribute(Constant.COMMON_ADMIN_SESSION);
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
			leave.setProperty("3");
			leave.setSources(commonAdmin.getDistrict().getDistrictCode());
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
	
	@RequestMapping(params="mark")
	@ResponseBody
	public AjaxJson hanlder(String id) {
	   AjaxJson j = new AjaxJson();
	   leaveService.bookHandler(id);
	   j.setMsg("标记成功");
	   return j;
	}

}
