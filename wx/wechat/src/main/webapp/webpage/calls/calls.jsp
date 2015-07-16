<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../context/mytags.jsp"%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<title>安庆移动推荐赢话费</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="renderer" content="webkit">

<!-- 是否启动webapp功能，会删除默认的苹果工具栏和菜单栏。 -->
<meta name="apple-mobile-web-app-capable" content="no" />
<!-- 显示手机信号、时间、电池的顶部导航栏的颜色。默认值为default（白色），可以定为black（黑色）和black-translucent（灰色半透明）。 -->
<meta name="apple-mobile-web-app-status-bar-style" content="default" />
<!-- 忽略页面中的数字识别为电话号码 -->
<meta name="format-detection" content="telephone=no, email=no" />
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

<link rel="stylesheet" href="${webRoot}/css/calls/squirrel-min.css" />
<script type="text/javascript"
	src="${webRoot}/js/calls/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${webRoot}/js/calls/alert.js"></script>

<style type="text/css">
.bgi1 {
	background:
		url('${webRoot}/files/images/calls/U8377P1276DT20140822174957.jpg')
		top center no-repeat;
}

.bgi2 {
	background:
		url('${webRoot}/files/images/calls/U8377P1276DT20140822174956.jpg')
		top center no-repeat;
}

.bgi3 {
	background:
		url('${webRoot}/files/images/calls/U8377P1276DT20140822174954.jpg')
		top center no-repeat;
}

.bgi0 {
	background:
		url('${webRoot}/files/images/calls/U8377P1276DT20140822174955.jpg')
		top center no-repeat;
}

.wx {
	background:
		url('${webRoot}/files/images/calls/U8377P1276DT20140822174955.jpg')
		top center no-repeat;
	height: 600px;
}

body,h1,h2,h3,h4,h5,h6,p,a,button,input[type="text"] {
	font-family: Microsoft Yahei, Hiragino Sans GB, WenQuanYi Micro Hei,
		sans-serif;
}

button:focus,input[type="text"]:focus {
	outline: none;
}

.shouji input[type="text"] {
	margin-left: 10px;
	width: 180px;
	height: 26px;
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 0 5px;
}

.shouji button {
	background-image: -webkit-linear-gradient(top, #ffa838, #ffca57);
	background-image: -moz-linear-gradient(top, #ffa838, #ffca57);
	background-image: -ms-linear-gradient(top, #ffa838, #ffca57);
	background-image: -o-linear-gradient(top, #ffa838, #ffca57);
	background-image: linear-gradient(top, #ffa838, #ffca57);
	border: none;
	width: 72px;
	height: 28px;
	color: #fff;
	border-radius: 5px;
}

body .main_a {
	width: 100%;
	height: 100%;
	display: block;
}
</style>
</head>
<body class="bgi1" style="height: 600px;position:relative;">

	<div id="zzao"
		style="position:absolute;left:0;top:0;width:100%;height:100%;z-index:1000;background:#000; 
         opacity: 0.80;/支持css3的浏览器-moz-opacity:0.8/*其他*/filter :alpha(opacity:80);/*ie*/">
		<div class="wx"></div>
	</div>

	<div class="row">
		<div class="col-mb-12" style="height: 105px;"></div>
		<div class="col-mb-12 shouji" style="height: 32px;">
			<input type="text" name="valitelephone" id="valitelephone"
				placeholder="填写已登记的安庆移动手机号">
			<button onclick="checkTel()">领话费</button>
		</div>
		<div class="col-mb-12" style="height: 259px;"></div>
		<div class="col-mb-12" style="height: 35px; margin-bottom: 9px;">
			<a href="javascript:void(0)" onclick="shareOne()" id="shareOne"
				class="main_a"></a>
		</div>
		<div class="col-mb-12" style="height: 35px; margin-bottom: 8px;">
			<a
				href="http://www.ygj.com.cn/wz.php?pageid=122608&openid=fromuserid&aw=wx.qq.com"
				class="main_a" id="duihuan"></a>
		</div>
		<div class="col-mb-12" style="height: 35px; margin-bottom: 16px;">
			<a href="javascript:void(0)" class="main_a" onclick="showCalls()"
				id="showCalls"></a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		setTimeout("show()", 2000);
	});

	function show() {
		$("#zzao").hide();
	}

	function shareOne() {
		$.ajax({
			url : "switchPage.do",
			type : "get",
			dataType : "json",
			success : function(data) {
				data = eval(data);
				if (data.success) {
					window.location.href = "share.do";
				} else {
					alert("亲!请先验证手机号码");
				}
			}
		});
	}

	function showCalls() {
		$.ajax({
			url : "switchPage.do",
			type : "get",
			dataType : "json",
			success : function(data) {
				data = eval(data);
				if (data.success) {
					window.location.href = "show.do";
				} else {
					alert("亲!请先验证手机号码");
				}
			}
		});
	}
	
	function checkTel() {
		var regBox = {
			regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/
		};
		var valitelephone = $("#valitelephone").val();
		var mflag = regBox.regMobile.test(valitelephone);

		if (valitelephone == "") {
			alert("请输入手机号码");
		} else if (!(mflag)) {
			alert("请填写正确的手机号码");
		} else {
			$.ajax({
				url : "isExitTel.do",
				type : "get",
				dataTyep : "json",
				data : {
					"telephone" : $("#valitelephone").val()
				},
				success : function(data) {
					data = eval(data);
					alert(data.msg);
				}
			});
		}
	}
</script>
</html>


