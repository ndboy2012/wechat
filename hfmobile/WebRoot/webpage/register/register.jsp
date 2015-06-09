<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
  <title>合肥移动号码登记</title>
  <script type="text/javascript" src="${webRoot}/plug-in/jquery/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/register/register.css" />
  <script type="text/javascript" src="${webRoot}/plug-in/register/register.js"></script>
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
  <div class="all">
	<div class="header">
		<img alt="" src="${webRoot}/files/images/register/header.png"
			class="header_img">
	</div>
	<div class="center">
		<div class="text_position">
			<input type="text" class="text" id="telephone" placeholder="手机号">
		</div>
		<div class="text_position">
			<div class="valid_code">
				<input type="text" class="text" id="validateCode" placeholder="输入验证码">
			</div>
			<div class="valid_btn_pos">
				<input type="button" class="valid_btn" id="valid_btn" value="获取验证码">
			</div>
		</div>
		<div class="text_position">
			<input type="text" class="text" id="recommend_telephone" placeholder="营业厅推荐人手机号(选填)">
		</div>
		<div class="text_position">
			<button class="submit_btn">确定</button>
		</div>
	</div>
	<div class="fotter">
		<img alt="" src="${webRoot}/files/images/register/fotter.png" class="foot_bg">
	</div>
	<input type="hidden" id="success_href" value="${webRoot}/stuPreferentialController/stuPreferential.do" >
</div>
</html>
