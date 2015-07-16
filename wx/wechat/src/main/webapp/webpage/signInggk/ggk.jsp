<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../context/mytags.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta forua="true" http-equiv="Cache-Control" content="no-cache" />
<title>安庆移动每日签到抽奖</title>
<link href="${webRoot}/css/signInggk/activity-style.css" rel="stylesheet" type="text/css">
<script src="${webRoot}/js/signInggk/jquery.js" type="text/javascript"></script>
<script src="${webRoot}/js/signInggk/jQueryRotate.js" type="text/javascript"></script>
<script src="${webRoot}/js/signInggk/jquery.easing.min.js"></script>
<script src="${webRoot}/js/signInggk/jscroller-0.4.js" type="text/javascript"></script>
<script src="${webRoot}/js/signInggk/wScratchPad.js" type="text/javascript"></script>
<script src="${webRoot}/js/signInggk/alert.js" type="text/javascript"></script>
<script type="text/javascript" src="${webRoot}/js/signInggk/lottery_ggk.js"></script>

</head>
<script type="text/javascript">
	function loading(canvas, options) {
		this.canvas = canvas;
		if (options) {
			this.radius = options.radius || 12;
			this.circleLineWidth = options.circleLineWidth || 4;
			this.circleColor = options.circleColor || 'lightgray';
			this.moveArcColor = options.moveArcColor || 'gray';
		} else {
			this.radius = 12;
			this.circelLineWidth = 4;
			this.circleColor = 'lightgray';
			this.moveArcColor = 'gray';
		}
	}
	loading.prototype = {
		show : function() {
			var canvas = this.canvas;
			if (!canvas.getContext)
				return;
			if (canvas.__loading)
				return;
			canvas.__loading = this;
			var ctx = canvas.getContext('2d');
			var radius = this.radius;
			var me = this;
			var rotatorAngle = Math.PI * 1.5;
			var step = Math.PI / 6;
			canvas.loadingInterval = setInterval(function() {
				ctx.clearRect(0, 0, canvas.width, canvas.height);
				var lineWidth = me.circleLineWidth;
				var center = {
					x : canvas.width / 2,
					y : canvas.height / 2
				};

				ctx.beginPath();
				ctx.lineWidth = lineWidth;
				ctx.strokeStyle = me.circleColor;
				ctx.arc(center.x, center.y + 20, radius, 0, Math.PI * 2);
				ctx.closePath();
				ctx.stroke();
				//在圆圈上面画小圆   
				ctx.beginPath();
				ctx.strokeStyle = me.moveArcColor;
				ctx.arc(center.x, center.y + 20, radius, rotatorAngle,
						rotatorAngle + Math.PI * .45);
				ctx.stroke();
				rotatorAngle += step;

			}, 100);
		},
		hide : function() {
			var canvas = this.canvas;
			canvas.__loading = false;
			if (canvas.loadingInterval) {
				window.clearInterval(canvas.loadingInterval);
			}
			var ctx = canvas.getContext('2d');
			if (ctx)
				ctx.clearRect(0, 0, canvas.width, canvas.height);
		}
	};
</script>

</head>
<body data-role="page" class="activity-scratch-card-winning">

	<div class="main">
		<div class="cover">
			<img src="${webRoot}/files/images/sign/activity-scratch-card-bannerbg.png">
			<!-- 显示刮开后显示的东西 -->
			<div id="prize"></div>  
			<div id="scratchpad"></div>   <!-- scratchpad一个标签用来使用刮东西 -->
		</div>
		<div class="boxcontent" style="display:none">
			percent scratched: <span id="percent_scratched"></span>
		</div>
		<div class="content">
			<div id="zjl"  style="display:none" class="boxcontent boxwhite">
				<div class="box">
					<div class="title-red">
						<span id="title_prize"></span>
					</div>
					<div class="Detail">
						<p>
							<span class="red" id="prizetype"> 谢谢参与 </span>
						</p>
						<p class="red"></p>
						<p>
							<input class="pxbtn" name="提 交" id="save-btn" type="button" value="下一张">
						</p>
					</div>
				</div>
			</div>
			
			<div class="boxcontent" style="text-align:center">
				<div class="box" style="padding:5px; color:#900; text-align:left;display:none">
					公告:为了向您提供更优质的服务，我公司将在<b>2014年7月17日23时至2014年7月18日6时</b>进行系统升级。在此期间中奖用户奖品将于次日补发，带来不便之处深表歉意，请您谅解。
				</div>
				<input id="txt_returnCode" type="hidden"  value="1400"> 
				<input id="openId" type="hidden" value="${openId}"> 
				<span  class="lottery_info">
				<span id="mobile" style="display:none">0</span>本月您已签到<span id="all_num">0</span> 次,还有<span id="lottery_num">0</span>张刮刮卡未使用</span>
			</div>

			<div class="boxcontent boxwhite">
				<div class="box">
					<div class="title-brown"> <span> 中奖名单： </span> </div>					
					<div class="Detail">
						<div id="scroller_container">
							<div id="scroller" class="winner_box">
								<ul class="winnerList" id="win-list-part1"></ul>
							</div>
							<div id="scroller2" class="winner_box"></div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<script type="text/javascript">
			$(function() {
				$.ajax({							
					        url : "listPrizeInfo.do",//调用接口
							dataType : "json",
							success : function(data) {
								var list = data.obj;
								if (list != null) {
									$.each(list,function(i, item) {
									      $("#win-list-part1").append('<li class="winner-item winner-border"> <p class="winner_name">'+ item.telephone.substring(0,3)+'****'+ item.telephone.substring(7,11)
															+ '</p><p class="winner_prize">'+ item.prizeName+ '</p></li>');

									});
								 } else {
								          $("#win-list-part1").append('<li class="winner-item winner-border"><p class="winner_name"></p><p class="winner_prize">暂无数据！</p></li>');
								    } 
								 
								},
							error : function() {
							              $("#win-list-part1").append('<li class="winner-item winner-border"><p class="winner_name">'
														+ item.mobile+ '</p><p class="winner_prize">暂无数据</p></li>');
								},
							timeout : 4000
						});

			});
		</script>
		<script type="text/javascript">
			var speed = 60;
			scroller2.innerHTML = scroller.innerHTML;
			function Marquee() {
				if (scroller_container.scrollTop >= scroller.scrollWidth) {
					scroller_container.scrollTop = 0;
				} else {
					scroller_container.scrollTop++;
				}
			}
			var MyMar = setInterval(Marquee, speed);
			scroller_container.onmouseover = function() {
				clearInterval(MyMar);
			};
			scroller_container.onmouseout = function() {
				MyMar = setInterval(Marquee, speed);
			};
		</script>
		<div style="clear:both;"></div>
	</div>
	
	<div style="padding-bottom:30px"></div>
	<div style="background-color:#484850;width:100%;position:fixed;bottom:0px;left:0px;font-family:'微软雅黑';z-index:999;filter:alpha(Opacity=80);-moz-opacity:0.8;opacity: 0.8;">

		<table width="300" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#484850">
			<tbody>
				<tr>
					<td height="45">
					<table width="300" border="0" cellspacing="0" cellpadding="0">
					   <tbody>
							<tr>
								<td width="140" align="center" valign="middle" class="activity-lottery-btn">
									     <a href="http://www.ygj.com.cn/wz.php?pageid=122618&openid=fromuserid&aw=wx.qq.com" id="link_rule" style="display:block;width:110px; height:26px">活动细则</a>
								</td>
									<td width="20" align="center" valign="middle"></td>
									<td width="140" align="center" valign="middle" class="activity-lottery-btn">
									    <a href="show.do?openId=${openId}" id="link_prizeRecord" style="display:block;width:110px; height:26px">我的奖品</a>
									</td>
								</tr>
				     </tbody>
		          </table>
		          </td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>
