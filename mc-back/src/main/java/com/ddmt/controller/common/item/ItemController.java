package com.ddmt.controller.common.item;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.ddmt.bean.CommonAdminSession;
import com.ddmt.constant.Constant;
import com.ddmt.entity.article.ArticleEntity;
import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.item.ItemEntity;
import com.ddmt.service.item.CommonItemServiceI;

/**
 * @Title: Controller
 * @Description: 类目基本信息
 * @author yelp
 * @date 2015-01-16 19:40:45
 * @version V1.0
 * 
 */
@Controller("common_ItemController")
@RequestMapping("/commonItemController")
public class ItemController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ItemController.class);

	@Autowired
	private CommonItemServiceI commonItemService;
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
	 * 类目基本信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "item")
	public ModelAndView item(HttpServletRequest request) {
		// 类目类型替换
		request.setAttribute("itemReplace", List2Replace.listToReplaceStr(
				DictionaryBean.allDicts.get("item_type"), "name", "value"));
		// 类目状态替换
		request.setAttribute("statusReplace", List2Replace.listToReplaceStr(
				DictionaryBean.allDicts.get("item_status"), "name", "value"));

		request.setAttribute("isPublicReplace", List2Replace.listToReplaceStr(
				DictionaryBean.allDicts.get("item_is_public"), "name", "value"));
		return new ModelAndView("commonadmin/item/itemList");
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
	public void datagrid(ItemEntity item, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ItemEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				item, request.getParameterMap());
		this.commonItemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除类目基本信息
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ItemEntity item, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		item = systemService.getEntity(ItemEntity.class, item.getId());
		message = "类目基本信息删除成功";
		commonItemService.delete(item);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(params = "treegried")
	@ResponseBody
	public List<Map<String, Object>> loadItem(HttpServletRequest request) {
		CommonAdminSession common = (CommonAdminSession) request.getSession().getAttribute(Constant.COMMON_ADMIN_SESSION);
		return commonItemService.queryTreeGridTree(common.getDistrict().getDistrictCode());
	}
	
	@RequestMapping(params = "addArticle")
	public ModelAndView addArticle(HttpServletRequest req,ItemEntity item) {
		List<ItemEntity> listEntity = commonItemService.findItemsByParentId("0");
		if (StringUtil.isNotEmpty(item.getId())) {
			item = commonItemService.getEntity(ItemEntity.class, item.getId());
			req.setAttribute("itemPage", item);
			if (item.getLevel() == 3) { //说明有三级菜单
			/*	req.setAttribute("secondPage", commonItemService
						.findItemsByParentId(commonItemService.findParticalItemById(
								item.getParentId()).getParentId()));*/
				req.setAttribute("secondPage", commonItemService.findItemsCanEnitByParentId(commonItemService.findParticalItemById(
								item.getParentId()).getParentId()));
				req.setAttribute("second", item.getParentId());
				req.setAttribute("first",
						commonItemService.findParticalItemById(item.getParentId()).getParentId());
			} else if (item.getLevel() == 2) {
				req.setAttribute("first", item.getParentId());
				req.setAttribute("secondPage",
						commonItemService.findItemsByParentId(item.getParentId()));
			}
		} else {
			if(listEntity.size()>0) {
				req.setAttribute("secondPage",
						commonItemService.findItemsCanEnitByParentId(listEntity.get(0).getId()));
				/*req.setAttribute("secondPage", commonItemService.findItemsCanEnitByParentId(commonItemService.findParticalItemById(
						listEntity.get(0).getParentId()).getParentId()));*/
			} else {
				return new ModelAndView("upperadmin/item/error");
			}
		}
		/*List<ItemEntity> listEntity = commonItemService.findItemsByParentId("0");*/
		req.setAttribute("itemReplace", listEntity);
		return new ModelAndView("commonadmin/item/article2");
	}
	
	@RequestMapping(params="articleSave")
	@ResponseBody
	public AjaxJson articleSave(ItemEntity item, String content,
			String contentTxt, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(item.getId())) {
			message = "内容信息添加成功";
			ItemEntity t = commonItemService.get(ItemEntity.class, item.getId());
			ArticleEntity a = t.getArticle();
			a.setContent(content);
			a.setContentTxt(contentTxt);
			t.setArticle(a);
			if (item.getParentId().equals("0")) {
				item.setLevel(1); // 设置为目录级
			} else {
				item.setLevel(commonItemService.findParticalItemById(item.getParentId())
						.getLevel() + 1); // 设置为内容级别
			}
			try {
				MyBeanUtils.copyBeanNotNull2Bean(item, t);
				commonItemService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "类目基本信息更新失败";
			}
		} else {
			CommonAdminSession sessionAdmin = (CommonAdminSession) req.getSession()
					.getAttribute(Constant.COMMON_ADMIN_SESSION);
			Date curDate = new Date(System.currentTimeMillis());
			message = "内容信息添加成功";
			item.setCreateTime(curDate);
			item.setUpdateTime(curDate);
			item.setBelongs(sessionAdmin.getDistrict().getDistrictCode());
			item.setCreator(sessionAdmin.getId());
			item.setType(Constant.Article);
			item.setStatus(DictionaryBean.keyDict.get("item_status_normal")
					.getValue());
			if (item.getParentId().equals("0")) {
				item.setLevel(1); // 设置为目录级
			} else {
				item.setLevel(commonItemService.findParticalItemById(item.getParentId())
						.getLevel() + 1); //设置为内容级别
			}
			ArticleEntity article = new ArticleEntity();
			article.setContent(content);
			article.setContentTxt(contentTxt);
			article.setParentId(item.getParentId());
			article.setBelongId(DictionaryBean.keyDict.get("item_belongs_private")
					.getValue());
			commonItemService.save(article);
			item.setArticle(article);
			commonItemService.save(item);
		}
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(params="reqItems")
	@ResponseBody
	public AjaxJson reqItems(String parentId) {
		AjaxJson j = new AjaxJson();
		j.setObj(commonItemService.findItemsCanEnitByParentId(parentId));
		j.setSuccess(commonItemService.itemCanEdit(parentId));
		return j;
	}
	
	@RequestMapping(params = "preview")
	public ModelAndView preview(String id, HttpServletRequest req) {
		req.setAttribute("article", commonItemService.findArticleByItemId(id));
		return new ModelAndView("upperadmin/item/preview");
	}
}
