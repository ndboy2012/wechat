<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'preview.jsp' starting page</title>
    <script type="text/javascript" src="${webRoot}/plug-in/ueditor/ueditor.parse.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript">
    /* setTimeout(function(){uParse('div',{
    	'highlightJsUrl':'${webRoot}/plug-in/ueditor/third-party/SyntaxHighlighter/shCore.js',
    	 'highlightCssUrl':'${webRoot}/plug-in/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css'})
    },300); */
    </script>
  </head>
  
  <body>
  asdfas
     <div>${article.content}</div>
     <div>${articling}</div>
  </body>
</html>
