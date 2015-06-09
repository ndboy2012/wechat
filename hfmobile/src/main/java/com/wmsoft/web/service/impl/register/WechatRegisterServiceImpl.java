package com.wmsoft.web.service.impl.register;

import java.util.Date;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;

import com.wmsoft.web.entity.register.RegisterEntity;
import com.wmsoft.web.entity.register.WechatRegHasget;
import com.wmsoft.web.service.register.WechatRegisterServiceI;
import com.wmsoft.wechat.constant.WechatConstant;
import com.wmsoft.wechat.entity.resp.TextMessage;
import com.wmsoft.wechat.util.Message2XmlUtil;

@Service
public class WechatRegisterServiceImpl extends CommonServiceImpl implements
		WechatRegisterServiceI {

	@Override
	public boolean hasRegister(String open_id) throws Exception {
		boolean result = false;
		int count = 0;
	    count = findByProperty(RegisterEntity.class, "openId", open_id).size();
	    if(count>0) {
	    	result = true;
	    }
		return result;
	}
	
	
	@Override
	public String responeHasNotRegister(Map<String, String> map)
			throws Exception {
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(map.get("ToUserName"));
		textMessage.setToUserName(map.get("FromUserName"));
		textMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setFuncFlag(1);
		textMessage.setContent("还未登记");
		return Message2XmlUtil.textMessageToXml(textMessage);
	}
    
	//查询登记人数
	@Override
	public int getRegisterNums() throws Exception {
		String hql = "select count(*) from WechatRegHasget";
		return Integer.parseInt(singleResult(hql).toString());
	}



	@Override
	public RegisterEntity findRegisterInfoByTel(String telephone)
			throws Exception {
		return findUniqueByProperty(RegisterEntity.class, "telephone", telephone);
	}


	@Override
	public boolean validateRegisterBytel(String telephone) throws Exception {
		boolean result = false;
		int count = 0;
	    count = findByProperty(RegisterEntity.class, "telephone", telephone).size();
	    if(count>0) {
	    	result = true;
	    }
		return result;
	}


	@Override
	public boolean hasGet(String telephone) throws Exception {
		boolean result = false;
		int count = 0;
	    count = findByProperty(WechatRegHasget.class, "telephone", telephone).size();
	    if(count>0) {
	    	result = true;
	    }
		return result;
	}


	@Override
	public void saveHasGet(WechatRegHasget get) throws Exception {
		save(get);
	}


	@Override
	public void saveRegisterInfo(RegisterEntity register) throws Exception {
       save(register);		
	}


	@Override
	public RegisterEntity findRegisterInfoByOpenId(String openId)
			throws Exception {
		return findUniqueByProperty(RegisterEntity.class, "openId", openId);
	}
}
