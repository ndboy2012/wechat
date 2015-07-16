package org.yelp.process.groupbuy;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.yelp.constant.WechatConstant;
import org.yelp.entity.common.Article;
import org.yelp.entity.resp.NewsMessage;
import org.yelp.util.Message2XmlUtil;


@Service("groupBuyProcess")
public class WechatGroupbuyProcess {
    
	 public String respGroupBuy(Map<String,String> map) {
		 NewsMessage newsMessage = new NewsMessage();
			newsMessage.setFromUserName(map.get("ToUserName"));
			newsMessage.setToUserName(map.get("FromUserName"));
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
			newsMessage.setFuncFlag(0);
			List<Article> articleList = new ArrayList<Article>();
			Article article = new Article();
			article.setTitle("拼团够手机");
			article.setPicUrl(WechatConstant.BASE_IMG_URL+"groupbuy/banner.png");
			article.setUrl(WechatConstant.BASE_URL+"groupBuyController/group.do");
			article.setDescription("更多惊喜，等你来玩~");
			articleList.add(article);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			return Message2XmlUtil.newsMessageToXml(newsMessage);
	 }
} 
