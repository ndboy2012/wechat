package org.yelp.process.KeyWord;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.yelp.constant.WechatConstant;
import org.yelp.entity.resp.TextMessage;
import org.yelp.util.Message2XmlUtil;

@Service("keyWordProcess")
public class WechatKeyWordProcess {

	public String respNotKeyWord(Map<String, String> map) {
		TextMessage text = new TextMessage();
		text.setFromUserName(map.get(WechatConstant.TOUSERNAME));
		text.setToUserName(map.get(WechatConstant.FROMEUSERNAME));
		text.setCreateTime(new Date().getTime());
		text.setMsgType(WechatConstant.REQ_MESSAGE_TYPE_TEXT);
		text.setFuncFlag(1);
		text.setContent("感谢你登记ndboy2012的微信公众平台，"
				+ "\n输入关键字“登记”，进入登记功能，"
				+ "\n输入关键字“签到”，进入到签到功能，"
				+ "\n输入关键字“宽带”，进入宽带功能，"
				+ "\n输入关键字“团购”，进入拼团国际功能，"
				+ "\n输入关键字“推荐”，进入推荐好友得话费功能"
				+ "\n输入关键字“秒杀”，进入双11手机秒杀活动功能"
				+ "\n输入关键字“秒杀码”，查询所秒杀到的手机兑换码");
		return Message2XmlUtil.textMessageToXml(text);
	}

}
