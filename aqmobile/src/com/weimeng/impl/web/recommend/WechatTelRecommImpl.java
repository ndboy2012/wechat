package com.weimeng.impl.web.recommend;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weimeng.dao.CommonToolsDao;
import com.weimeng.dao.WechatTelRecommDao;
import com.weimeng.entity.web.recommend.WechatRecommCustomer;

@Service("wechatTelRecommImpl")
public class WechatTelRecommImpl implements WechatTelRecommDao{
    
	@Resource(name = "commonToolsImpl")
	private CommonToolsDao commonToolsImpl;

	public boolean isExitOpenidInBase(String open_id) throws Exception {
		boolean result = false;
		String sql = "select count(*) from WechatBaseInfo where openId=?";
		String[] parameters = { open_id };
		int count = 0;
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isExitTelBase(String recommTel) throws Exception {
		boolean result = false;
		String sql = "select count(*) from WechatBaseInfo where telephone=?";
		String[] parameters = { recommTel };
		int count = 0;
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isExitOpenidInRecomm(String open_id) throws Exception {
		boolean result = false;
		String sql = "select count(*) from WechatRecommCustomer where openId=?";
		String[] parameters = { open_id };
		int count = 0;
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters)
				.toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}
    
	public String getTelephone(String open_id) throws Exception {
			String sql = "select telephone from WechatBaseInfo where openId ='"+open_id+"'";
			return (String) commonToolsImpl.queryUnique(sql, null);			
	}

	public String getOpenid(String telephone) throws Exception {
		String sql = "select openId from WechatBaseInfo where telephone ='"+telephone+"'";
		return (String) commonToolsImpl.queryUnique(sql, null);	
	}

	@Override
	public void saveObject(Object obj) throws Exception {
		commonToolsImpl.saveObj(obj);
	}

	@Override
	public boolean isRepeateRecomm(String open_id, String telephone)
			throws Exception {
		boolean result = false;
		String sql = "select count(*) from WechatRecommCustomer where open_id=? and recommendTel=?";
		String[] parameters = {open_id,telephone};
		int count = 0;
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters)
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
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters)
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
		effect = commonToolsImpl.executeUpdate(sql, parameters);
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
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters)
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
		effect = commonToolsImpl.executeUpdate(sql, parameters);
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
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters).toString());
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
	    if(commonToolsImpl.queryUnique(sql, parameters)!=null)
	    	count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters).toString());
		return count;
	}

	@Override
	public List<WechatRecommCustomer> getAllRecommInfoByTel(String Tel)
			throws Exception {

		String sql = "from WechatRecommCustomer where recommendTel = ?";
		String[] parameters = {Tel};
		List<WechatRecommCustomer> listOpenId = commonToolsImpl.executeQuery(sql, parameters);
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
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters).toString());
		return count;
	}

	@Override
	public int getRecommedNum(String tel) throws Exception {
		
		int count = 0;
		String sql = "select count(*) from WechatRecommCustomer where recommendTel=?";
		String[] parameters = {tel};
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql, parameters).toString());
		return count;
	}
	
}
