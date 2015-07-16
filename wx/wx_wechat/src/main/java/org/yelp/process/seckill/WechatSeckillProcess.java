package org.yelp.process.seckill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yelp.constant.WechatConstant;
import org.yelp.entity.common.Article;
import org.yelp.entity.resp.NewsMessage;
import org.yelp.entity.resp.TextMessage;
import org.yelp.entity.seckill.SeckillPhoneInfo;
import org.yelp.entity.seckill.SeckillUserInfo;
import org.yelp.service.seckill.WechatSeckillServiceI;
import org.yelp.util.DateProcessTools;
import org.yelp.util.GenerateRandCode;
import org.yelp.util.Message2XmlUtil;

@Service
public class WechatSeckillProcess {

	@Autowired
	private WechatSeckillServiceI seckill;

	public String respSeckillMessage(Map<String, String> map) {

		String openId = map.get("FromUserName");
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setFromUserName(map.get("ToUserName"));
		newsMessage.setToUserName(map.get("FromUserName"));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_NEWS); // 回复图文消息
		newsMessage.setFuncFlag(0);
		List<Article> articleList = new ArrayList<Article>();

		Article article = new Article();
		String description = "";
		String imgUrl = WechatConstant.BASE_IMG_URL + "seckill/";

		/* 1.判断是否在特定时间秒杀 2.判断是否已经参加过团购了 3.是否已经秒杀到手机了 */

		int hour = seckill.isRightTime(
				Integer.parseInt(WechatConstant.KillTimeDay),
				Integer.parseInt(WechatConstant.KillTimeHourBef),
				Integer.parseInt(WechatConstant.KillTimeHourAft));

		if (hour > 0) {
			if (!seckill.hasParticipateGroup(openId)) {
				if (!seckill.hasParticipate(openId)) {
					// 判断手机是否秒杀完
					if (!seckill.hasSoldOut(hour)) {

						SeckillPhoneInfo seck = seckill.getPhoneInfo(hour);

						String code = seck.getHeadCode()
								+ GenerateRandCode.getRandomString(10);
						description = seck.getDescribe()
								+ WechatConstant.SeckillPub;
						imgUrl = imgUrl + seck.getImgUrl();
						SeckillUserInfo user = new SeckillUserInfo();
						user.setOpenId(openId);
						user.setCode(code);
						user.setTelephone(seckill.getTelephoneByOpenid(openId));
						user.setPhone(seck.getPhone());
						user.setDate(DateProcessTools.geTimestamp());

						seckill.saveSeckillUserInfo(user, hour);

					} else {
						description = WechatConstant.SoldOut;
						imgUrl = imgUrl + "soldout.jpg";
					}
				} else {
					description = WechatConstant.HasGotOne;
					imgUrl = imgUrl + "hasgotone.jpg";
				}
			} else {
				description = WechatConstant.SoldOut;
				imgUrl = imgUrl + "soldout.jpg";
			}
		} else {

			if (hour == -2) { // 活动已经结束
				description = WechatConstant.OutTime;
				imgUrl = imgUrl + "outtime.jpg";
			} else { // 活动还未还是
				description = WechatConstant.TimeInvalid;
				imgUrl = imgUrl + "timebef.jpg";
			}
		}

		article.setPicUrl(imgUrl);
		article.setDescription(description);
		article.setTitle("马鞍山移动双11秒杀活动");
		article.setUrl("http://weibodl.55zhe.net/wz.php?pageid=136166&openid=fromuserid&aw=wx.qq.com");
		articleList.add(article);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return Message2XmlUtil.newsMessageToXml(newsMessage);
	}

	public String resopneQuerySeckillCode(Map<String, String> mapXml) {

		String openId = mapXml.get("FromUserName");
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(mapXml.get("ToUserName"));
		textMessage.setToUserName(mapXml.get("FromUserName"));
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WechatConstant.RESP_MESSAGE_TYPE_TEXT); // 回复
		textMessage.setFuncFlag(1);
		String content = "";
		SeckillUserInfo user = seckill.getUserInfoByOpenid(openId);

		if (user != null) {
			content = "秒杀购机码:" + user.getCode() + "\n注：此码仅限手机号为"
					+ user.getTelephone() + "的用户购买“" + user.getPhone()
					+ "”手机\n请到指定营业厅去购买对应手机";
		} else {
			content = "您现在还未秒中任何手机哦！";
		}

		textMessage.setContent(content);
		return Message2XmlUtil.textMessageToXml(textMessage);

	}
}
