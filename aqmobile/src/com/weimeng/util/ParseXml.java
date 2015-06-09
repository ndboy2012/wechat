package com.weimeng.util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.weimeng.entity.web.signInggk.TeleAndPrizes;





/**
 * XML文件解析工具,接收微信服务器传送过来的XML文件信息请求�?然后进行解析各个字段的名称和值，存入到Map中返�?
 * 
 * @author Administrator
 * 
 */
public class ParseXml {

	public static Map<String, String> parse(HttpServletRequest request)
			throws Exception {
			
		Map<String, String> mapXml = new HashMap<String, String>();		
			
		InputStream inputStream = request.getInputStream();	
		
		SAXReader reader = new SAXReader();
		
		Document document = reader.read(inputStream);			
		
		Element root = document.getRootElement();
		
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();			 
		
		for (Element e : elementList) {						
			mapXml.put(e.getName(), e.getText());
		}		
		// 释放资源
		inputStream.close();
		inputStream = null;  	
		return mapXml;
	}
	
	/*
	 * 这个XML解析工具只能解析最多含两级结点的xml文件
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> parse(String path,String prizeInfo) throws Exception {
		Map<String, Object> mapXml = new HashMap<String, Object>();	
		
		File xmlFile = new File(path); 
		if(xmlFile.exists()) {
			SAXReader reader = new SAXReader();  
			
			Document document = reader.read(xmlFile);
			
			Element root = document.getRootElement(); 
			
			List<Element> listroot = root.elements();
			
			String before = null;
			List<String> list = null;
			for(int i=0;i<listroot.size();i++) {
				
				  Element e = listroot.get(i);
				  if(e.getTextTrim().equals("")) {    //说明是一个结点，还有子节点
					   if(!e.getName().equals(before)) {     //如果和前一个子结点不同
						       list = new ArrayList<String>();
						       before = e.getName();
						       mapXml.put(e.getName(), list);
					   } else {
						      list = (List<String>) mapXml.get(e.getName());
					   }
					   List<Element> listson = e.elements();
					   for(int j=0;j<listson.size();j++) {
						   Element eson = listson.get(j);
						   list.add(eson.getText());
					   }
					   
				  } else {
					  mapXml.put(e.getName(), e.getText());
				  }			
			}
		}
	    return mapXml;
	}
	
	public static boolean ConvertToXmlFile(String path,Map<String,Object> map) {
		boolean result = false;
		Document document = DocumentHelper.createDocument();
		Element winnersElement = document.addElement("winners");    //添加根结点
		@SuppressWarnings("unchecked")
		List<TeleAndPrizes> listtel = (List<TeleAndPrizes>) map.get("winner");
		
		for(int i=0;i<listtel.size();i++) {
			TeleAndPrizes tel = listtel.get(i);
			
			Element winnerElement = winnersElement.addElement("winner");
			Element openidElement = winnerElement.addElement("openid");
			openidElement.setText(tel.getOpenId());
			Element telephone = winnerElement.addElement("telephone");
			telephone.setText(tel.getTelephone());
			Element prizeName = winnerElement.addElement("prizeName");
			prizeName.setText(tel.getPrizeName());
		}
		
		try {
			
			 OutputFormat format = OutputFormat.createPrettyPrint();
			 format.setEncoding("gbk");
			 XMLWriter writer =  new XMLWriter(new FileWriter(new File(path)),format);
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
