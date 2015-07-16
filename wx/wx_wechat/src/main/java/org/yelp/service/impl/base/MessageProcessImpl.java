package org.yelp.service.impl.base;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yelp.constant.WechatConstant;
import org.yelp.process.KeyWord.WechatKeyWordProcess;
import org.yelp.process.band.WechatBandProcess;
import org.yelp.process.calls.WechatCallsProcess;
import org.yelp.process.groupbuy.WechatGroupbuyProcess;
import org.yelp.process.register.WechatRegisterProcess;
import org.yelp.process.seckill.WechatSeckillProcess;
import org.yelp.process.sign.WechatSignProcess;
import org.yelp.service.base.MessageProcessI;
import org.yelp.service.register.WechatRegisterServiceI;

@Service
public class MessageProcessImpl implements MessageProcessI {

	@Autowired
	private WechatRegisterServiceI wechatService;
    
	//登记相关处理
	@Autowired
	private WechatRegisterProcess registerProcess;
	
    //关键字处理
	@Autowired
	private WechatKeyWordProcess keyWorkProcess;
	
	//签到相关处理
	@Autowired
	private WechatSignProcess signProcess;
	
	//宽带查询出处理
	@Autowired
	private WechatBandProcess bandProcess;
	
	//团购相关
	@Autowired
	private WechatGroupbuyProcess groupProcess;
	
	//推荐话费相关
	@Autowired
	private WechatCallsProcess callProcess;
	
	//双11秒杀活动相关
	@Autowired
	private WechatSeckillProcess seckill;

	@Override
	public String getRespMessage(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		String respMessage = "";
		String key = "";
		if (!wechatService.isRegister(map.get(WechatConstant.FROMEUSERNAME))) { // 如果还未登记过
			respMessage = registerProcess.respNotRegister(map);
		} else {
			switch (map.get(WechatConstant.MESSAGE_TYPE)) {
			case WechatConstant.REQ_MESSAGE_TYPE_IMAGE: // 接收到图片消息请求
				// respMessage = wechatNotKeyProcessImpl.responeNotKeyWord(map);
				break;
			case WechatConstant.REQ_MESSAGE_TYPE_TEXT: //接收到文本消息请求;
				key = map.get("Content");
				respMessage = getMsgProcess(map, key);
				break;
			case WechatConstant.REQ_MESSAGE_TYPE_EVENT: // 接收到事件推送请求
				key = map.get("EventKey");
				respMessage = getMsgProcess(map, key);
				break;
			}
		}
		return respMessage;
	}

	@Override
	public void processMessage(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub

	}

	private String getMsgProcess(Map<String, String> map, String key) {
		String rtnMessage = "";
		switch (key) {
		case WechatConstant.KEY_REGISTER:   //用户输入登记关键字
			rtnMessage = registerProcess.respHasRegister(map);
			break;
		case WechatConstant.KEY_SIGN:
			rtnMessage = signProcess.respSignLink(map);
			break;
		case WechatConstant.KEY_BAND:
			rtnMessage = bandProcess.respBand(map);
			break;
		case WechatConstant.KEY_GROUPBUY:
			rtnMessage = groupProcess.respGroupBuy(map);
			break;
		case WechatConstant.KEY_CALLS:
			rtnMessage = callProcess.recommendCalls(map);
			break;
		case WechatConstant.KEY_SECKILL:
			rtnMessage = seckill.respSeckillMessage(map);
			break;
		case WechatConstant.KEY_SECKILL_CODE:
			rtnMessage = seckill.resopneQuerySeckillCode(map);
			break;
		default: // 默认非关键字回复
			rtnMessage = keyWorkProcess.respNotKeyWord(map);
		}
		return rtnMessage;
	}

}
