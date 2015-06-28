package com.weimeng.dao;

import com.weimeng.entity.web.register.WechatBaseInfo;



public interface WechatRegisterDao {
    
	    public boolean isExitTel(String tel) throws Exception;
	    
	    public void saveWeiChatInfo(WechatBaseInfo weiChatInfo) throws Exception;
	    
	    public boolean isExitOpenid(String openid) throws Exception;
}   
