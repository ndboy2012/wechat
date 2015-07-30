<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter,com.baidu.ueditor.upload.Uploader;"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	String rootPath = application.getRealPath( "/" );
/* 	Uploader up = new Uploader(request);
	   up.setSavePath("../upload");  //根据当前的JSP文件位置定位到upload文件,因jsp文件的位置而异
	   String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
	   up.setAllowFiles(fileType);
	   up.setMaxSize(10000); //单位KB
	   up.upload(); */
	out.write( new ActionEnter( request, rootPath ).exec() );
	
	
%>