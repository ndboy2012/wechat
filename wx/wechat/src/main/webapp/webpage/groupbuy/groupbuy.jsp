<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>拼团购机</title>
<link rel="stylesheet" type="text/css"
	href="${webRoot}/css/groupbuy/groupbuy.css">
<script type="text/javascript"
	src="${webRoot}/plug-in/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${webRoot}/js/groupbuy/groupbuy.js"></script>

<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
</head>
<body>
	<div class="banner"></div>
	<div class="center">
		<div class="title">
			<span>5款手机任你选:</span>
		</div>
		<hr>
	</div>
	<div id="content"></div>
	<input type="hidden" id="picture" value="${webRoot}/files/images/groupbuy/">
	<div class="describe">
		<span>★ 活动详情:</span>
		<ul>
			<li>1.活动时间：即日起至2015年1月20日</li>
			<li>2.每人限参与拼团一次，30人起团，起团即优惠50元，拼团人数每增加10人，则拼团机型裸机价优惠10元，最高300元封顶；</li>
			<li>3.成功拼团，1月21日至31日，凭拼团购机码（微信发送关键字“购机码”或菜单栏获取）、手机号和有效身份证件，到市县沟通“100”营业厅或义安路卖场进行线下办理购机，购机时需办理30元500M以上的包年套餐</li>
			<li>4.本次活动仅限铜陵移动用户参与，最终解释权归铜陵移动。</li>
		</ul>
	</div>
	<div>
		<HR>
	</div>
</body>
</html>
