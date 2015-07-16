package org.yelp.service.group;


import java.util.List;

import org.jeecgframework.core.common.service.CommonServiceI;
import org.yelp.entity.group.ProductInfo;



public interface GroupBuyServiceI extends CommonServiceI{
	public List<ProductInfo> queryAllProducts() throws Exception;
}
