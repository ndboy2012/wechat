package com.wmsoft.wechat.service.impl.register;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;

import com.wmsoft.wechat.constant.WechatConstant;
import com.wmsoft.wechat.entity.commom.Article;
import com.wmsoft.wechat.entity.resp.NewsMessage;
import com.wmsoft.wechat.entity.resp.TextMessage;
import com.wmsoft.wechat.service.register.WechatRegisterProcessI;
import com.wmsoft.wechat.util.Message2XmlUtil;

@Service
public class WechatRegisterProcessImpl extends CommonServiceImpl implements
		WechatRegisterProcessI {

	/*
	 * 用户还未登记,这时候提醒登记
	 * 
	 * @see com.wmsoft.service.wechat.register.WechatRegisterProcessI#
	 * responeHasNotRegister(java.util.Map)
	 */
	@Override
	public String responeHasNotRegister(Map<String, String> map) {

		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setFromUserName(map.get("ToUserName"));
		newsMessage.setToUserName(map.get("FromUserName"));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("合肥移动微信手机号登记");
		// 不回复图片
		article.setPicUrl("");
		article.setUrl(WechatConstant.baseUrl
				+ "/registerController/reg.do?openId="
				+ map.get("FromUserName"));
		article.setDescription(WechatConstant.register_describe);
		articleList.add(article);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return Message2XmlUtil.newsMessageToXml(newsMessage);
	}

	public String responeHasRegister(Map<String, String> map) {
		TextMessage text = new TextMessage();
		text.setFromUserName(map.get("ToUserName"));
		text.setToUserName(map.get("FromUserName"));
		text.setCreateTime(new Date().getTime());
		text.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_TEXT);
		text.setFuncFlag(1);
		text.setContent("您已经登记过了，不需要重新登记");
		return Message2XmlUtil.textMessageToXml(text);
	}

}
