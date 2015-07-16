package org.yelp.service.impl.calls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yelp.dao.calls.CallsDaoI;
import org.yelp.entity.calls.WechatRecommCustomer;
import org.yelp.service.calls.CallsServiceI;

@Service
public class CallsService implements CallsServiceI {
    
	@Autowired
	private CallsDaoI call;
	
	@Override
	public boolean isExitOpenidInBase(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return call.isExitOpenidInBase(open_id);
	}

	@Override
	public boolean isExitTelBase(String recommTel) throws Exception {
		// TODO Auto-generated method stub
		return call.isExitTelBase(recommTel);
	}

	@Override
	public boolean isExitOpenidInRecomm(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return call.isExitOpenidInRecomm(open_id);
	}

	@Override
	public String getTelephone(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return call.getTelephone(open_id);
	}

	@Override
	public boolean isExitTelCalls(String recommTel) throws Exception {
		// TODO Auto-generated method stub
		return call.isExitTelCalls(recommTel);
	}

	@Override
	public boolean isExitOpenidCalls(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return call.isExitOpenidCalls(open_id);
	}

	@Override
	public String getOpenid(String telephone) throws Exception {
		// TODO Auto-generated method stub
		return call.getOpenid(telephone);
	}

	@Override
	public void saveObject(Object obj) throws Exception {
		// TODO Auto-generated method stub
       call.saveObject(obj);
	}

	@Override
	public boolean isRepeateRecomm(String open_id, String telephone)
			throws Exception {
		// TODO Auto-generated method stub
		return call.isRepeateRecomm(open_id, telephone);
	}

	@Override
	public boolean updateAmmountByTel(String tele) throws Exception {
		// TODO Auto-generated method stub
		return call.updateAmmountByTel(tele);
	}

	@Override
	public boolean updateAmmountByOpenId(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return call.updateAmmountByOpenId(open_id);
	}

	@Override
	public boolean isFirstRecommend(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return call.isFirstRecommend(open_id);
	}

	@Override
	public int getAmmountByOpenId(String open_id) throws Exception {
		// TODO Auto-generated method stub
		return call.getAmmountByOpenId(open_id);
	}

	@Override
	public int getAmmountByTel(String tel) throws Exception {
		// TODO Auto-generated method stub
		return call.getAmmountByTel(tel);
	}

	@Override
	public int getRecommedNum(String tel) throws Exception {
		// TODO Auto-generated method stub
		return call.getRecommedNum(tel);
	}

	@Override
	public List<WechatRecommCustomer> getAllRecommInfoByTel(String Tel)
			throws Exception {
		return call.getAllRecommInfoByTel(Tel);
	}

}
