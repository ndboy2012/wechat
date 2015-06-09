<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../mytags.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>安庆移动微信平台后台管理</title>
  <script type="text/javascript" src="${webRoot}js/time.js"></script>
  </head>
  
  <body>
     <div id="cc" class="easyui-layout" style="width:100%;height:100%;">
        <div data-options="region:'north'" style="height:100px">   
        </div>
        <div data-options="region:'west',split:false," title="导航栏" style="width:250px;"></div>
        <div data-options="region:'center',split:false">
            <div id="maintabs" class="easyui-tabs" fit="true" border="false">
			<div class="easyui-tab" title="首页" href="../mainController/home.do"
				  style="padding: 2px; overflow: hidden;">
			</div>
		</div>
        </div>
    </div>
  </body>
</html>
