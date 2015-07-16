package org.yelp.process.band;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.yelp.constant.WechatConstant;
import org.yelp.entity.resp.TextMessage;
import org.yelp.util.Message2XmlUtil;

@Service
public class WechatBandProcess {

	public String respBand(Map<String, String> map) {
		TextMessage text = new TextMessage();
		text.setFromUserName(map.get(WechatConstant.TOUSERNAME));
		text.setToUserName(map.get(WechatConstant.FROMEUSERNAME));
		text.setCreateTime(new Date().getTime());
		text.setMsgType(WechatConstant.REQ_MESSAGE_TYPE_TEXT);
		text.setFuncFlag(1);
		text.setContent("<a href=\"http://weibodl.55zhe.net/wz.php?mod=wzfenlei&wzid=17935&openid=oSKfajunz6HAXIoG72_7Didaq9Ko&aw=wx.qq.com\">宽带覆盖查询办理，快点击我</a>");
		return Message2XmlUtil.textMessageToXml(text);
	}
}
