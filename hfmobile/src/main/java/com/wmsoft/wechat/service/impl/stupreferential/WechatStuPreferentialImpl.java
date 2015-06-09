package com.wmsoft.wechat.service.impl.stupreferential;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wmsoft.wechat.constant.WechatConstant;
import com.wmsoft.wechat.entity.commom.Article;
import com.wmsoft.wechat.entity.resp.NewsMessage;
import com.wmsoft.wechat.service.stupreferential.WechatStuPreferentialI;
import com.wmsoft.wechat.util.Message2XmlUtil;

@Service
public class WechatStuPreferentialImpl implements WechatStuPreferentialI {

	

	private NewsMessage newsMessage;


	@Override
	public String reqMsgStuPreferential(Map<String, String> map) {
		newsMessage = new NewsMessage();
		newsMessage.setFromUserName(map.get(WechatConstant.TOUSERNAME));
		newsMessage.setToUserName(map.get(WechatConstant.FROMEUSERNAME));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("合肥移动大学生寒假特惠");
		article.setPicUrl(WechatConstant.baseUrl
				+ "/files/images/stupreferential/wechatversion.png");
		article.setUrl(WechatConstant.baseUrl
				+ "/stuPreferentialController/stuPreferentialWe.do?openId="
				+ map.get(WechatConstant.FROMEUSERNAME));
		article.setDescription("亲爱的学生用户，放寒假期间，你还在担心回家之后高昂的漫游费吗？快来参与合肥移动的寒假特惠活动，即可享受2015年2月份寒假期间国内（不含港澳台）漫游话费超出50元全额返还，最高可返还150元哦！你还在等什么，快来参与吧！");
		articleList.add(article);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return Message2XmlUtil.newsMessageToXml(newsMessage);
	}

}
