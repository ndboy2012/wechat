package com.ddmt.service.impl.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddmt.constant.Constant;
import com.ddmt.entity.article.ArticleEntity;
import com.ddmt.entity.district.DistrictEntity;
import com.ddmt.entity.item.ItemEntity;
import com.ddmt.service.item.ItemServiceI;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("itemService")
@Transactional
public class ItemServiceImpl extends CommonServiceImpl implements ItemServiceI {

	private List<ItemEntity> allCategoryList;

	@Override
	public List<Map<String, Object>> queryTreeGridTree() {
		/*allCategoryList = getList(ItemEntity.class);  取出所有的分类*/
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
			map.put("status", obj.getStatus());
			map.put("createTime", obj.getCreateTime());
			map.put("updateTime", obj.getUpdateTime());
			map.put("children", getTreeGridChildren(obj.getId()));
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
	public List<Map<String, Object>> getTreeGridChildren(String code) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (ItemEntity obj : allCategoryList) {
			if (obj.getParentId().equals(code)
					&& obj.getBelongs().equals("public")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj.getId());
				map.put("title", obj.getTitle());
				map.put("type", obj.getType());
				map.put("status", obj.getStatus());
				map.put("isPublic", obj.getIsPublic());
				map.put("createTime", obj.getCreateTime());
				map.put("updateTime", obj.getUpdateTime());
				map.put("children", getTreeGridChildren(obj.getId()));
				if (!dataList.contains(map)) {
					dataList.add(map);
				}
			}
		}
		return dataList;
	}

	// 查找到在同一个目录下面的目录
	@Override
	public List<ItemEntity> findItemsByParentId(String ids) {
		return getSession()
				.createCriteria(ItemEntity.class)
				.add(Restrictions.in("parentId", new String[] { ids }))
				.add(Restrictions.not(Restrictions.in("type",
						new String[] { Constant.Article }))).list();
	}

	@Override
	public void deleteItem(ItemEntity item) {
		String id = item.getId();
		if (item.getType().equals(Constant.Article)) {
			delete(item.getArticle());
			delete(item);
		} else {
			delete(item);
		}
		deleteChildrenItem(id);
	}

	public void deleteChildrenItem(String id) {
		List<ItemEntity> items = findItemsByParentId(id);
		if (items != null) {
			for (ItemEntity obj : items) {
				deleteItem(obj);
			}
		}
	}

	@Override
	@Transactional
	public void saveArticle(ItemEntity item, ArticleEntity article) {
		save(item);
		save(article);
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

	@Override
	public boolean changeIsPublic(String itemId, String value) {
		String sql = "update tb_mctax_item_info set IS_PUBLIC='" + value
				+ "' where id='" + itemId + "'";
		updateBySqlString(sql);
		return true;
	}

	@Override
	public List<DistrictEntity> queryAllDistrict() {
		return loadAll(DistrictEntity.class);
	}

	@Override
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

	public List<ItemEntity> getAllItemsByOrder() {
		return getSession().createCriteria(ItemEntity.class)
				.addOrder(Order.asc("sort")).addOrder(Order.desc("createTime"))
				.list();
	}

}