<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>未打开红包</title>
<link rel="stylesheet" type="text/css"
	href="${webRoot}/css/merry/unopen.css">
<script type="text/javascript" src="${webRoot}/js/merry/unopen.js"></script>
<script type="text/javascript" src="${webRoot}/js/jq.snow.js"></script>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
</head>
<body>
	<div class="pop none">
		<div class="share_friend none">
			<img alt="" src="${webRoot}/images/merry/share_tip.png"
				style="width:100%">
		</div>
		<div class="prizeShow none">
			<div class="prizeContent">
				<span id="prizeContent" class="prizeContentSty"></span>
			</div>
			<img alt="" src="${webRoot}/images/merry/success.png"
				style="position: absolute;width:70%;left:10%">
		</div>
	</div>
	
	<div class="whole">
		<img alt="" src="${webRoot}/images/merry/unopen.png"
			style="position:absolute;width:100%;height:680px">
		<div class="excrete">
			<span id="content"></span>
			<button class="friendbtnun" id="friend">找好友拆红包</button>
			<button class="friendbtnun" id="expose" hidden="true">打开红包</button>
		</div>
	</div>
	
	<input type="hidden" id="openId" value="${openId}">
	<input type="hidden" id="_wx_share_title" value="迎双旦，庆和4G一周年，安庆移动送我神秘红包，快来帮我拆吧！">
	<input type="hidden" id="_wx_share_desc_content"
		value="即日起至2015年1月31日，关注安庆移动官方微信，流量、话费、通话包、古井贡酒…万份红包等您拿！">
	<input type="hidden" id="_wx_share_img_url"
		value="${webRoot}/images/merry/wechatShare.jpg">
	<input type="hidden" id="_wx_share_link"
		value="${webRoot}/friendController/friend.do?openId=${openId}">
	<script type="text/javascript"
		src="${webRoot}/js/weixin_share.js"></script>
</body>
</html>
