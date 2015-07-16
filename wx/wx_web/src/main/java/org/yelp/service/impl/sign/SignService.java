package org.yelp.service.impl.sign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yelp.dao.sign.SignDaoI;
import org.yelp.entity.signInggk.WechatPrizeInfo;
import org.yelp.entity.signInggk.WechatSignInfo;
import org.yelp.entity.signInggk.WechatTotalSignAccount;
import org.yelp.entity.signInggk.WechatWinPrizes;
import org.yelp.entity.signInggk.WechatWinnerUtil;
import org.yelp.service.sign.SignServiceI;

@Service
public class SignService implements SignServiceI{
    
	@Autowired
	private SignDaoI signDao;
	
	@Override
	public boolean isExitOpenId(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return signDao.isExitOpenId(open_id);
	}

	@Override
	public WechatSignInfo getSignInfo(String open_id) throws Exception {
		// TODO Auto-generated method stub
		
		return signDao.getSignInfo(open_id);
	}

	@Override
	public void saveWinPrize(WechatWinPrizes win) throws Exception {
		// TODO Auto-generated method stub
		signDao.saveWinPrize(win);
	}

	@Override
	public boolean updateSignInfo(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return signDao.updateSignInfo(open_id);
	}

	@Override
	public void updateSignInfo(WechatSignInfo signInfo) throws Exception {
		// TODO Auto-generated method stub
		signDao.updateSignInfo(signInfo);
	}

	@Override
	public int getSignCount(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return signDao.getSignCount(open_id);
	}

	@Override
	public void saveWeiChatSign(WechatSignInfo sign) throws Exception {
		// TODO Auto-generated method stub
		signDao.saveWeiChatSign(sign);
	}

	@Override
	public List listAllPrizeByOpenid(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return signDao.listAllPrizeByOpenid(open_id);
	}

	@Override
	public WechatPrizeInfo getPrizeInfo(int prizeLevel) {
		// TODO Auto-generated method stub
		return signDao.getPrizeInfo(prizeLevel);
	}

	@Override
	public boolean alreadyGetPrize(String open_id, int prize_no)
			throws Exception {
		// TODO Auto-generated method stub
		return signDao.alreadyGetPrize(open_id, prize_no);
	}

	@Override
	public void updatePrizeInfo(WechatPrizeInfo prezeInfo) throws Exception {
		// TODO Auto-generated method stub
		signDao.updatePrizeInfo(prezeInfo);
	}

	@Override
	public int getPrizeRemainCount(int prizeLevel) {
		// TODO Auto-generated method stub
		return signDao.getPrizeRemainCount(prizeLevel);
	}

	@Override
	public void updatePrizeReainCount(int prizeLevel, int remainCounts)
			throws Exception {
		// TODO Auto-generated method stub
		signDao.updatePrizeReainCount(prizeLevel, remainCounts);
	}

	@Override
	public List<WechatWinPrizes> getWinPrizes(int prizeLevel) throws Exception {
		// TODO Auto-generated method stub
		return signDao.getWinPrizes(prizeLevel);
	}

	@Override
	public List<Object> getWinPrizes(String datebef, String dateaft)
			throws Exception {
		// TODO Auto-generated method stub
		return signDao.getWinPrizes(datebef, dateaft);
	}

	@Override
	public String getTelephone(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return signDao.getTelephone(open_id);
	}

	@Override
	public String getPrizeName(int prizeNo) throws Exception {
		// TODO Auto-generated method stub
		return signDao.getPrizeName(prizeNo);
	}

	@Override
	public void saveWechatWinnerUtil(WechatWinnerUtil wechatWinnerUtil)
			throws Exception {
		// TODO Auto-generated method stub
		signDao.saveWechatWinnerUtil(wechatWinnerUtil);
	}

	@Override
	public List<WechatSignInfo> getWechatSignInfos(int account)
			throws Exception {
		// TODO Auto-generated method stub
		return signDao.getWechatSignInfos(account);
	}

	@Override
	public void saveWechatTotalSign(WechatTotalSignAccount wechatTotal)
			throws Exception {
		// TODO Auto-generated method stub
		signDao.saveWechatTotalSign(wechatTotal);
	}

}
