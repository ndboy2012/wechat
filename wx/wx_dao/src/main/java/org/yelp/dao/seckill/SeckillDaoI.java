package org.yelp.dao.seckill;

import org.jeecgframework.core.common.service.CommonServiceI;
import org.yelp.entity.seckill.SeckillPhoneInfo;
import org.yelp.entity.seckill.SeckillUserInfo;

public interface SeckillDaoI extends CommonServiceI {

	public boolean hasParticipate(String openId);

	public boolean hasSoldOut(int hour);

	public SeckillPhoneInfo getPhoneInfo(int hour);

	public void saveSeckillUserInfo(SeckillUserInfo user, int hour);

	public SeckillUserInfo getUserInfoByOpenid(String openid);

	public boolean hasParticipateGroup(String openid);
}
