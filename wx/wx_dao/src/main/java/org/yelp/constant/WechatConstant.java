package org.yelp.constant;

public class WechatConstant {

	/**
	 * 微信TOKEN
	 */
	public static final String TOKEN = "Rav0PP";

	public static final String MESSAGE_TYPE = "MsgType";
	/**
	 * 返回文本消息类型
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音乐
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送事件
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	public static final String FROMEUSERNAME = "FromUserName";

	public static final String TOUSERNAME = "ToUserName";

	public static final String BASE_URL = "120.26.70.137/wechat/";

	public static final String BASE_IMG_URL = "http://120.26.70.137/wechat/files/images/";

	/******************************* 微信关键字 *******************************************/

	public static final String KEY_REGISTER = "登记";

	public static final String KEY_GROUPBUY = "团购";

	public static final String KEY_SIGN = "签到";

	public static final String KEY_BAND = "宽带";

	public static final String KEY_CALLS = "推荐";
	
	public static final String KEY_SECKILL = "秒杀";
	
	public static final String KEY_SECKILL_CODE = "秒杀码";

	/*************************************************************************************/

	/***** 马鞍山双11秒杀活动常量 begin ******/

	public static String TimeInvalid = "";

	public static String SoldOut = "";

	public static String HasGotOne = "";

	public static String OutTime = "";

	public static String KillKeyWordAsk = "";

	public static String KillKeyWordRep = "";

	public static String KillTimeDay = "";

	public static String KillTimeHourBef = "";

	public static String KillTimeHourAft = "";
	
	public static String SeckillPub = "";
	/***** 马鞍山双11秒杀活动秒杀常量 end ******/

}
