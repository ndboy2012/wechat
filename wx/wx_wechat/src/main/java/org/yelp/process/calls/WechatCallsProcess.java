package org.yelp.process.calls;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.yelp.constant.WechatConstant;
import org.yelp.entity.resp.TextMessage;
import org.yelp.util.Message2XmlUtil;

@Service
public class WechatCallsProcess {
   
	 public String recommendCalls(Map<String,String> map) {
		 TextMessage text = new TextMessage();
			text.setFromUserName(map.get(WechatConstant.TOUSERNAME));
			text.setToUserName(map.get(WechatConstant.FROMEUSERNAME));
			text.setCreateTime(new Date().getTime());
			text.setMsgType(WechatConstant.REQ_MESSAGE_TYPE_TEXT);
			text.setFuncFlag(1);
			text.setContent("<a href=\""+WechatConstant.BASE_URL+"callsController/skip.do?openId="+map.get(WechatConstant.FROMEUSERNAME)+"\">推荐好友领话费</a>");
			return Message2XmlUtil.textMessageToXml(text);
	 }
}
