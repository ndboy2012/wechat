<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<c:set var="webRoot" value="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="${webRoot}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${webRoot}/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${webRoot}/easyui/demo/demo.css">
<script type="text/javascript" src="${webRoot}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${webRoot}/easyui/jquery.easyui.min.js"></script>