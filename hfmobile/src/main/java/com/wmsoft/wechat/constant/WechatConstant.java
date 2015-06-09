package com.wmsoft.wechat.constant;


/*
 * 微信端内部常量
 */
public class WechatConstant {
   /**
    * 微信TOKEN
    */
	public static final String TOKEN = "Rav0PP";
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
       
    /*************************登记功能需要经常修改的参数*********************************/
    
    public static String validate_success;
    
    public static String validate_failed;
    
    public static String validate_none = "1";   //还未进行验证
    
    public static String validate_failure = "2";  //验证失败
    
    public static String validate_session_code = "validateCode";  //验证码
    
    public static String register_session_openId = "openId";     //openId
    
    public static String register_success;
    
    public static String has_register = "hasEixt";
    
    public static String register_describe;     //微信登记描述
    
    public static String SmsContent;
    
    /**************************************************************************/
    //基础URL包括了ip地址和项目名称
    public static final String baseUrl = "http://103.229.183.22/hfmobile";
    
    /*************************************************************************/
    
    public static final String KeyOfRegister = "登记"; //微信登记关键字
    
    public static final String KeyOfMerry = "红包"; //双蛋红包关键字
    
    public static final String KeyOfPrivilege = "大学生寒假特惠"; //特惠关键字
}
