<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>安庆移动话费大派送</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">

		<!-- 是否启动webapp功能，会删除默认的苹果工具栏和菜单栏。 -->
		<meta name="apple-mobile-web-app-capable" content="no" />
		<!-- 显示手机信号、时间、电池的顶部导航栏的颜色。默认值为default（白色），可以定为black（黑色）和black-translucent（灰色半透明）。 -->
		<meta name="apple-mobile-web-app-status-bar-style" content="default" />
		<!-- 忽略页面中的数字识别为电话号码 -->
		<meta name="format-detection"content="telephone=no, email=no" />
		<!-- 启用360浏览器的极速模式(webkit) -->
		<meta name="renderer" content="webkit">
		<!-- 针对手持设备优化，主要是针对一些老的不识别viewport的浏览器，比如黑莓 -->
		<meta name="HandheldFriendly" content="true">
		<!-- 微软的老式浏览器 -->
		<meta name="MobileOptimized" content="320">
		<!-- uc强制竖屏 -->
		<meta name="screen-orientation" content="portrait">
		<!-- QQ强制竖屏 -->
		<meta name="x5-orientation" content="portrait">
		<!-- windows phone 点击无高光 -->
		<meta name="msapplication-tap-highlight" content="no">

		<link rel="stylesheet" href="../../css/recommend/squirrel-min.css" />
		<script type="text/javascript" src="../../js/recommend/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="../../js/recommend/alert.js"></script>
		
		<style type="text/css">
		body, h1, h2, h3, h4, h5, h6, p, a, button, input[type="text"] {font-family: Microsoft Yahei, Hiragino Sans GB, WenQuanYi Micro Hei, sans-serif;}
		button:focus, input[type="text"]:focus {outline: none;}
		body {background: url(../../images/recommend/U8377P1276DT20140827175602.jpg) top center no-repeat;}
		input[type="text"] {margin-left: 10px; width: 190px; height: 26px; border: 1px solid #ccc; border-radius: 5px; padding: 0 5px; display: block; margin: 0 auto; }
		.wx {background: url(../../images/recommend/U8377P1276DT20140826100901.jpg) top center no-repeat; height: 600px;}
		body .main_btn {
			display: block; margin: 10px auto 0;
			background-image: -webkit-linear-gradient(top, #ffa838, #ffca57);
			background-image: -moz-linear-gradient(top, #ffa838, #ffca57);
			background-image: -ms-linear-gradient(top, #ffa838, #ffca57);
			background-image: -o-linear-gradient(top, #ffa838, #ffca57);
			background-image: linear-gradient(top, #ffa838, #ffca57);
			border: none;
			width: 200px;
			height: 28px;
			color: #fff;
			border-radius: 5px;
		}
		</style>
  </head>
   <body style="height: 600px;position:relative;">
   <div id="zzao" style="position:absolute;left:0;top:0;width:100%;height:100%;z-index:1000;background:#000; 
         opacity: 0.80;/支持css3的浏览器-moz-opacity:0.8/*其他*/filter :alpha(opacity:80);/*ie*/display:none;" >
        <div class="wx"></div>
   </div>
   
   <div class="row">
	<div class="col-mb-12" style="height: 483px;"></div>
	<div class="col-mb-12">
		<input type="text"  name="recommtel" id="recommtel" placeholder="输入推荐人号一起赢话费 ">
		<button class="main_btn" onclick="recommendtel()">分享到朋友圈赢话费</button>
	</div>
  </div>
      <input type="text" name="telephone" style="display:none;" id = "telephone" value="<%=session.getAttribute("telephone") %>">
  </body>
  <script type="text/javascript">
     function recommendtel() {
    	
    	  var regBox = { regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/}; 
    	  var recommtel = $("#recommtel").val();
    	  var telephone = $("#telephone").val();
    	  var mflag = regBox.regMobile.test(recommtel);
    	  if(recommtel == "") {
      		 alert("请输入手机号码");
      	  } else if(!(mflag)) {
      		 alert("请填写正确的手机号码");
      	  } else {
      		 $.ajax({
      			url:"wechatTelRecommController/recommTelephone.do",
      			type:"get",
      			dataType:"json",
      			data:{"recommendtel":recommtel,"telephone":telephone},
      		    success:function(date) {
      		    	date = eval(date);
      		    	if(date.msg=="success") {
      		    	   $("#zzao").show();
      		    	} else {
      		    		alert(date.msg);
      		    	}
      		    }
      		 });
      	  }
     }
    
     
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
         });
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
