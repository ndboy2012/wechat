package com.weimeng.dao;

import java.util.List;

import com.weimeng.entity.web.signInggk.WechatPrizeInfo;
import com.weimeng.entity.web.signInggk.WechatSignInfo;
import com.weimeng.entity.web.signInggk.WechatTotalSignAccount;
import com.weimeng.entity.web.signInggk.WechatWinPrizes;
import com.weimeng.entity.web.signInggk.WechatWinnerUtil;

public interface WechatSignDao {
     
	 /*判断open_id是否存在*/
    public boolean isExitOpenId(String open_id) throws Exception;
    
    public WechatSignInfo getSignInfo(String open_id) throws Exception;
    /*保存用户所获的奖项*/
    public void saveWinPrize(WechatWinPrizes win) throws Exception;
    
    public boolean updateSignInfo(String open_id) throws Exception;
    
    public void updateSignInfo(WechatSignInfo signInfo) throws Exception;
    /*通过open_id获取签到数量*/
    public int getSignCount(String open_id) throws Exception;
    
    public void saveWeiChatSign(WechatSignInfo sign) throws Exception;
    /*根据open_id查出这个人所中的奖*/
    public List listAllPrizeByOpenid(String open_id) throws Exception;
    
    public WechatPrizeInfo getPrizeInfo(int prizeLevel);
    
    /*查询某个人是否中过了某等奖品*/
    public boolean alreadyGetPrize(String open_id,int prize_no) throws Exception;
    
    public void updatePrizeInfo(WechatPrizeInfo prezeInfo) throws Exception;
    /*根据奖品等级查询奖品所剩数量*/
    public int getPrizeRemainCount(int prizeLevel);
    
    public void updatePrizeReainCount(int prizeLevel,int remainCounts) throws Exception;
    /*查出小于奖品等级的所有获奖信息*/
    public List<WechatWinPrizes> getWinPrizes(int prizeLevel) throws Exception;
    
    public List<Object> getWinPrizes(String datebef,String dateaft) throws Exception;
    
    public String getTelephone(String open_id) throws Exception;
    
    public String getPrizeName(int prizeNo) throws Exception; 
    
    public void saveWechatWinnerUtil(WechatWinnerUtil wechatWinnerUtil) throws Exception;
    /*查询签到次数大于account次数的信息*/
    public List<WechatSignInfo> getWechatSignInfos(int account) throws Exception;
    
    public void saveWechatTotalSign(WechatTotalSignAccount wechatTotal) throws Exception;
	    
}
