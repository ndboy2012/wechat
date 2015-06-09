package com.wmsoft.wechat.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmsoft.web.service.register.WechatRegisterServiceI;
import com.wmsoft.wechat.constant.WechatConstant;
import com.wmsoft.wechat.service.MessageProcessI;
import com.wmsoft.wechat.service.common.WechatNotKeyProcessI;
import com.wmsoft.wechat.service.merry.WechatMerryProcessI;
import com.wmsoft.wechat.service.register.WechatRegisterProcessI;
import com.wmsoft.wechat.service.stupreferential.WechatStuPreferentialI;

@Service
public class MessageProcessImpl implements MessageProcessI {

	@Autowired
	private WechatRegisterServiceI wechatRegisterServiceImpl;
	@Autowired
	private WechatNotKeyProcessI wechatNotKeyProcessImpl;
	@Autowired
	private WechatRegisterProcessI wechatRegisterProcessImpl;
	@Autowired
	private WechatMerryProcessI wechatMerryProcessImpl; // 圣诞节活动相关
	@Autowired
	private WechatStuPreferentialI wechatStuPreferentialImpl; // 大学生寒假特惠相关
	
	private Logger logger = Logger.getLogger(MessageProcessI.class);

	@Override
	public String getRespMessage(Map<String, String> map) throws Exception {
		String respMessage = "";
		String key = "";
		if (!wechatRegisterServiceImpl.hasRegister(map
				.get(WechatConstant.FROMEUSERNAME))) {
			respMessage = wechatRegisterProcessImpl.responeHasNotRegister(map);
		} else {
			switch (map.get("MsgType")) {
			case WechatConstant.REQ_MESSAGE_TYPE_IMAGE: // 接收到图片消息请求
				respMessage = wechatNotKeyProcessImpl.responeNotKeyWord(map);
				break;
			case WechatConstant.REQ_MESSAGE_TYPE_TEXT: // 接收到文本消息请求;
				key = map.get("Content");
				respMessage = getMsgProcess(map, key);
				break;
			case WechatConstant.REQ_MESSAGE_TYPE_EVENT: // 接收到事件推送请求
				key = map.get("EventKey");
				respMessage = getMsgProcess(map, key);
				break;
			}
			return respMessage;
		}
		return respMessage;
	}

	@Override
	public void processMessage(Map<String, String> map) throws Exception {

	}
	
	private String getMsgProcess(Map<String, String> map, String key) {
		String rtnMessage = "";
		switch (key) {
		case WechatConstant.KeyOfRegister: // 登记关键字
			logger.info("合肥移动用户登记");
			rtnMessage = wechatRegisterProcessImpl.responeHasRegister(map);
			break;
		case WechatConstant.KeyOfMerry: // 圣诞活动礼包
			logger.info("合肥移动用户申请红包");
			rtnMessage = wechatMerryProcessImpl.reqMsgEnvelop(map);
			break;
		case WechatConstant.KeyOfPrivilege: // 特惠页面
			logger.info("大学生寒假特惠");
			rtnMessage = wechatStuPreferentialImpl.reqMsgStuPreferential(map);
			break;	
		default: // 默认非关键字回复
			rtnMessage = wechatNotKeyProcessImpl.responeNotKeyWord(map);
		}
		return rtnMessage;
	}

}
