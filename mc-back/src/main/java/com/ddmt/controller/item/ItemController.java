package com.ddmt.controller.item;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.io.ZipUtil;

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
import com.ddmt.bean.UpperAdminSession;
import com.ddmt.constant.Constant;
import com.ddmt.entity.article.ArticleEntity;
import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.item.ItemEntity;
import com.ddmt.entity.leave.LeaveEntity;
import com.ddmt.service.item.CommonItemServiceI;
import com.ddmt.service.item.ItemServiceI;

/**
 * @Title: Controller
 * @Description: 类目基本信息
 * @author yelp
 * @date 2015-01-16 19:40:45
 * @version V1.0i
 * 
 * 
 */
@Controller("upper_ItemController")
@RequestMapping("/upperItemController")
public class ItemController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ItemController.class);

	@Autowired
	private ItemServiceI itemService;
	@Autowired
	private SystemService systemService;
	private String message;
    
	private String article;
	
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
	
	@RequestMapping(params="isPub")
	@ResponseBody
	public AjaxJson updatePublic(String id,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		System.out.println("这里执行了");
		ItemEntity item = itemService.getEntity(ItemEntity.class, id);
		System.out.println(item.getTitle()+"这里也执行了");
		if(item.getIsPublic().equals(DictionaryBean.keyDict.get("item_is_open").getValue())) {
			itemService.changeIsPublic(id, DictionaryBean.keyDict.get("item_is_close").getValue());
		} else {
			itemService.changeIsPublic(id, DictionaryBean.keyDict.get("item_is_open").getValue());
		}
		return j;
	}
	
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
		
		return new ModelAndView("upperadmin/item/itemList");
	}
	
	//超级管理员查看各个单位文章情况
	@RequestMapping(params = "itemSecond")
	public ModelAndView itemSecond(HttpServletRequest request,String districtCode) {
		        // 类目类型替换
				request.setAttribute("itemReplace", List2Replace.listToReplaceStr(
						DictionaryBean.allDicts.get("item_type"), "name", "value"));
				// 类目状态替换
				request.setAttribute("statusReplace", List2Replace.listToReplaceStr(
						DictionaryBean.allDicts.get("item_status"), "name", "value"));

				request.setAttribute("isPublicReplace", List2Replace.listToReplaceStr(
						DictionaryBean.allDicts.get("item_is_public"), "name", "value"));
				
				request.setAttribute("districtList", itemService.queryAllDistrict()); // 查询出所有地区
				
				request.setAttribute("districtCode", districtCode);
				
				return new ModelAndView("upperadmin/item/itemSecondList");
		
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
		this.itemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除类目基本信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ItemEntity item, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		item = systemService.getEntity(ItemEntity.class, item.getId());
		message = "类目基本信息删除成功";
		itemService.delete(item);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}
	
	/**
	 * 删除类目基本信息
	 * @return
	 */
	@RequestMapping(params = "delSec")
	@ResponseBody
	public AjaxJson delSec(ItemEntity item, HttpServletRequest request,String districtCode) {
		AjaxJson j = new AjaxJson();
		UpperAdminSession admin= (UpperAdminSession) request.getSession().getAttribute(Constant.UPPER_ADMIN_SESSION);
		item = systemService.getEntity(ItemEntity.class, item.getId());
		message = "类目基本信息删除成功";
		itemService.delete(item);
		LeaveEntity leave = new LeaveEntity();
		
		leave.setContent("超级管理员删除了您所属单位中题目为<<"+item.getTitle()+">>的文章");
		leave.setIsHandle("0");
		leave.setTarget(item.getBelongs());
		Date curDate = new Date(System.currentTimeMillis());
		leave.setCreateTime(curDate);
		leave.setProperty("1");
		leave.setSources(admin.getId());
		leave.setTarget(districtCode);
		itemService.save(leave);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加类目基本信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ItemEntity item, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Date curDate = new Date(System.currentTimeMillis());
		UpperAdminSession admin = (UpperAdminSession) request.getSession()
				.getAttribute(Constant.UPPER_ADMIN_SESSION);
		if (StringUtil.isNotEmpty(item.getId())) {
			message = "类目基本信息更新成功";
			ItemEntity t = itemService.get(ItemEntity.class, item.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(item, t);
				itemService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "类目基本信息更新失败";
			}
		} else {
			int level = 0;
			if(!item.getParentId().equals("0")) {
				level = itemService.findParticalItemById(item.getParentId())
						.getLevel() + 1;
			}
			message = "类目基本信息添加成功";
			String[] items = item.getTitle().split(",");
			for (int i = 0; i < items.length; i++) {
				ItemEntity newItem = new ItemEntity();
				newItem.setTitle(items[i]);
				newItem.setStatus(item.getStatus());
				newItem.setBelongs(DictionaryBean.keyDict.get(
						"item_belongs_public").getValue()); // 管理员添加的文章或者目录公有
				newItem.setParentId(item.getParentId());
				newItem.setIsPublic(item.getIsPublic());
				newItem.setCreateTime(curDate);
				newItem.setUpdateTime(curDate);
				newItem.setCreator(admin.getId());
				newItem.setSort(1);
				if (item.getParentId().equals("0")) {
					newItem.setLevel(1);
				} else {
					newItem.setLevel(level);
				}
				newItem.setStatus(DictionaryBean.keyDict.get(
						"item_status_normal").getValue());
				newItem.setType(DictionaryBean.keyDict.get("item_type_button")
						.getValue());
				itemService.save(newItem);
			}
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 类目基本信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ItemEntity item, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(item.getId())) {
			item = itemService.getEntity(ItemEntity.class, item.getId());
			req.setAttribute("itemPage", item);
			req.setAttribute("itemStatus",
					DictionaryBean.allDicts.get("item_status"));
			req.setAttribute("formType", "update");
		} else {
			req.setAttribute("formType", "add");
		}
		req.setAttribute("firstItem", itemService.findItemsByParentId("0"));
		return new ModelAndView("upperadmin/item/item");
	}

	@RequestMapping(params = "treegried")
	@ResponseBody
	public List<Map<String, Object>> loadItem() {
		return itemService.queryTreeGridTree();
	}
	
	@RequestMapping(params = "Sectreegried")
	@ResponseBody
	public List<Map<String, Object>> loadSecondItem(String districtCode) {
		return itemService.queryTreeGridTree(districtCode);
	}

	@RequestMapping(params = "addArticle")
	public ModelAndView addArticle(ItemEntity item, HttpServletRequest req) {
		List<ItemEntity> listEntity = itemService.findItemsByParentId("0");
		if (StringUtil.isNotEmpty(item.getId())) {
			item = itemService.getEntity(ItemEntity.class, item.getId());
			req.setAttribute("itemPage", item);
			if (item.getLevel() == 3) { //说明有三级菜单
				req.setAttribute("secondPage", itemService
						.findItemsByParentId(itemService.findParticalItemById(
								item.getParentId()).getParentId()));
				req.setAttribute("second", item.getParentId());
				req.setAttribute("first",
						itemService.findParticalItemById(item.getParentId()).getParentId());
			} else if (item.getLevel() == 2) {
				req.setAttribute("first", item.getParentId());
				req.setAttribute("secondPage",
						itemService.findItemsByParentId(item.getParentId()));
			}
		} else {
			if(listEntity.size()>0) {
				req.setAttribute("secondPage",
						itemService.findItemsByParentId(listEntity.get(0).getId()));
			} else {
				return new ModelAndView("upperadmin/item/error");
			}
		}
		req.setAttribute("firstPage", listEntity);
		return new ModelAndView("upperadmin/item/article2");
	}
	
	@RequestMapping(params = "addArticleSec")
	public ModelAndView addArticleSec(ItemEntity item,String districtCode,HttpServletRequest req) {
		List<ItemEntity> listEntity = itemService.findItemsByParentId("0");
		if (StringUtil.isNotEmpty(item.getId())) {
			item = itemService.getEntity(ItemEntity.class, item.getId());
			req.setAttribute("itemPage", item);
			if (item.getLevel() == 3) { //说明有三级菜单
				req.setAttribute("secondPage", itemService
						.findItemsByParentId(itemService.findParticalItemById(
								item.getParentId()).getParentId()));
				req.setAttribute("second", item.getParentId());
				req.setAttribute("first",
						itemService.findParticalItemById(item.getParentId()).getParentId());
			} else if (item.getLevel() == 2) {
				req.setAttribute("first", item.getParentId());
				req.setAttribute("secondPage",
						itemService.findItemsByParentId(item.getParentId()));
			}
		} else {
			if(listEntity.size()>0) {
				req.setAttribute("secondPage",
						itemService.findItemsByParentId(listEntity.get(0).getId()));
			} else {
				return new ModelAndView("upperadmin/item/error");
			}
		}
		req.setAttribute("firstPage", listEntity);
		req.setAttribute("districtCode", districtCode);
		return new ModelAndView("upperadmin/item/articleSec");
	}
	
	@RequestMapping(params = "reqItems")
	@ResponseBody
	public AjaxJson reqItems(String parentId) {
		AjaxJson j = new AjaxJson();
		j.setObj(itemService.findItemsByParentId(parentId));
		return j;
	}

	@RequestMapping(params = "articleSave")
	@ResponseBody
	public AjaxJson articleSave(ItemEntity item, String content,
			String contentTxt,String files,HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(item.getId())) {
			message = "类目基本信息更新成功";
			ItemEntity t = itemService.get(ItemEntity.class, item.getId());
			ArticleEntity a = t.getArticle();
			a.setContent(content);
			a.setContentTxt(contentTxt);
			t.setArticle(a);
			if (item.getParentId().equals("0")) {
				item.setLevel(1); // 设置为目录级
			} else {
				item.setLevel(itemService.findParticalItemById(item.getParentId())
						.getLevel() + 1); // 设置为内容级别
			}
			try {
				MyBeanUtils.copyBeanNotNull2Bean(item, t);
				itemService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "类目基本信息更新失败";
			}
		} else {
			UpperAdminSession sessionAdmin = (UpperAdminSession) req.getSession()
					.getAttribute(Constant.UPPER_ADMIN_SESSION);
			Date curDate = new Date(System.currentTimeMillis());
			message = "内容信息添加成功";
			item.setCreateTime(curDate);
			item.setUpdateTime(curDate);
			item.setBelongs(DictionaryBean.keyDict.get("item_belongs_public")
					.getValue());
			item.setCreator(sessionAdmin.getId());
			item.setType(Constant.Article);
			item.setStatus(DictionaryBean.keyDict.get("item_status_normal")
					.getValue());
			if (item.getParentId().equals("0")) {
				item.setLevel(1); // 设置为目录级
			} else {
				item.setLevel(itemService.findParticalItemById(item.getParentId())
						.getLevel() + 1); // 设置为内容级别
			}
			ArticleEntity article = new ArticleEntity();
			article.setContent(content);
			article.setContentTxt(contentTxt);
			article.setParentId(item.getParentId());
			article.setBelongId(DictionaryBean.keyDict.get("item_belongs_public")
					.getValue());
			if(files.equals("1")) {
				String filePath=Constant.BASE_File_PATH+contentTxt;
				String targetUnzipPath = Constant.BASE_File_PATH+"/mc-back/files/touchFiles"+System.currentTimeMillis();
				  try {
					ZipUtil.unzip(filePath, targetUnzipPath); 
					File[] root = (new File(targetUnzipPath)).listFiles();
					for (int i = 0; i < root.length; i++) {
						String fileName = root[i].getName();
						if (fileName.contains(".htm")) {
							article.setTouchPath(targetUnzipPath+"/"+fileName);
						}
					}
					article.setIsTouch(files);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			itemService.save(article);
			item.setArticle(article);
			itemService.save(item);
		}
		
		systemService.addLog(message, Globals.Log_Type_INSERT,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(params = "articleSaveSec")
	@ResponseBody
	public AjaxJson articleSaveSec(ItemEntity item, String content,
			String contentTxt,String districtCode,HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		UpperAdminSession admin= (UpperAdminSession) req.getSession().getAttribute(Constant.UPPER_ADMIN_SESSION);
		if (StringUtil.isNotEmpty(item.getId())) {
			message = "内容信息更新成功";
			ItemEntity t = itemService.get(ItemEntity.class, item.getId());
			ArticleEntity a = t.getArticle();
			a.setContent(content);
			a.setContentTxt(contentTxt);
			t.setArticle(a);
			if (item.getParentId().equals("0")) {
				item.setLevel(1); // 设置为目录级
			} else {
				item.setLevel(itemService.findParticalItemById(item.getParentId())
						.getLevel() + 1); // 设置为内容级别
			}
			try {
				MyBeanUtils.copyBeanNotNull2Bean(item, t);
				itemService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
				LeaveEntity leave = new LeaveEntity();
				
				leave.setContent("超级管理员对您所属单位中题目为<<"+item.getTitle()+">>的文章进行了修改");
				leave.setIsHandle("0");
				leave.setTarget(item.getBelongs());
				Date curDate = new Date(System.currentTimeMillis());
				leave.setCreateTime(curDate);
				leave.setProperty("1");
				leave.setSources(admin.getId());
				leave.setTarget(districtCode);
				itemService.save(leave);
				
			} catch (Exception e) {
				e.printStackTrace();
				message = "内容信息更新成功";
			}
		} 
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "preview")
	public ModelAndView preview(String id, HttpServletRequest req) {
		req.setAttribute("article", itemService.findArticleByItemId(id));
		return new ModelAndView("upperadmin/item/preview");
	}

	// 在编辑中的预览
	@RequestMapping(params = "previewEditing")
	@ResponseBody
	public AjaxJson previewEditing(String content, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		article = content;
		return j;
	}
	
	@RequestMapping(params = "previewEditing2")
	public ModelAndView preview(HttpServletRequest req) {
		req.setAttribute("articling",article);
		return new ModelAndView("upperadmin/item/preview");
	}
	
}
