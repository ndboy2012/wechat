package org.yelp.dao.impl.sign;

import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.yelp.dao.sign.SignDaoI;
import org.yelp.entity.signInggk.WechatPrizeInfo;
import org.yelp.entity.signInggk.WechatSignInfo;
import org.yelp.entity.signInggk.WechatTotalSignAccount;
import org.yelp.entity.signInggk.WechatWinPrizes;
import org.yelp.entity.signInggk.WechatWinnerUtil;

@Service
public class SignDao extends CommonServiceImpl implements SignDaoI {

	@Override
	public boolean isExitOpenId(String open_id) throws Exception {
		boolean result = false;
		String parameters[] = { open_id };
		String sql = "select count(*) from WechatSignInfo where openId=?";
		int number = Integer.parseInt(queryUnique(sql,parameters).toString());
		if (number > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public WechatSignInfo getSignInfo(String open_id) throws Exception {
		String parameters[] = {open_id };
		String sql = "from WechatSignInfo where openId=?";	
		return (WechatSignInfo)queryUnique(sql, parameters);
	}

	@Override
	public void saveWinPrize(WechatWinPrizes win) throws Exception {
		// TODO Auto-generated method stub
		save(win);
	}

	@Override
	public boolean updateSignInfo(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateSignInfo(WechatSignInfo signInfo) throws Exception {
		// TODO Auto-generated method stub
		saveOrUpdate(signInfo);
	}

	@Override
	public int getSignCount(String open_id) throws Exception {
		// TODO Auto-generated method stub
		String hql = "select monthSignAccounts from  WechatSignInfo where openId=?";
		String parameters[] = {open_id};
		return Integer.parseInt(queryUnique(hql, parameters).toString());
	}

	@Override
	public void saveWeiChatSign(WechatSignInfo sign) throws Exception {
		// TODO Auto-generated method stub
		save(sign);
	}

	@Override
	public List listAllPrizeByOpenid(String open_id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "from WechatWinPrizes where openId=?";
		String parameters[] = {open_id};
		return executeQuery(sql, parameters);
	}

	@Override
	public WechatPrizeInfo getPrizeInfo(int prizeLevel) {
		// TODO Auto-generated method stub
		String sql = "from WechatPrizeInfo where prizeNo="+prizeLevel;
		WechatPrizeInfo prizeinfo = null;
		try {
			 prizeinfo = (WechatPrizeInfo)queryUnique(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prizeinfo;
	}

	@Override
	public boolean alreadyGetPrize(String open_id, int prize_no)
			throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "select count(*) from WechatWinPrizes where openId=? and prizeNo="+prize_no;
		String[] parameters = {open_id};
	    int account = Integer.parseInt(queryUnique(sql, parameters).toString());
	    if(account > 0) {
	    	result = true;
	    }
		return result;
	}

	@Override
	public void updatePrizeInfo(WechatPrizeInfo prezeInfo) throws Exception {
		// TODO Auto-generated method stub
		saveOrUpdate(prezeInfo);
	}

	@Override
	public int getPrizeRemainCount(int prizeLevel) {
		// TODO Auto-generated method stub
		String sql = "select prizeRemainCount from WechatPrizeInfo where prizeNo="+prizeLevel;
		int remainCount = 0;
		try {
			remainCount = (Integer)queryUnique(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return remainCount;
	}

	@Override
	public void updatePrizeReainCount(int prizeLevel, int remainCounts)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "update tb_wechat_prize_info set prize_remain_count="+remainCounts+" where prize_no="+prizeLevel;
        executeUpdate(sql, null);
	}

	@Override
	public List<WechatWinPrizes> getWinPrizes(int prizeLevel) throws Exception {
		// TODO Auto-generated method stub
		String sql = "from WechatWinPrizes where prizeNo<="+prizeLevel;
		List<WechatWinPrizes> list = executeQuery(sql, null);
		return list;
	}

	@Override
	public List<Object> getWinPrizes(String datebef, String dateaft)
			throws Exception {
	
		return null;
	}

	@Override
	public String getTelephone(String open_id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select telephone from WechatBaseInfo where openId ='"+open_id+"'";
		return (String)queryUnique(sql, null);	
	}

	@Override
	public String getPrizeName(int prizeNo) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select prizeContent from WechatPrizeInfo where prizeNo='"+prizeNo+"'";
		return (String)queryUnique(sql, null);	
	}

	@Override
	public void saveWechatWinnerUtil(WechatWinnerUtil wechatWinnerUtil)
			throws Exception {
		// TODO Auto-generated method stub
		save(wechatWinnerUtil);
	}

	@Override
	public List<WechatSignInfo> getWechatSignInfos(int account)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "from  WechatSignInfo where monthSignAccounts>="+account;
		List<WechatSignInfo> list = executeQuery(sql, null);
		return list;
	}

	@Override
	public void saveWechatTotalSign(WechatTotalSignAccount wechatTotal)
			throws Exception {
		// TODO Auto-generated method stub
        save(wechatTotal);
	}

}
