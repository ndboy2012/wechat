package com.weimeng.impl.web.register;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimeng.dao.CommonToolsDao;
import com.weimeng.dao.WechatRegisterDao;
import com.weimeng.entity.web.register.WechatBaseInfo;

@Service("wechatRegisterImpl")
@Transactional
public class WechatRegisterImpl implements WechatRegisterDao{
    
	@Resource(name = "commonToolsImpl")
	private CommonToolsDao commonToolsImpl;
	
	@Override
	public boolean isExitTel(String tel) throws Exception {
		
		boolean result = false;
		int count = 0;
		String parameters[] = { tel };
		String sql = "select count(*) from WechatBaseInfo where telephone=?";
	    count = Integer.parseInt(commonToolsImpl.queryUnique(sql,
				parameters).toString());
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public void saveWeiChatInfo(WechatBaseInfo weiChatInfo) throws Exception {
		commonToolsImpl.saveObj(weiChatInfo);
	}

	@Override
	public boolean isExitOpenid(String openid) throws Exception {
		
		boolean result = false;
		String[] parameters = {openid};
		int count = 0;
		String sql = "select count(*) from WechatBaseInfo where openId = ?";
		count = Integer.parseInt(commonToolsImpl.queryUnique(sql,
					parameters).toString());
		 if(count > 0) {
			 result = true;
		 }
		return result;
	}

}
