package org.yelp.service.impl.group;


import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yelp.entity.group.ProductInfo;
import org.yelp.service.group.GroupBuyServiceI;


@Service
public class GroupBuyService extends CommonServiceImpl implements GroupBuyServiceI{
	
	@Override
	@Transactional
	public List<ProductInfo> queryAllProducts() throws Exception {
		return loadAll(ProductInfo.class);
	}
}
