package com.ddmt.service.item;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

import com.ddmt.entity.article.ArticleEntity;
import com.ddmt.entity.item.ItemEntity;

public interface CommonItemServiceI extends CommonService{
       
	public List<Map<String, Object>> queryTreeGridTree(String districtCode);
	
	//查找能够添加文章的类目
	public List<ItemEntity> findItemsCanEdit();
	
	public List<ItemEntity> findItemsByParentId(String parentId);
	
	public List<ItemEntity> findItemsCanEnitByParentId(String parentId);
	//此目录下面是否可以添加文章
	public boolean itemCanEdit(String id);
	
	public ItemEntity findParticalItemById(String id);
	
	public ArticleEntity findArticleByItemId(String id);
}  
