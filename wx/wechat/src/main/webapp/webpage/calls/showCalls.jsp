<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <meta forua="true" http-equiv="Cache-Control" content="no-cache" />
    <meta name="viewport"  content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0" />
    <meta name="viewport"  content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">   
    <link href="${webRoot}/css/calls/activity-style.css" rel="stylesheet" type="text/css">
    <script src="${webRoot}/js/calls/jquery-1.8.2.min.js" type="text/javascript"></script>	
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>查看获取多少话费</title>
    <style type="text/css">
    body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(${webRoot}/files/images/calls/bg.jpg);
   }
    </style>
  </head> 
  <body>
	<table width="320" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="100"><img src="${webRoot}/files/images/calls/showbg.jpg" width="320" height="100" /></td>
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
	<input id="telephone" type="hidden" value="${telephone}"> 
  </body>
				<script type="text/javascript">
					$(function() {
							$("#table_prize").html('<tr><td width="52" height="25" align="center" bgcolor="#FF9900" class="STYLE78">编号</td><td width="75" height="25" align="center" bgcolor="#FF9900" class="STYLE78">推荐号码</td><td width="130" height="25" align="center" bgcolor="#FF9900" class="STYLE78">推荐日期</td> </tr>');							
							$.ajax({									
								        url : "listAllrecommendInfo.do",//调用接口	
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
				</script>
</html>
