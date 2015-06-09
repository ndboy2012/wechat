<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../context/mytags.jsp"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" />
<title>登记手机</title>
<link type="text/css" rel="stylesheet" href="../../css/register/style.css" />
<script type="text/javascript" src="../../js/register/jquery-1.8.3.js"></script>
<script src="../../js/register/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="../../js/register/jquery.alerts.js" type="text/javascript"></script>
<link href="../../css/register/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="../../js/register/js.js"></script>
<script>
var wait=60;
var canClick = true;
   
function show(){
  if(canClick) {  
  var regBox = { regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/}; 
  
  var telValue=$(".wapper .tel input").val();
  
  var mflag = regBox.regMobile.test(telValue);
  
  if($(".wapper .tel input").val()==""){
		telValue=$(".wapper.tel input").val();
		$(".wapper .tel input").focus();
		$("#tips").text("手机号码不能为空，请重新填写。");
	}else if(!(mflag)){
		telValue=$(".wapper.tel input").val();
		$(".wapper .tel input").focus();
		$("#tips").text("手机号码有误，请重新填写。");
	}else{
		var telephone=$("#telephone").val();
		var openid=$("#openid").val();
		
		$.ajax({
			url:"registerController/verifyCode.do",
			type:"get",
			dataType:'json',
			data:{"telephone":telephone,"openid":openid},
			success:function(data) {
				 data=eval(data);
				 $("#tips").text(data.msg);
			},
			error:function(e){
				alert("请求失败");
			}
		});
		time();   //发送成功就调用函数，让show()不能点击
	 }
	}
 }
 
function time(){ 
     if (wait == 0) { 
         document.getElementById("getpass").innerHTML="获取验证码";
         wait = 60; 
         canClick = true;
      } else { 
           document.getElementById("getpass").innerHTML="("+wait+"秒)再试";
           wait--; 
           canClick = false;
          setTimeout(function() { 
                time(); 
          },1000); 
       } 
}

	 
function bind() {
     var regBox = {regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
		};	
	var telValue=$(".wapper .tel input").val();
		var mflag = regBox.regMobile.test(telValue);
		if($(".wapper .tel input").val()==""){
			telValue=$(".wapper .tel input").val();
			$(".wapper .tel input").focus();
			$("#tips").text("手机号码不能为空，请重新填写。");
		}	
		else if(!(mflag)){
			telValue=$(".wapper .tel input").val();
			$(".wapper .tel input").focus();
			$("#tips").text("手机号码有误，请重新填写。");
		}
		else if($(".wapper .checkcode input").val()==""){
			telValue=$(".wapper .checkcode input").val();
			$(".wapper .checkcode input").focus();
			$("#tips").text("验证码不能为空，请重新填写。");
		}
		else{ 
		   var telephone=$("#telephone").val();
		   var openId=$("#openid").val();
		   var verify=$("#verify").val();
				$.ajax({
					url:"registerController/bindInfo.do",
					type:"post",
					dataType:'json',
					data:{"telephone":telephone,"openid":openId,"verify":verify},
					success:function(data) {
						data=eval(data);
						if(data.msg=="success") { 
							 if(confirm("恭喜您登记成功！确定是否跳转到双旦领神秘红包页面")){
								 var _success_href = $("#success_href").val()+$("#openid").val();
								 alert(_success_href);
								 window.location.href=_success_href;
							 }
						} else {
							$("#tips").text(data.msg);
						}
					}
				});
		}
}	 	 
</script>
</head>
<body>
	<!-- 内容开始 -->
	<div class="bodys" style="margin-top:15%;">
		<div class="wapper">
			<form id="LoginForm">
				<div class="sr tel">
					<label>手机号：</label> <input type="text" class="txtsr left"
						placeholder="请输入安庆移动手机号码…" maxlength="90" name="telephone"
						id="telephone" />
					<div class="clear"></div>
				</div>
				
				<div class="sr tel" hidden="hidden">
					<label>openid：</label> <input type="text" class="txtsr left" 
						placeholder="请输入安庆移动手机号码…" maxlength="90" name="openid"
						id="openid" value="<%=request.getParameter("openid")%>"/>
					<div class="clear"></div>
				</div>
				
				<div class="sr checkcode">
					<label>验证码：</label> <input type="text" class="txtsr left"
						placeholder="请输入验证码…" maxlength="90" id="verify" name="verify" /> <a
						class="fs right" onClick="show()" id="getpass">获取验证码</a>
					<div class="clear"></div>
				</div>
				<div id="tips"
					style="color:#f00; width:90%; margin:0 auto; height:2em;"></div>
				<input type="hidden" name="url" value="Portal/Signin/award" /> <input
					type="hidden" name="ajax" value="0" /> 
				<a class="next" onClick="bind()">确 认 登 记</a>
			</form>
		</div>
		<input type="hidden" id="success_href" value="${webRoot}/merryController/envelop.do?openId=" >
	</div>
	<!-- 内容结束 -->
	<div class="footer2 fix">
		<p>&copy;安庆移动 版权所有</p>
	</div>
</body>
</html>