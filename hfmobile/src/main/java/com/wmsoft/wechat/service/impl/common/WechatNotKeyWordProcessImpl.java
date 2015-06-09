package com.wmsoft.wechat.service.impl.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wmsoft.wechat.constant.WechatConstant;
import com.wmsoft.wechat.entity.commom.Article;
import com.wmsoft.wechat.entity.resp.NewsMessage;
import com.wmsoft.wechat.entity.resp.TextMessage;
import com.wmsoft.wechat.service.common.WechatNotKeyProcessI;
import com.wmsoft.wechat.util.Message2XmlUtil;

@Service
public class WechatNotKeyWordProcessImpl implements WechatNotKeyProcessI {

	@Override
	public String responeNotKeyWord(Map<String, String> map) {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setFromUserName(map.get("ToUserName"));
		newsMessage.setToUserName(map.get("FromUserName"));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
		newsMessage.setFuncFlag(0);
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("移动4G优惠购机全攻略");
		article.setPicUrl(WechatConstant.baseUrl+"/files/images/register/resp.jpg");
		article.setUrl("http://weibodl.55zhe.net/wz.php?pageid=133730&openid=o8IqvjgDRLhgLBDKfEc5VYJXRPe4&aw=wx.qq.com");
		article.setDescription("更多惊喜，就在合肥移动~");
		articleList.add(article);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return Message2XmlUtil.newsMessageToXml(newsMessage);
	}

}
