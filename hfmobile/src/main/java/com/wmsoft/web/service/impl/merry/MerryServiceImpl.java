package com.wmsoft.web.service.impl.merry;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wmsoft.web.entity.merry.MerryAchieverInfo;
import com.wmsoft.web.entity.merry.MerryPrizeInfo;
import com.wmsoft.web.entity.merry.MerryUnopenInfo;
import com.wmsoft.web.entity.register.RegisterEntity;
import com.wmsoft.web.service.merry.MerryServiceI;

@Service
public class MerryServiceImpl extends CommonServiceImpl implements
		MerryServiceI {

	@Override
	public boolean hasEnvelop(String openId) throws Exception {
		boolean result = false;
		int count = 0;
		String hql = "select count(*) from MerryUnopenInfo where openId='"
				+ openId + "'";
		count = findByProperty(MerryUnopenInfo.class, "openId", openId).size();
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public String queryTelByOpenId(String openId) throws Exception {
		RegisterEntity register = (RegisterEntity) findUniqueByProperty(
				RegisterEntity.class, "openId", openId);
		return register.getTelephone();
	}

	@Override
	public void saveMerryUnopenInfo(MerryUnopenInfo info) throws Exception {
       save(info);
	}
    
	/*
	 * 查询好友打开的数量
	 * @see com.wmsoft.service.merry.MerryServiceI#queryFriendOpen(java.lang.String)
	 */
	@Override
	public int queryFriendOpen(String openid) throws Exception {
		MerryUnopenInfo info = findByProperty(MerryUnopenInfo.class, "openId", openid).get(0);
		//MerryUnopenInfo info = (MerryUnopenInfo) findUniqueByProperty(MerryUnopenInfo.class, "openId", openid);
		return info.getOpen();
	}
    
	/*
	 * 查询该用户是否有奖品
	 * @see com.wmsoft.service.merry.MerryServiceI#hasPrizeContent(java.lang.String)
	 */
	@Override 
	public boolean hasPrizeContent(String openId) throws Exception {
		boolean result = false;
		int count = 0;
		String hql = "select count(*) from MerryAchieverInfo where openId='"+openId+"'";
		count = Integer.parseInt(singleResult(hql).toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}
    
	/*
	 * 更新红包打开次数加1
	 * @see com.wmsoft.service.merry.MerryServiceI#updateEnvelopNumbers(java.lang.String)
	 */
	@Override
	public void updateEnvelopNumbers(String openId) throws Exception {
		    String hql = "update tb_hf_merry_unopen_info set open=open+1 where open_id='"+openId+"'";
	        Object[] parameters = { openId };
	        updateBySqlString(hql);
	}
    
	/*
	 * 查询奖品剩余数量
	 * @see com.wmsoft.service.merry.MerryServiceI#queryPrizeRemainCounts(int)
	 */
	@Override
	public int queryPrizeRemainCounts(int level) throws Exception {
		MerryPrizeInfo info = (MerryPrizeInfo) findUniqueByProperty(MerryPrizeInfo.class, "id", level);
		return info.getRemain();
	}
    
	/*
	 * 查询奖品名称通过奖品id
	 * @see com.wmsoft.service.merry.MerryServiceI#queryPrizeNameByLevel(int)
	 */
	@Override
	public String queryPrizeNameByLevel(int level) throws Exception {
		MerryPrizeInfo info = (MerryPrizeInfo) findUniqueByProperty(MerryPrizeInfo.class, "id", level);
		return info.getName();
	}
     
	/**
	 * 保存用户的抽奖信息，并且更新奖品数量减1
	 */
	@Override
	@Transactional
	public void savePrizeAchever(MerryAchieverInfo info, int level)
			throws Exception {
		String hql = "update tb_hf_merry_prize_info set remain=remain-1 where id='"+level+"'";
		updateBySqlString(hql);
		save(info);
	}
    
	/**
	 *通过openId来获取用户抽取的奖品
	 */
	@Override
	public String hasPrizeInfo(String openId) throws Exception {
		String hql = "select prizeName from MerryAchieverInfo where openId='"+openId+"'";
		return singleResult(hql);
	}

}
