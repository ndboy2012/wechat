package org.yelp.service.impl.seckill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yelp.dao.register.RegisterDaoI;
import org.yelp.dao.seckill.SeckillDaoI;
import org.yelp.entity.seckill.SeckillPhoneInfo;
import org.yelp.entity.seckill.SeckillUserInfo;
import org.yelp.service.seckill.WechatSeckillServiceI;
import org.yelp.util.DateProcessTools;

@Service
public class WechatSeckillService implements WechatSeckillServiceI {
    
	@Autowired
	private SeckillDaoI seck;
	
	@Autowired
	private RegisterDaoI register;
	
	@Override
	public boolean hasParticipate(String openId) {
		// TODO Auto-generated method stub
		return seck.hasParticipate(openId);
	}

	@Override
	public int isRightTime(int day, int hourBef, int hourAft) {
		// TODO Auto-generated method stub
		int rtnHour = -1;
		int ccrrentDay = DateProcessTools.achieveDayOfMonth();
		int currentHour = DateProcessTools.achieveDayOfHour();
		if ((ccrrentDay == day) && (hourBef <= currentHour)
				&& (currentHour <= hourAft)) {
			rtnHour = currentHour;
		} else if(((ccrrentDay == day))&&(currentHour>hourAft)){   //活动已经结束
			rtnHour = -2;
		}
		return rtnHour;
	}

	@Override
	public boolean hasSoldOut(int hour) {
		// TODO Auto-generated method stub
		return seck.hasSoldOut(hour);
	}

	@Override
	public SeckillPhoneInfo getPhoneInfo(int hour) {
		// TODO Auto-generated method stub
		return seck.getPhoneInfo(hour);
	}

	@Override
	public void saveSeckillUserInfo(SeckillUserInfo user, int hour)
			{
		// TODO Auto-generated method stub
        seck.saveSeckillUserInfo(user, hour);
	}

	@Override
	public String getTelephoneByOpenid(String openId) {
		// TODO Auto-generated method stub
		try {
			return register.getPhoneByOpenId(openId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SeckillUserInfo getUserInfoByOpenid(String openid) {
		// TODO Auto-generated method stub
		return seck.getUserInfoByOpenid(openid);
	}

	@Override
	public boolean hasParticipateGroup(String openid) {
		// TODO Auto-generated method stub
		return seck.hasParticipateGroup(openid);
	}

}
