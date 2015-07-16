package org.yelp.process.register;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.yelp.constant.WechatConstant;
import org.yelp.entity.common.Article;
import org.yelp.entity.resp.NewsMessage;
import org.yelp.entity.resp.TextMessage;
import org.yelp.util.Message2XmlUtil;

@Service
public class WechatRegisterProcess {

	public String respNotRegister(Map<String, String> map) {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setFromUserName(map.get("ToUserName"));
		newsMessage.setToUserName(map.get("FromUserName"));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("微信手机号登记");
		// 不回复图片
		article.setPicUrl(WechatConstant.BASE_IMG_URL+"register/libr.jpg");
		article.setUrl(WechatConstant.BASE_URL
				+ "/register.do?reg&openId="
				+ map.get("FromUserName"));
		article.setDescription("您暂时还未登记号码，请先登记");
		articleList.add(article);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return Message2XmlUtil.newsMessageToXml(newsMessage);
	}

	public String respHasRegister(Map<String, String> map) {
		TextMessage text = new TextMessage();
		text.setFromUserName(map.get("ToUserName"));
		text.setToUserName(map.get("FromUserName"));
		text.setCreateTime(new Date().getTime());
		text.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_TEXT);
		text.setFuncFlag(1);
		text.setContent("您已经登记过了!");
		return Message2XmlUtil.textMessageToXml(text);
	}
}
