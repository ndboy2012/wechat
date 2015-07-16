<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>铜陵移动号码登记</title>
<script type="text/javascript"
	src="${webRoot}/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${webRoot}/js/register/register.js"></script>
<link rel="stylesheet" type="text/css"
	href="${webRoot}/css/register/register.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
</head>
<div class="pop none">
	<div class="alert_wrap none">
		<img src="${webRoot}/files/images/register/smile2.png">
		<p class="alert_msg" id="alert_msg"></p>
		<div class="alert_btn" id="alert_btn">确定</div>
	</div>
</div>
<div class="pop_suc none">
	<div class="alert_wrap_suc none">
		<img src="${webRoot}/files/images/register/smile.png">
		<p class="alert_msg" id="alert_msg_suc"></p>
		<div class="alert_btn" id="alert_btn_suc">确定</div>
	</div>
</div>

<div class="top_warp">
	<div class="logo">
		<img alt="" src="${webRoot}/files/images/register/logo.png"
			style="width:100%;">
	</div>
	<div class="title">
		<span>铜陵移动&nbsp&nbsp&nbsp号码登记</span>
	</div>
	<div class="form">
		<div>
			<input type="text" class="textSty" id="telephone" placeholder="手机号">
		</div>
		<div class="code">
			<div class="code-left">
				<input type="text" class="validate" id="validate" placeholder="验证码">
			</div>
			<div class="code-right">
				<button id="achieveCode">获取验证码</button>
			</div>
		</div>
		<div class="recommend">
			<input type="text" class="textSty" id="recommender" placeholder="营业厅推荐人员手机号(选填)">
		</div>
	</div>
	<div class="footer">
		<div class="submit">
			<button id="submit">确定</button>
		</div>
		
	</div>
</div>
</html>
