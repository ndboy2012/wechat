package org.yelp.dao.impl.calls;

import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.yelp.dao.calls.CallsDaoI;
import org.yelp.entity.calls.WechatRecommCustomer;

@Service
public class CallDao extends CommonServiceImpl implements CallsDaoI {

	@Override
	public boolean isExitOpenidInBase(String open_id) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "select count(*) from WechatBaseInfo where openId=?";
		String[] parameters = { open_id };
		int count = 0;
		count = Integer.parseInt(queryUnique(sql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isExitTelBase(String recommTel) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "select count(*) from WechatBaseInfo where telephone=?";
		String[] parameters = { recommTel };
		int count = 0;
		count = Integer.parseInt(queryUnique(sql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isExitOpenidInRecomm(String open_id) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "select count(*) from WechatRecommCustomer where openId=?";
		String[] parameters = { open_id };
		int count = 0;
		count = Integer.parseInt(queryUnique(sql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public String getTelephone(String open_id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select telephone from RegisterEntity where openId ='"+open_id+"'";
		return (String) queryUnique(sql, null);	
	}

	public String getOpenid(String telephone) throws Exception {
		String sql = "select openId from RegisterEntity where telephone ='"+telephone+"'";
		return (String)queryUnique(sql, null);	
	}

	@Override
	public void saveObject(Object obj) throws Exception {
		save(obj);
	}

	@Override
	public boolean isRepeateRecomm(String open_id, String telephone)
			throws Exception {
		boolean result = false;
		String sql = "select count(*) from WechatRecommCustomer where open_id=? and recommendTel=?";
		String[] parameters = {open_id,telephone};
		int count = 0;
		count = Integer.parseInt(queryUnique(sql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isExitTelCalls(String recommTel) throws Exception {
		boolean result = false;
		String sql = "select count(*) from WechatCallsCumulative where telephone=?";
		String[] parameters = { recommTel };
		int count = 0;
		count = Integer.parseInt(queryUnique(sql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateAmmountByTel(String tele) throws Exception {
		
		boolean result = false;
		String sql = "update tb_wechat_calls_cumulative  set ammounts = ammounts+1 where telephone =?";
		String[] parameters = {tele};
		int effect = 0;
		effect = executeUpdate(sql, parameters);
		if(effect>0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isExitOpenidCalls(String open_id) throws Exception {
		boolean result = false;
		String sql = "select count(*) from WechatCallsCumulative where openId=?";
		String[] parameters = { open_id };
		int count = 0;
		count = Integer.parseInt(queryUnique(sql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateAmmountByOpenId(String open_id) throws Exception {
		
		boolean result = false;
		String sql = "update tb_wechat_calls_cumulative  set ammounts = ammounts+1 where open_id =?";
		String[] parameters = {open_id};
		int effect = 0;
		effect = executeUpdate(sql, parameters);
		if(effect>0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isFirstRecommend(String open_id) throws Exception {
		boolean result = false;
		String sql = "select count(*) from WechatRecommCustomer where openId=?";
		String[] parameters = {open_id};
		int count = 0;
		count = Integer.parseInt(queryUnique(sql, parameters).toString());
		if(count < 2) {
			result = true;
		}
		return result;
	}

	@Override
	public int getAmmountByOpenId(String open_id) throws Exception {
		int count = 0;
		String sql = "select ammounts from WechatCallsCumulative where openId=?";
		String[] parameters = {open_id};
	    if(queryUnique(sql, parameters)!=null)
	    	count = Integer.parseInt(queryUnique(sql, parameters).toString());
		return count;
	}

	@Override
	public List<WechatRecommCustomer> getAllRecommInfoByTel(String Tel)
			throws Exception {

		String sql = "from WechatRecommCustomer where recommendTel = ?";
		String[] parameters = {Tel};
		List<WechatRecommCustomer> listOpenId = executeQuery(sql, parameters);
		for(int i = 0;i<listOpenId.size();i++) {
			WechatRecommCustomer we = listOpenId.get(i);
			we.setRecommendTel(getTelephone(we.getOpenId()));  //openId转换
			listOpenId.set(i, we);
		}
		return listOpenId;
	}

	@Override
	public int getAmmountByTel(String tel) throws Exception {
		
		int count = 0;
		String sql = "select ammounts from WechatCallsCumulative where telephone=?";
		String[] parameters = {tel};
		count = Integer.parseInt(queryUnique(sql, parameters).toString());
		return count;
	}

	@Override
	public int getRecommedNum(String tel) throws Exception {
		
		int count = 0;
		String sql = "select count(*) from WechatRecommCustomer where recommendTel=?";
		String[] parameters = {tel};
		count = Integer.parseInt(queryUnique(sql, parameters).toString());
		return count;
	}

}
