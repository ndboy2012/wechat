<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <meta forua="true" http-equiv="Cache-Control" content="no-cache" />
    <meta name="viewport"  content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0" />
    <meta name="viewport"  content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">   
    <link href="../../css/signInggk/activity-style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>查看获取多少话费</title>
    <style type="text/css">
    body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(../../images/recommend/bg.jpg);
   }
    </style>
  </head> 
  <body>
	<table width="320" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="100"><img src="../../images/recommend/showbg.jpg" width="320" height="100" /></td>
		</tr>
		<tr>
			<td height="5" bgcolor="#FFCC00"></td>
		</tr>
		<tr>
			<td align="center" valign="middle">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
					<tr>
						<td align="center" valign="middle" bgcolor="#CCCCCC"class="STYLE69">
							<table width="100%" border="0" cellpadding="0" cellspacing="1" id="table_prize">
								<tr>
									<td width="69" height="25" align="center" bgcolor="#FF9900"  class="STYLE78">编号</td>
									<td width="85" height="25" align="center" bgcolor="#FF9900"  class="STYLE78">推我的号码</td>
									<td width="167" height="25" align="center" bgcolor="#FF9900" class="STYLE78">推我的日期</td>
								</tr>
							</table>
						</td>
					</tr>
					 <tr>
						<td height="10" align="left" valign="middle"></td>
					</tr>
				</table> 
			</td>
		</tr>
		<tr>
		<td height="50"></td>
		</tr>
	</table>
	<input id="telephone" type="hidden" value="<%=session.getAttribute("telephone")%>"> 
  </body>
  <script src="../../js/recommend/jquery-1.8.2.min.js" type="text/javascript"></script>			
				<script type="text/javascript">
					$(function() {
							$("#table_prize").html('<tr><td width="52" height="25" align="center" bgcolor="#FF9900" class="STYLE78">编号</td><td width="75" height="25" align="center" bgcolor="#FF9900" class="STYLE78">推荐号码</td><td width="130" height="25" align="center" bgcolor="#FF9900" class="STYLE78">推荐日期</td> </tr>');							
							$.ajax({									
								        url : "wechatTelRecommController/listAllrecommendInfo.do",//调用接口	
								        type : "get",
										dataType : "json",
									    data : {"telephone":$("#telephone").val()},
										success : function(data) {
											var msg = data.msg;
											var list = data.obj;
											if (list != null) {
												$.each(list,function(i,item) {
													 $("#table_prize").append('<tr><td height="24" align="center" valign="middle" bgcolor="#FFFFFF">'+ (i+1)
															+ '</td><td height="24" align="center" valign="middle" bgcolor="#FFFFFF">'+ item.recommendTel + '</td><td height="24" align="center" valign="middle" bgcolor="#FFFFFF">'+ item.recommendDate.toString().substring(5,7)+ '月'+ item.recommendDate.toString().substring(8,10)+ '日'+ '</td></tr>');
												});	
												$("#table_prize").append('<tr><td height="24" colspan="3" align="center" valign="middle" bgcolor="#FFFFFF">'+msg+'</tr>');
											} else {
												$("#table_prize").append('<tr><td height="24" colspan="3" align="center" valign="middle" bgcolor="#FFFFFF">亲！您还没有验证信息暂时无法进行操作</tr>');
											}
										},
										error : function() {
											$("#table_prize").append('<tr><td height="24" colspan="3" align="center" valign="middle" bgcolor="#FFFFFF">用户验证已过期，请重新验证</td></tr>');
										},
									});
					});
					
					
				    var imgUrl = "http://103.229.183.247/aqMobile/images/recommend/ads-100M.jpg";
				    var lineLink = "http://103.229.183.247/aqMobile/web/recommend/calls.jsp";
				    var descContent = "分享给您的好友，让你的好友为你推荐话费";
				    var shareTitle = "安庆移动分享好友得话费";
				    var appid = '';
				       
				      function shareFriend() {
				          WeixinJSBridge.invoke('sendAppMessage',{
				              "appid": appid,
				              "img_url": imgUrl,
				              "img_width": "200",
				              "img_height": "200",
				              "link": lineLink,
				              "desc": descContent,
				              "title": shareTitle
				          }, function(res) {
				              //_report('send_msg', res.err_msg);
				          })
				      }
				      function shareTimeline() {
				          WeixinJSBridge.invoke('shareTimeline',{
				              "img_url": imgUrl,
				              "img_width": "200",
				              "img_height": "200",
				              "link": lineLink,
				              "desc": descContent,
				              "title": shareTitle
				          }, function(res) {
				                 //_report('timeline', res.err_msg);
				          });
				      }
				      function shareWeibo() {
				          WeixinJSBridge.invoke('shareWeibo',{
				              "content": descContent,
				              "url": lineLink,
				          }, function(res) {
				              //_report('weibo', res.err_msg);
				          });
				      }
				      // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
				      document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
				          // 发送给好友
				          WeixinJSBridge.on('menu:share:appmessage', function(argv){
				              shareFriend();
				          });
				          // 分享到朋友圈
				          WeixinJSBridge.on('menu:share:timeline', function(argv){
				              shareTimeline();
				          });
				          // 分享到微博
				          WeixinJSBridge.on('menu:share:weibo', function(argv){
				              shareWeibo();
				          });
				      }, false);
				</script>
</html>
