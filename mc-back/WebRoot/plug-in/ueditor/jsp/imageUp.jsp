<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
   request.setCharacterEncoding("utf-8");
   response.setCharacterEncoding("utf-8");
   Uploader up = new Uploader(request);
   up.setSavePath("../upload");  //根据当前的JSP文件位置定位到upload文件,因jsp文件的位置而异
   String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
   up.setAllowFiles(fileType);
   up.setMaxSize(10000); //单位KB
   up.upload();
   /*url地址修正*/
   String urlFix = up.getUrl().replaceAll("../","");      //这儿的位置需要根据自己ueditor的目录深度来定需要怎么样定位到根目录
   response.getWriter().print("{'original':'"+up.getOriginalName()+"','url':'"+urlFix+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");
%>