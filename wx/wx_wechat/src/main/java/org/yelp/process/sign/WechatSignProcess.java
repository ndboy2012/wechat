package org.yelp.process.sign;

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

/**
 * 每日签到抽奖
 * @author Administrator
 *
 */
@Service
public class WechatSignProcess {
    
	public String respSignLink(Map<String,String> map) {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setFromUserName(map.get("ToUserName"));
		newsMessage.setToUserName(map.get("FromUserName"));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("每日签到抽奖");
		// 不回复图片
		article.setPicUrl(WechatConstant.BASE_IMG_URL+"sign/sign.jpg");
		article.setUrl(WechatConstant.BASE_URL
				+ "/signController/skip.do?openId="
				+ map.get("FromUserName"));
		article.setDescription("每日签到抽奖");
		articleList.add(article);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return Message2XmlUtil.newsMessageToXml(newsMessage);
	}
 	
}
