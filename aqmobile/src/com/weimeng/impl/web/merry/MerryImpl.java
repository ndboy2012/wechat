package com.weimeng.impl.web.merry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimeng.dao.CommonToolsDao;
import com.weimeng.dao.merry.MerryDao;
import com.weimeng.entity.web.merry.MerryAchieverInfo;
import com.weimeng.entity.web.merry.MerryUnopenInfo;

@Service("merryImpl")
public class MerryImpl implements MerryDao {

	@Resource(name = "commonToolsImpl")
	private CommonToolsDao commonToolsImpl;

	@Override
	public boolean hasEnvelop(String openId) throws Exception {
		boolean result = false;
		int count = 0;
		String hql = "select count(*) from MerryUnopenInfo where openId=?";
		Object[] parameters = { openId };
		count = Integer.parseInt(commonToolsImpl.queryUnique(hql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public String queryTelByOpenId(String openId) throws Exception {
		String sql = "select telephone from WechatBaseInfo where openId =?";
		Object[] parameters = { openId };
		return commonToolsImpl.queryUnique(sql, parameters).toString();
	}

	@Override
	public void saveMerryUnopenInfo(MerryUnopenInfo info) throws Exception {
		// TODO Auto-generated method stub
		commonToolsImpl.saveObj(info);
	}

	@Override
	public int queryFriendOpen(String openId) throws Exception {
		String sql = "select open from MerryUnopenInfo where openId=?";
		Object[] parameters = { openId };
		return Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters)
				.toString());
	}

	@Override
	public boolean hasPrizeContent(String openId) throws Exception {
		boolean result = false;
		int count = 0;
		String hql = "select count(*) from MerryAchieverInfo where openId=?";
		Object[] parameters = { openId };
		count = Integer.parseInt(commonToolsImpl.queryUnique(hql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public void updateEnvelopNumbers(String openId) throws Exception {
        String hql = "update tb_merry_unopen_info set open=open+1 where open_id=?";
        Object[] parameters = { openId };
        commonToolsImpl.executeUpdate(hql, parameters);
	}

	@Override
	public int queryPrizeRemainCounts(int level) throws Exception {
		String hql = "select remain from MerryPrizeInfo where id=?";
		System.out.println(level);
		Object[] parameters = { level };
		return Integer.parseInt(commonToolsImpl.queryUnique(hql, parameters)
				.toString());
	}

	@Override
	public String queryPrizeNameByLevel(int level) throws Exception {
		String hql = "select name from MerryPrizeInfo where id=?";
		Object[] parameters = { level };
		return (String) commonToolsImpl.queryUnique(hql, parameters);
	}

	@Override
	@Transactional
	public void savePrizeAchever(MerryAchieverInfo info,int level) throws Exception {
		String hql = "update tb_merry_prize_info set remain=remain-1 where id=?";
		Object[] parameters = { level };
		commonToolsImpl.executeUpdate(hql, parameters);
		commonToolsImpl.saveObj(info);
	}

	@Override
	public String hasPrizeInfo(String openId) throws Exception {
		String hql = "select prizeName from MerryAchieverInfo where openId=?";
		Object[] parameters = { openId };
		return (String) commonToolsImpl.queryUnique(hql, parameters);
	}

}
