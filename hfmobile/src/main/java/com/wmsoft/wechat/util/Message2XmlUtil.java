package com.wmsoft.wechat.util;


import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wmsoft.wechat.entity.commom.Article;
import com.wmsoft.wechat.entity.resp.MusicMessage;
import com.wmsoft.wechat.entity.resp.NewsMessage;
import com.wmsoft.wechat.entity.resp.TextMessage;


public class Message2XmlUtil {
     
	 /** 
     * 文本消息对象转换成xml 
     *  
     * @param textMessage 文本消息对象 
     * @return xml 
     */  
    public static String textMessageToXml(TextMessage textMessage) {  
        xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);  
    }  
    
    /** 
     * 音乐消息对象转换成xml 
     *  
     * @param musicMessage 音乐消息对象 
     * @return xml 
     */  
    public static String musicMessageToXml(MusicMessage musicMessage) {  
        xstream.alias("xml", musicMessage.getClass());  
        return xstream.toXML(musicMessage);  
    }  
    
    /** 
     * 图文消息对象转换成xml 
     *  
     * @param newsMessage 图文消息对象 
     * @return xml 
     */  
    public static String newsMessageToXml(NewsMessage newsMessage) {  
        xstream.alias("xml", newsMessage.getClass());  
        xstream.alias("item", new Article().getClass());  
        return xstream.toXML(newsMessage);  
    }  
    
    /** 
     * 扩展xstream，使其支持CDATA�?
     *  
     * @date 2013-05-19 
     */  
    private static XStream xstream = new XStream(new XppDriver() {
    	 public HierarchicalStreamWriter createWriter(Writer out) {  
             return new PrettyPrintWriter(out) {  
                 // 对所有xml节点的转换都增加CDATA标记  
                 boolean cdata = true;  
   
                 @SuppressWarnings("unchecked")  
                 public void startNode(String name, Class clazz) {  
                     super.startNode(name, clazz);  
                 }  
   
                 protected void writeText(QuickWriter writer, String text) {  
                     if (cdata) {  
                         writer.write("<![CDATA[");  
                         writer.write(text);  
                         writer.write("]]>");  
                     } else {  
                         writer.write(text);  
                     }  
                 }  
             };  
         }  
    });
}
