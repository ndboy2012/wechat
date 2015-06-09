package com.weimeng.entity.wechat.req;

/**
 * 
 * 图片消息请求
 * @author Administrator
 *
 */
public class ImageMessage extends BaseMessage{
   
	// 图片链接  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
}
