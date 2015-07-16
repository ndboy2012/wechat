package org.yelp.dao.impl.seckill;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.yelp.dao.seckill.SeckillDaoI;
import org.yelp.entity.seckill.SeckillPhoneInfo;
import org.yelp.entity.seckill.SeckillUserInfo;

@Service
public class SeckillDao extends CommonServiceImpl implements SeckillDaoI {

	@Override
	public boolean hasParticipate(String openId) {
		// TODO Auto-generated method stub
		boolean result = false;
		int count = 0;
		String sql = "select count(*) from SeckillUserInfo where openId = ?";
		Object[] parameters = { openId };
		try {
			count = Integer.parseInt(queryUnique(sql, parameters).toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * 查询hour时刻的手机是否已经被秒杀完
	 */
	@Override
	public boolean hasSoldOut(int hour) {
		// TODO Auto-generated method stub
		int counts = 0;
		boolean result = true;
		String sql = "select remains from SeckillPhoneInfo where participateTime = ?";
		Object[] parameters = { hour };
		try {
			counts = Integer.parseInt(queryUnique(sql, parameters).toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (counts > 0) {
			result = false;
		}
		return result;
	}

	@Override
	public SeckillPhoneInfo getPhoneInfo(int hour) {
		// TODO Auto-generated method stub
		String sql = "from SeckillPhoneInfo where participateTime = ?";
		Object[] parameters = { hour };
		try {
			return (SeckillPhoneInfo) queryUnique(sql, parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveSeckillUserInfo(SeckillUserInfo user, int hour) {
		// TODO Auto-generated method stub
		String sql = "update tb_wechat_seckill_phoneinfo set remains=remains-1 where participateTime=?";
		Object[] parameters = { hour };
		try {
			executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		save(user);
	}

	@Override
	public SeckillUserInfo getUserInfoByOpenid(String openid) {
		// TODO Auto-generated method stub
		String sql = "from SeckillUserInfo where openId=?";
		Object[] parameters = { openid };
		try {
			return (SeckillUserInfo) queryUnique(sql, parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean hasParticipateGroup(String openid) {
		// TODO Auto-generated method stub
		int count = 0;
		boolean result = false;
		String sql = "select count(*) from GroupBuyerInfo where openId=?";
		Object[] parameters = { openid };
		try {
			count = Integer.parseInt(queryUnique(sql, parameters).toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count > 0) {
			result = true;
		}
		return result;
	}
}
