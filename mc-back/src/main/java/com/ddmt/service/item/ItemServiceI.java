package com.ddmt.service.item;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

import com.ddmt.entity.article.ArticleEntity;
import com.ddmt.entity.district.DistrictEntity;
import com.ddmt.entity.item.ItemEntity;

public interface ItemServiceI extends CommonService {

	public List<Map<String, Object>> queryTreeGridTree();
    //查找到在同一个目录下面的目录
	public List<ItemEntity> findItemsByParentId(String ids);

	public void deleteItem(ItemEntity item);

	public void saveArticle(ItemEntity item, ArticleEntity article);
	
	public ItemEntity findParticalItemById(String id);
	
	public ArticleEntity findArticleByItemId(String id);
	//改变开放权限
	public boolean changeIsPublic(String itemId,String value);
	
	public List<DistrictEntity> queryAllDistrict();
	
	public List<Map<String, Object>> queryTreeGridTree(String districtCode);
	
}
