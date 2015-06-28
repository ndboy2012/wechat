<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../mytags.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理员登入</title>
<style type="text/css">
.align-center {
	position: fixed;
	left: 35%;
	top: 20%;
	margin-left: width/2;
	margin-top: height/2;
}
</style>
<body>
	<div class="align-center">
		<div class="easyui-panel" title="管理员登入" style="width:400px;padding:30px 70px 20px 70px">
			<form id="formobj" method="post">
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="username" name="username" style="width:100%;height:40px;padding:12px"
						data-options="required:true,prompt:'输入用户名',iconCls:'icon-man',iconWidth:38">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" id="password" name="password"
						type="password" style="width:100%;height:40px;padding:12px"
						data-options="required:true,prompt:'输入密码',iconCls:'icon-lock',iconWidth:38">
				</div>
				<div style="margin-bottom:20px">
					<input type="checkbox" checked="checked"> <span>记住账号</span>
				</div>
			</form>
			<div>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submit()" data-options="iconCls:'icon-ok'"
					style="padding:5px 0px;width:100%;"> 
					<span style="font-size:14px;">登入</span>
				</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
    function submit() {
    	  var username=$("#username").val();
    	  var password=$("#password").val();
    	  $.ajax({
    	    	url:'validateLogin.do',
    	    	type:'get',
    	    	dataType:'json',
    	    	data:{"username":username,"password":password},
    	    	success : function(date) {
    	    		date = eval(date);
    	    		if(date.msg=="true") {
    	    			location.href="loginSuccess.do";
    	    		} else {
    	    			tip("用户名或者密码错误");
    	    		}
    	    	},
    	    	error : function(date) {
     	             tip("shurucuowu");
     	    	 }
    	    });
    }
	/**
	 * 提示信息
	 */
	function tip(msg) {
		$.messager.show({
			title : '提示信息',
			msg : msg,
			timeout : 1000 * 6
		});
	}
	/**
	 * 提示信息像alert一样
	 */
	function alertTip(msg, title) {
		title = title ? title : "提示信息";
		$.dialog({
			title : title,
			icon : 'tips.gif',
			content : msg
		});
	}
</script>
</html>
