<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
<meta forua="true" http-equiv="Cache-Control" content="no-cache" />
<meta name="viewport"  content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0" />
<link href="../../css/signInggk/activity-style.css" rel="stylesheet" type="text/css">
<script src="../../js/signInggk/jquery.js" type="text/javascript"></script>
<script src="../../js/signInggk/jquery.pager.js" type="text/javascript"></script>
<style type="text/css">

<!--
a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
	color: #FF9900;
}

a:active {
	text-decoration: none;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(../../images/signInggk/activity-scratch-card-bg.jpg);
}

.STYLE64 {
	font-family: "微软雅黑";
	font-size: 14px;
	color: #990000;
	font-weight: bold;
}

.STYLE69 {
	font-family: "微软雅黑";
	font-size: 12px;
	color: #666666;
}

.STYLE70 {
	color: #FFFFFF;
	font-size: 15px;
}

.STYLE77 {
	font-family: "微软雅黑";
	font-size: 14px;
	color: #FF3300;
	font-weight: bold;
}

.STYLE78 {
	color: #FFFFFF;
	font-size: 14px;
	font-weight: bold;
}

.STYLE79 {
	color: #3366CC
}

.STYLE104 {
	font-family: "微软雅黑", "宋体", Arial, sans-serif;
	font-size: 12px;
	color: #666666;
}

.STYLE6 {
	font-family: "微软雅黑", "宋体", Arial, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #FFFFFF;
}
-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中奖纪录</title>
</head>
<body>
    <input id="txt_mobile" type="hidden" value="<%=request.getParameter("openid")%>"> 
	<table width="320" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="100"><img src="../../images/signInggk/top1.jpg" width="320" height="100" /></td>
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
									<td width="69" height="25" align="center" bgcolor="#FF9900"  class="STYLE78">时间</td>
									<td width="167" height="25" align="center" bgcolor="#FF9900" class="STYLE78">奖品内容</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td height="10" align="left" valign="middle"></td>
					</tr>
					<tr>
						<td height="60" align="center" valign="middle">
						   <div id="luckpage" class="pager"></div>
						</td>
					</tr>
				</table> 
				
				<script src="js/jquery.js" type="text/javascript">
				</script>			
				<script type="text/javascript">
					$(function() {
						//$.getScript("test.js");
						$.getScript("js/jquery.pager.js");
						function queryOrderInfo(pageclickednumber) {
							$("#table_prize").html('<tr><td width="52" height="25" align="center" bgcolor="#FF9900" class="STYLE78">时间</td><td width="130" height="25" align="center" bgcolor="#FF9900" class="STYLE78">奖品内容</td> </tr>');							
							$.ajax({									
								        url : "wechatSignController/listSinglePrizeInfo.do",//调用接口	
								        type : "get",
										dataType : "json",
									    data : {"open_id":$("#txt_mobile").val()},
										success : function(data) {
											var list = data.obj;
											if (list != null) {
												var PRIZETYPE, PROD_NAME;
												$.each(list,function(i,item) {
																	if (item.prizeNo == 2) {
																		PRIZETYPE = "二等奖";
																		PROD_NAME = "1G流量红包";
																	}
																	if (item.prizeNo == 3) {
																		PRIZETYPE = "三等奖";
																		PROD_NAME = "200M流量红包";
																	}

																	if (item.prizeNo == 4) {
																		PRIZETYPE = "四等奖";
																		PROD_NAME = "免费电子图书&nbsp;&nbsp; <a href='javascript:getbook()'>领取</a>";
																	}

																	if (item.prizeNo == 5) {
																		PRIZETYPE = "五等奖";
																		PROD_NAME = "免费精美铃音";
																	}
																	
																	if (item.prizeNo == 1) {
																		PRIZETYPE = "一等奖";
																		PROD_NAME = "免费电影票2张";	
																	}

																	if (item.prizeNo == 6) {
																		PRIZETYPE = "六等奖";																		
																		PROD_NAME = "安徽手机报免费体验60日";	
																	}

																	if (item.prizeNo == -4) {
																		PRIZETYPE = "未中奖";
																		PROD_NAME = "很遗憾未中奖";
																	}
																	$("#table_prize").append('<tr><td height="24" align="center" valign="middle" bgcolor="#FFFFFF">'+ item.winDate.toString().substring(5,7)+ '月'+ item.winDate.toString().substring(8,10)+ '日'
																							+'</td><td height="24" align="center" valign="middle" bgcolor="#FFFFFF">'+ PROD_NAME+ '</td></tr>');
																});

											} else {
												$("#table_prize").append('<tr><td height="24" colspan="3" align="center" valign="middle" bgcolor="#FFFFFF">亲！您还没有登记暂时无法进行操作</tr>');
											}
											$("#luckpage").pager(
															{
																pagenumber : pageclickednumber,
																pagecount : 0,
																buttonClickCallback : queryOrderInfo
															});
										},
										error : function() {
											$("#table_prize").append('<tr><td height="24" colspan="3" align="center" valign="middle" bgcolor="#FFFFFF">用户登陆已过期，请重新发送指令进行抽奖哦</td></tr>');
										},
										timeout : 4000
									});
						}
						queryOrderInfo(1);
					});
				</script>
			</td>
		</tr>
		<tr>
		<td height="50"></td>
		</tr>
	</table>
	<script type="text/javascript">
	    function getbook() {
	    	var booknum = random(1,14);
	    	switch(booknum) {
	    	case 1:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=392986464&cm=S5510028";
	    		break;
	    	case 2:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=393365243&cm=S5510028";
	    		break;
	    	case 3:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=379505400&cm=S5510028";
	    		break;
	    	case 4:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=350066678&cm=S5510028";
	    		break;
	    	case 5:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=381307191&cm=S5510028";
	    		break;
	    	case 6:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=392986464&cm=S5510028";
	    		break;
	    	case 7:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=383705960&cm=S5510028";
	    		break;
	    	case 8:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=387831824&cm=S5510028";
	    		break;
	    	case 9:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=387365246&cm=S5510028";
	    		break;
	    	case 10:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=387969846&cm=S5510028";
	    		break;
	    	case 11:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=392986464&cm=S5510028";
	    		break;
	    	case 12:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=395547280&cm=S5510028";
	    		break;
	    	case 13:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=355852167&cm=S5510028";
	    		break;
	    	case 14:
	    		window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=354088544&cm=S5510028";
	    		break;
	    		default:
	    			window.location.href="http://wap.cmread.com/r/viewbook.a?type=1&bid=354088544&cm=S5510028";
	    		break;
	    	}
	    }
	    
	    function random(min,max){
	        return Math.floor(min+Math.random()*(max-min));
	    }
	</script>
</body>
</html>

