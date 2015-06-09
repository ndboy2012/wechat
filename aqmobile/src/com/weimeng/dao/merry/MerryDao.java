package com.weimeng.dao.merry;

import com.weimeng.entity.web.merry.MerryAchieverInfo;
import com.weimeng.entity.web.merry.MerryUnopenInfo;


public interface MerryDao {
    
	 public boolean hasEnvelop(String openId) throws Exception;
	 
	 public String queryTelByOpenId(String openId) throws Exception;
	 
	 public void saveMerryUnopenInfo(MerryUnopenInfo info) throws Exception;
	 
	 //查询好友帮忙拆开的红包数量
	 public int queryFriendOpen(String openid) throws Exception;
	 //查询是否已经打开了红包
	 public boolean hasPrizeContent(String openId) throws Exception;
	 //在好友点击 帮他拆红包之后更新红包数量
	 public void updateEnvelopNumbers(String openId) throws Exception;
	 
	 public int queryPrizeRemainCounts(int level) throws Exception;
	 //通过奖品等级来查询奖品名称
	 public String queryPrizeNameByLevel(int level) throws Exception;
	 
	 public void savePrizeAchever(MerryAchieverInfo info,int level) throws Exception;
	 //查询是否有奖品
	 public String hasPrizeInfo(String openId) throws Exception;
	 
}
