package com.ddmt.service.impl.main;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.DataGridReturn;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.easyui.Autocomplete;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.springframework.stereotype.Service;

import com.ddmt.entity.leave.LeaveEntity;
import com.ddmt.service.main.CommonMainServiceI;

@Service("commonMainService")
public class CommonMainServiceImpl extends CommonServiceImpl implements
		CommonMainServiceI {

	@Override
	public int queryLeaveMessage(String districtCode) {
		return getSession()
				.createCriteria(LeaveEntity.class)
				.add(Restrictions.or(Restrictions.in("target",
						new String[] { districtCode }), Restrictions.in(
						"property", new String[] { "2" })))
				.add(Restrictions.in("isHandle", new String[] { "0" })).list()
				.size();
	}

	@Override
	public int queryLeaveMessage() {
		return getSession().createCriteria(LeaveEntity.class)
				.add(Restrictions.in("property", new String[] { "3" }))
				.add(Restrictions.in("isHandle", new String[] { "0" })).list()
				.size();
	}
}
