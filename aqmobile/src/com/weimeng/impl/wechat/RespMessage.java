package com.weimeng.impl.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.weimeng.dao.WechatRegisterDao;
import com.weimeng.entity.wechat.Article;
import com.weimeng.entity.wechat.resp.NewsMessage;
import com.weimeng.entity.wechat.resp.TextMessage;
import com.weimeng.util.ConstantStr;
import com.weimeng.util.Message2Xml;


@Service("respMessage")
public class RespMessage {

	private Logger logger = Logger.getLogger(RespMessage.class);

	@Resource(name = "wechatRegisterImpl")
	private WechatRegisterDao wechatRegisterImpl;

	public String respTextMessage(Map<String, String> mapXml) throws Exception {
		String respString = null;
		// 如果不openid不存在的话就提示进行绑定操作
		if (!wechatRegisterImpl.isExitOpenid(mapXml.get("FromUserName"))) {
			
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setFromUserName(mapXml.get("ToUserName"));
			newsMessage.setToUserName(mapXml.get("FromUserName"));
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(ConstantStr.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
			newsMessage.setFuncFlag(0);

			List<Article> articleList = new ArrayList<Article>();

			Article article = new Article();
			article.setTitle("登记安庆移动手机号码");
			// 不回复图片
			article.setPicUrl("");
			article.setUrl("http://103.229.183.247/aqMobile/web/register/register.jsp?openid="
					+ mapXml.get("FromUserName"));

			logger.info("http://103.229.183.247/aqMobile/web/register/register.jsp?openid="
					+ mapXml.get("FromUserName") + "*******");

			article.setDescription("尊敬的用户:登记手机号不仅仅为了更好地为您服务。点击“阅读全文”，登记安庆移动手机号;");
			articleList.add(article);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			respString = Message2Xml.newsMessageToXml(newsMessage);

		} else if (mapXml.get("MsgType").equals(
				ConstantStr.REQ_MESSAGE_TYPE_EVENT)
				&& mapXml.get("Event").equals(ConstantStr.EVENT_TYPE_CLICK)
				&& mapXml.get("EventKey").equals("号码登记")) {
            
			TextMessage textMessage = new TextMessage();
			textMessage.setFromUserName(mapXml.get("ToUserName"));
			textMessage.setToUserName(mapXml.get("FromUserName"));
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(ConstantStr.RESP_MESSAGE_TYPE_TEXT); // 回复
			textMessage.setFuncFlag(1);

			String content = "亲！您已经登记过了不需要重新登记";
			logger.info(content);
			textMessage.setContent(content);
			respString = Message2Xml.textMessageToXml(textMessage);

		} else if (mapXml.get("MsgType").equals( // 每日签到触发
				ConstantStr.REQ_MESSAGE_TYPE_EVENT)
				&& mapXml.get("Event").equals(ConstantStr.EVENT_TYPE_CLICK)
				&& mapXml.get("EventKey").equals("签到")) {

			TextMessage textMessage = new TextMessage();
			textMessage.setFromUserName(mapXml.get("ToUserName"));
			textMessage.setToUserName(mapXml.get("FromUserName"));
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(ConstantStr.RESP_MESSAGE_TYPE_TEXT); // 回复
			textMessage.setFuncFlag(1);

			String content2 = "活动已经结束,我们正在努力统计与派发奖品，请耐心等待";
			logger.info(content2);
			textMessage.setContent(content2);
			respString = Message2Xml.textMessageToXml(textMessage);

		}  else if (mapXml.get("MsgType").equals(
				ConstantStr.REQ_MESSAGE_TYPE_EVENT)
				&& mapXml.get("Event").equals(ConstantStr.EVENT_TYPE_CLICK)
				&& mapXml.get("EventKey").equals("推荐")) {

			TextMessage textMessage = new TextMessage();
			textMessage.setFromUserName(mapXml.get("ToUserName"));
			textMessage.setToUserName(mapXml.get("FromUserName"));
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(ConstantStr.RESP_MESSAGE_TYPE_TEXT); // 回复
			textMessage.setFuncFlag(1);

			String content2 = "活动已经结束,我们正在努力统计与派发奖品，请耐心等待";
			logger.info(content2);
			textMessage.setContent(content2);
			respString = Message2Xml.textMessageToXml(textMessage);

		} else if(mapXml.get("MsgType").equals(
				ConstantStr.REQ_MESSAGE_TYPE_EVENT)
				&& mapXml.get("Event").equals(ConstantStr.EVENT_TYPE_CLICK)
				&& mapXml.get("EventKey").equals("红包")) {
			
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setFromUserName(mapXml.get("ToUserName"));
			newsMessage.setToUserName(mapXml.get("FromUserName"));
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(ConstantStr.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
			newsMessage.setFuncFlag(0);

			List<Article> articleList = new ArrayList<Article>();
			Article article = new Article();
			article.setTitle("迎圣诞元旦，蛋蛋有惊喜");
			article.setPicUrl(ConstantStr.BaseUrl
					+ "/images/merry/wechatShare.jpg");
			article.setUrl(ConstantStr.BaseUrl
					+ "/merryController/envelop.do?openId="
					+ mapXml.get("FromUserName"));
			article.setDescription("即日起至2015年1月31日，关注安庆移动官方微信，流量、话费、通话包、古井贡酒…万份红包等您拿！");
			articleList.add(article);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			return Message2Xml.newsMessageToXml(newsMessage);
			
		}else if (mapXml.get("Content").equals("签到")) {

			TextMessage textMessage = new TextMessage();
			textMessage.setFromUserName(mapXml.get("ToUserName"));
			textMessage.setToUserName(mapXml.get("FromUserName"));
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(ConstantStr.RESP_MESSAGE_TYPE_TEXT); // 回复
			textMessage.setFuncFlag(1);
			String content2 = "活动已经结束,我们正在努力统计与派发奖品，请耐心等待";
			logger.info(content2);
			textMessage.setContent(content2);
			respString = Message2Xml.textMessageToXml(textMessage);

		}else if (mapXml.get("Content").equals("红包")) {
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setFromUserName(mapXml.get("ToUserName"));
			newsMessage.setToUserName(mapXml.get("FromUserName"));
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(ConstantStr.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
			newsMessage.setFuncFlag(0);

			List<Article> articleList = new ArrayList<Article>();
			Article article = new Article();
			article.setTitle("迎圣诞元旦，蛋蛋有惊喜");
			article.setPicUrl(ConstantStr.BaseUrl
					+ "/images/merry/wechatShare.jpg");
			article.setUrl(ConstantStr.BaseUrl
					+ "/merryController/envelop.do?openId="
					+ mapXml.get("FromUserName"));
			article.setDescription("即日起至2015年1月31日，关注安庆移动官方微信，流量、话费、通话包、古井贡酒…万份红包等您拿！");
			articleList.add(article);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			return Message2Xml.newsMessageToXml(newsMessage);
		} else {

			TextMessage textMessage = new TextMessage();
			textMessage.setFromUserName(mapXml.get("ToUserName"));
			textMessage.setToUserName(mapXml.get("FromUserName"));
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(ConstantStr.RESP_MESSAGE_TYPE_TEXT); // 回复
			textMessage.setFuncFlag(1);

			String content = "欢迎关注安庆移动官方微信，这里有最新最优惠的移动资讯哦；\n即日起发送关键字“红包”即可参与抢红包活动；\n您可以点击菜单栏操作，也可以按以下方式操作:\n"
					+ "\n\n【查手机号】-输入：手机 13712345678  \n【查火车】-火车 车次或火车 起始地 目的地\n【查天气】-安庆天气\n【笑话】-笑话"
					+ "\n【糗事】-糗事  \n【百科】-百科杜甫  \n【美食】-美食\n【成语词典】-成语 一心一意"
					+ "\n【快递查询】-韵达快递1200722815552 \n【想听音乐】-音乐 或 音乐 周杰伦，音乐 双节棍  \n【想看电影】-电影 或 电影 大话西游  \n.....";

			textMessage.setContent(content);
			respString = Message2Xml.textMessageToXml(textMessage);

		}
		return respString;
	}
}
