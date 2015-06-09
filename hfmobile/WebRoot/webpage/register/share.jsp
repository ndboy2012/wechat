<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
  <title>100M流量普惠来袭</title>
  <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/register/base.css" />
</head>
<body>
<div class="pop none">
	<div class="apply_sucess none" id="apply_sucess">
		<img src="${webRoot}/files/images/register/applay_sucess.png">
	</div>
	<div class="alert_wrap none">
		<img src="${webRoot}/files/images/register/smile2.png">
		<p class="alert_msg" id="get_failure">未领取成功</p>
		<div class="alert_btn" id="get">确定</div>
	</div>
	<div class="validate_tel none">
	    <div class="close">
	      <a href="javascript:void(0)" onclick="closetab()">X</a>
	    </div>
	    <p class="alert_msg">验证您的手机号码</p>
	    <input type="text" class="input_text" id="validate_tel">
	    <div class="alert_btn" id="validate">确定</div>
	    <p class="alert_msg" id="validate_msg">请输入您需要验证的手机号</p>
	</div>
</div>
<div class="pop2 none">
	<div class="share_friends">
		<img src="${webRoot}/files/images/register/share_tip.png">
	</div>
</div>
<header>
	<img class="hd" src="${webRoot}/files/images/register/hd.png" alt="合肥移动150M流量活动">
</header>
<section>
	<article>
		<div class="mn">
			<div class="m_bg_w">
			   <img src="${webRoot}/files/images/register/m_bg.png" class="m_bg">
			</div>
			<ul class="mn_list">
				<li>
					<img src="${webRoot}/files/images/register/n1.png" class="num" alt="1">
					<div>
						<p><em>关注“合肥移动”官微</em>并<em>登记手机号</em></p>
						<p class="small">（如果已经关注并登记过的就可以跳过啦）</p>
						<img class="bind" src="${webRoot}/files/images/register/bind.png">
					</div>
				</li>
				<li>
					<img src="${webRoot}/files/images/register/n2.png" class="num" alt="2">
					<div>
						<p>如果您已关注并成功登记，发送关键字“赚流量”或点击菜单栏【查询服务】→【赚流量】按钮回到本页面点击领取流量；</p>
						<img class="apply" src="${webRoot}/files/images/register/apply.png">
						<div class="apply_txt" id="apply_txt">已经有<%=request.getAttribute("regCount")%>人参与了流量申请</div>
					</div>
				</li>
				<li>
					<img src="${webRoot}/files/images/register/n3.png" class="num" alt="3">
					<div>
						<p>开心等待流量送到账，分享到朋友圈，喊上好友一起来参加，每增加1000人，我们加送100M流量（1G封顶）；</p>
						<img class="share_btn" src="${webRoot}/files/images/register/share.png">
					</div>
				</li>
			</ul>
		</div>
	</article>
	<article>
		<div class="info">
			<div class="t">活动时间：</div>
			<p>即日起至2014年12月31日</p>
			<div class="t">领取需知：</div>
			<ul class="info_list">
				<li>
					<i>1、</i>
					<p>流量申领后请仔细确认申领小任务是否都已完成哦；</p>
				</li>
				<li>
					<i>2、</i>
					<p>本活动仅限合肥移动用户参与，非合肥移动用户不可兑现流量，每位用户不可重复参与；</p>
				</li>
				<li>
					<i>3、</i>
					<p>发奖时间：100M流量红包会在您完成任务后的次月10日前派发到您的账户，届时请关注；流量限赠送当月使用，次月失效哦；</p>
				</li>
				<li>
					<i>4、</i>
					<p>分享到朋友圈喊上朋友一起来参与，界面显示总用户参与数每增加1000人，我们在原赠送的基础上再赠送每人100M流量，每位用户最高获总流量1G封顶；</p>
				</li>
				<li>
					<i>5、</i>
					<p>本次活动的最终解释权归合肥移动所有。</p>
				</li>
			</ul>
		</div>
	</article>
</section>
    <input type="text"  id="temp" value="${telephone}" hidden="hidden">
    <script src="${webRoot}/plug-in/jquery/jquery.min.js"></script>
    <script src="${webRoot}/plug-in/register/base.js"></script>
        <input type="hidden" id="_wx_share_title" value="合肥移动100M流量普惠来袭！现在开抢(每人至少100M哦)">
        <input type="hidden" id="_wx_share_desc_content" value="关注合肥移动官微，完成打怪练级小任务，100M流量等你免费拿！">
        <input type="hidden" id="_wx_share_img_url" value="${webRoot}/files/images/register/hd.png">
        <input type="hidden" id="_wx_share_link" value="${webRoot}/registerController/share.do">
        <script type="text/javascript" src="${webRoot}/plug-in/register/weixin_share.js"></script>
</body>
<script type="text/javascript">
function closetab() {
	$("#validate_msg").html("");
	$(".pop").hide();
}
</script>
</html>