package com.ddmt.service.impl.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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
import org.springframework.transaction.annotation.Transactional;

import com.ddmt.entity.article.ArticleEntity;
import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.item.ItemEntity;
import com.ddmt.service.item.CommonItemServiceI;

@Service("commonItemService")
@Transactional
public class CommonItemServiceImpl extends CommonServiceImpl implements
		CommonItemServiceI {

	private List<ItemEntity> allCategoryList;

	List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

	public List<Map<String, Object>> queryTreeGridTree(String districtCode) {
		allCategoryList = getAllItemsByOrder();
		List<ItemEntity> rootCategoryList = findByProperty( // 取出一级分类
				ItemEntity.class, "parentId", "0");
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < rootCategoryList.size(); i++) { // 装载一级分类的东西
			ItemEntity obj = rootCategoryList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", obj.getId());
			map.put("title", obj.getTitle());
			map.put("type", obj.getType());
			map.put("isPublic", obj.getIsPublic());
			map.put("belongs", obj.getBelongs());
			map.put("status", obj.getStatus());
			map.put("createTime", obj.getCreateTime());
			map.put("updateTime", obj.getUpdateTime());
			map.put("children", getTreeGridChildren(obj.getId(), districtCode));
			if (!dataList.contains(map)) {
				dataList.add(map);
			}
		}
		return dataList;
	}

	/**
	 * 递归查询子节点
	 * 
	 * @param guid
	 * @return
	 */
	public List<Map<String, Object>> getTreeGridChildren(String code,
			String districtCode) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (ItemEntity obj : allCategoryList) { // 进行帅选
			if (obj.getParentId().equals(code)
					&& (obj.getBelongs().equals("public") || obj.getBelongs()
							.equals(districtCode))) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj.getId());
				map.put("title", obj.getTitle());
				map.put("type", obj.getType());
				map.put("status", obj.getStatus());
				map.put("isPublic", obj.getIsPublic());
				map.put("createTime", obj.getCreateTime());
				map.put("belongs", obj.getBelongs());
				map.put("updateTime", obj.getUpdateTime());
				map.put("children",
						getTreeGridChildren(obj.getId(), districtCode));
				if (!dataList.contains(map)) {
					dataList.add(map);
				}
			}
		}
		return dataList;
	}

	@Override
	public List<ItemEntity> findItemsCanEdit() {
		return findByProperty(ItemEntity.class, "isPublic",
				DictionaryBean.keyDict.get("item_is_open").getValue());
	}

	@Override
	public List<ItemEntity> findItemsCanEnitByParentId(String parentId) {
		
		return getSession()
				.createCriteria(ItemEntity.class)
				.add(Restrictions.in("parentId", new String[] { parentId }))
				.add(Restrictions.in(
						"isPublic",
						new String[] { DictionaryBean.keyDict.get(
								"item_is_open").getValue() })).list();
		
	}

	@Override
	public boolean itemCanEdit(String id) {
		ItemEntity item = getEntity(ItemEntity.class, id);
		if(item.getIsPublic().equals( DictionaryBean.keyDict.get(
								"item_is_open").getValue())) {
			return true;
		}
		return false;
	}

	@Override
	public List<ItemEntity> findItemsByParentId(String parentId) {
		return findByProperty(ItemEntity.class, "parentId", parentId);
	}

	/**
     * 通过id来查找到某一个特定的目录
     */
	@Override
	public ItemEntity findParticalItemById(String id) {
		return getEntity(ItemEntity.class, id);
	}

	@Override
	public ArticleEntity findArticleByItemId(String id) {
		ItemEntity item = getEntity(ItemEntity.class, id);
		return item.getArticle();
	}
	
	public List<ItemEntity> getAllItemsByOrder() {
		return getSession().createCriteria(ItemEntity.class)
				.addOrder(Order.asc("sort")).addOrder(Order.desc("createTime"))
				.list();
	}

}
