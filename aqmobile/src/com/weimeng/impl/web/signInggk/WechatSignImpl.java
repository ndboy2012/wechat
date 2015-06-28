package com.weimeng.impl.web.signInggk;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimeng.dao.CommonToolsDao;
import com.weimeng.dao.WechatSignDao;
import com.weimeng.entity.web.signInggk.WechatPrizeInfo;
import com.weimeng.entity.web.signInggk.WechatSignInfo;
import com.weimeng.entity.web.signInggk.WechatTotalSignAccount;
import com.weimeng.entity.web.signInggk.WechatWinPrizes;
import com.weimeng.entity.web.signInggk.WechatWinnerUtil;
import com.weimeng.util.DateProcess;

@Service("wechatSignImpl")
@Transactional
public class WechatSignImpl implements WechatSignDao {
    
	@Resource(name="commonToolsImpl")
	private CommonToolsDao commonToolsImpl;
	
	public boolean isExitOpenId(String open_id) throws Exception {
		boolean result = false;
		String parameters[] = { open_id };
		String sql = "select count(*) from WechatSignInfo where openId=?";
		int number = Integer.parseInt(commonToolsImpl.queryUnique(sql,
				parameters).toString());
		if (number > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public WechatSignInfo getSignInfo(String open_id) throws Exception {
		String parameters[] = {open_id };
		String sql = "from WechatSignInfo where openId=?";	
		return (WechatSignInfo)commonToolsImpl.queryUnique(sql, parameters);
	}

	@Override
	public void saveWinPrize(WechatWinPrizes win) throws Exception {
		commonToolsImpl.saveObj(win);
	}

	@Override
	public boolean updateSignInfo(String open_id) throws Exception {
		
		return false;
	}

	@Override
	public void updateSignInfo(WechatSignInfo signInfo) throws Exception {
		commonToolsImpl.updateObject(signInfo);

	}

	@Override
	public int getSignCount(String open_id) throws Exception {
		String hql = "select monthSignAccounts from  WechatSignInfo where openId=?";
		String parameters[] = {open_id};
		return Integer.parseInt(commonToolsImpl.queryUnique(hql, parameters).toString());
	}

	@Override
	public void saveWeiChatSign(WechatSignInfo sign) throws Exception {
              commonToolsImpl.saveObj(sign);
	}

	@Override
	public List listAllPrizeByOpenid(String open_id) throws Exception {
		String sql = "from WechatWinPrizes where openId=?";
		String parameters[] = {open_id};
		return commonToolsImpl.executeQuery(sql, parameters);
	}

	@Override
	public WechatPrizeInfo getPrizeInfo(int prizeLevel) {
		String sql = "from WechatPrizeInfo where prizeNo="+prizeLevel;
		WechatPrizeInfo prizeinfo = null;
		try {
			 prizeinfo = (WechatPrizeInfo) commonToolsImpl.queryUnique(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prizeinfo;
	}

	@Override
	public void updatePrizeInfo(WechatPrizeInfo prezeInfo) throws Exception {
		commonToolsImpl.updateObject(prezeInfo);
	}

	@Override
	public int getPrizeRemainCount(int prizeLevel) {
		String sql = "select prizeRemainCount from WechatPrizeInfo where prizeNo="+prizeLevel;
		int remainCount = 0;
		try {
			remainCount = (Integer) commonToolsImpl.queryUnique(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return remainCount;
	}

	@Override
	public void updatePrizeReainCount(int prizeLevel, int remainCounts)
			throws Exception {
		String sql = "update tb_wechat_prize_info set prize_remain_count="+remainCounts+" where prize_no="+prizeLevel;
        commonToolsImpl.executeUpdate(sql, null);
	}

	@Override
	public List<WechatWinPrizes> getWinPrizes(int prizeLevel) throws Exception {
		String sql = "from WechatWinPrizes where prizeNo<="+prizeLevel;
		List<WechatWinPrizes> list = commonToolsImpl.executeQuery(sql, null);
		return list;
	}

	@Override
	public String getTelephone(String open_id) throws Exception {
		String sql = "select telephone from WechatBaseInfo where openId ='"+open_id+"'";
		return (String) commonToolsImpl.queryUnique(sql, null);			
	}

	@Override
	public String getPrizeName(int prizeNo) throws Exception {
		String sql = "select prizeContent from WechatPrizeInfo where prizeNo='"+prizeNo+"'";
		return (String) commonToolsImpl.queryUnique(sql, null);	
	}

	@Override
	public List<Object> getWinPrizes(String datebef,String dateaft) throws Exception {
		Date dbef = DateProcess.convertString2date(datebef);
		Date daft = DateProcess.convertString2date(dateaft);
		String sql = "select distinct openId,prizeNo from WechatWinPrizes where winDate<? and winDate>?";
		Object[] obj = {daft,dbef};
		List<Object> list = commonToolsImpl.executeQuery(sql, obj);
		return list;
	}

	@Override
	public void saveWechatWinnerUtil(WechatWinnerUtil wechatWinnerUtil)
			throws Exception {
		    commonToolsImpl.saveObj(wechatWinnerUtil);
	}

	@Override
	public List<WechatSignInfo> getWechatSignInfos(int account)
			throws Exception {
		String sql = "from  WechatSignInfo where monthSignAccounts>="+account;
		List<WechatSignInfo> list = commonToolsImpl.executeQuery(sql, null);
		return list;
	}

	@Override
	public void saveWechatTotalSign(WechatTotalSignAccount wechatTotal)
			throws Exception {
		  commonToolsImpl.saveObj(wechatTotal);
	}

	@Override
	public boolean alreadyGetPrize(String open_id,int prize_no) throws Exception {
		boolean result = false;
		String sql = "select count(*) from WechatWinPrizes where openId=? and prizeNo="+prize_no;
		String[] parameters = {open_id};
	    int account = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters).toString());
	    if(account > 0) {
	    	result = true;
	    }
		return result;
	}
}
