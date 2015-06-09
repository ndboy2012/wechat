package com.wmsoft.wechat.service.impl.merry;



import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wmsoft.wechat.constant.WechatConstant;
import com.wmsoft.wechat.entity.resp.NewsMessage;
import com.wmsoft.wechat.entity.resp.TextMessage;
import com.wmsoft.wechat.service.merry.WechatMerryProcessI;
import com.wmsoft.wechat.util.Message2XmlUtil;

@Service
public class WechatMerryProcessImpl implements WechatMerryProcessI {

	/*
	 * 请求红包 ,回复图文消息
	 * 
	 * @see
	 * com.wmsoft.service.wechat.merry.WechatMerryProcessI#reqMsgEnvelop(java
	 * .util.Map)
	 */

	private NewsMessage newsMessage;
    
	private TextMessage textMessage;
	
	public String reqMsgEnvelop(Map<String, String> map) {
		/*newsMessage = new NewsMessage();
		newsMessage.setFromUserName(map.get(WechatConstant.TOUSERNAME));
		newsMessage.setToUserName(map.get(WechatConstant.FROMEUSERNAME));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("激情双旦，合肥移动万份红包等您抢");
		article.setPicUrl(WechatConstant.baseUrl
				+ "/files/images/merry/wechatShare.png");
		article.setUrl(WechatConstant.baseUrl
				+ "/merryController/envelop.do?openId="
				+ map.get(WechatConstant.FROMEUSERNAME));
		article.setDescription("激情双旦，合肥移动万份红包等您抢，限量10000份，先到先得，赶紧来抢吧！");
		articleList.add(article);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return Message2XmlUtil.newsMessageToXml(newsMessage);*/
		textMessage = new TextMessage();
		textMessage.setFromUserName(map.get(WechatConstant.TOUSERNAME));
		textMessage.setToUserName(map.get(WechatConstant.FROMEUSERNAME));
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_TEXT); // 回复
		textMessage.setFuncFlag(1);
		String content = "活动已经结束";
		textMessage.setContent(content);
		return Message2XmlUtil.textMessageToXml(textMessage);
	}

}
