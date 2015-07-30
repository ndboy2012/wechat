<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>登陆超时</title>
<t:base type="jquery,easyui"></t:base>
</head>
<body>
<script type="text/javascript">
$.messager.alert('确定','登陆超时，返回登陆页','info',function(r){    
	window.top.location.href="${redirectUrl}";  
});
$(".panel-tool-close").click(function(){
	window.top.location.href="${redirectUrl}";
});
</script>
</body>
</html>