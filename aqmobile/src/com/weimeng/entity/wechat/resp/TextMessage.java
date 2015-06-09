package com.weimeng.entity.wechat.resp;

/**
 * 文本消息请求
 * 
 * @author 叶兰�?
 *
 */
public class TextMessage extends BaseMessage{
    
	// 消息内容 
	private String Content;  
	  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }

}
