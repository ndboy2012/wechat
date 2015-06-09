<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>找好友拆红包</title>
<script type="text/javascript"
	src="${webRoot}/plug-in/jquery/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${webRoot}/plug-in/merry/unopen.css">
<script type="text/javascript" src="${webRoot}/plug-in/merry/friend.js"></script>
<script type="text/javascript" src="${webRoot}/plug-in/snow/jq.snow.js"></script>
<script type="text/javascript" src="${webRoot}/plug-in/wechat_share/weixin_share.js"></script>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
</head>
<body>
	<div class="whole">
	    <img alt="" src="${webRoot}/files/images/merry/unopen2.png" style="position:absolute;width:100%;height:680px">
		<div class="excreteFriend">
			<span id="content"></span>
			<button class="friendbtn" id="friend">帮TA拆红包</button>
			<button class="iWant" onclick="iwant()">我也要红包</button>
		</div>
	</div>
	<input type="hidden" id="openId" value="${openId}">
	<input type="hidden" id="_wx_share_title"
		value="激情双旦，合肥移动送我神秘红包，快来帮我拆开吧！">
	<input type="hidden" id="_wx_share_desc_content"
		value="关注合肥移动官方微信，万份神秘红包等您抢！数量有限，先到先得……">
	<input type="hidden" id="_wx_share_img_url"
		value="${webRoot}/files/images/merry/wechatShare.png">
	<input type="hidden" id="_wx_share_link"
		value="${webRoot}/friendController/friend.do?openId=${openId}">
	<script type="text/javascript"
		src="${webRoot}/plug-in/wechat_share/weixin_share.js"></script>
</body>
</html>
