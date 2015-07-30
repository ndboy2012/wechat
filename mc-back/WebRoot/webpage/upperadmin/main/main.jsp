<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>蒙城地税局触摸屏查询系统</title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<link rel="shortcut icon" href="images/favicon.ico">
<style type="text/css">
a {
	color: Black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}
</style>
<SCRIPT type="text/javascript">
	$(function() {
		$('#layout_east_calendar').calendar({
			fit : true,
			current : new Date(),
			border : false,
			onSelect : function(date) {
				$(this).calendar('moveTo', new Date());
			}
		});
		$(".layout-expand").click(function() {
			$('#layout_east_calendar').css("width", "auto");
			$('#layout_east_calendar').parent().css("width", "auto");
		});
	});
	var onlineInterval;
	function easyPanelCollapase() {
		window.clearTimeout(onlineInterval);
	}
	function easyPanelExpand() {
		onlineInterval = window.setInterval(function() {
			$('#layout_jeecg_onlineDatagrid').datagrid('load', {});
		}, 1000 * 20);
	}
</SCRIPT>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<!-- 顶部-->
	<div region="north" border="false"
		style="background:url(${webRoot}/files/images/top.jpg); height: 90px; padding: 1px; overflow: hidden;no-repeat;"
		class="panel-body panel-body-noheader panel-body-noborder layout-body">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<%-- <td align="left" style="vertical-align: text-bottom"><img
						src="${webRoot}/plug-in/login/images/slogan_ad.png"
						style="float:left;margin-top:8px;margin-left:30px;width:12%" /></td> --%>
					<td align="right" nowrap="">
						<table border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr style="height: 25px;" align="right">
									<td
										style="width:100%;background: url(${webRoot}/plug-in/login/images/top_bg.jpg)  right center;"
										colspan="3">
										<div>
											<div style="float: right; margin-left: 18px;">
												<div style="right: 0px; bottom: 0px;">
													<a style="color:#ffffff" href="javascript:void(0);"
														onclick="exit('loginController.do?logout','确定退出该系统吗 ?',1);"
														class="easyui-menubutton" menu="#layout_north_zxMenu"
														iconCls="icon-exit">注销</a>
												</div>
											</div>
											<div
												style="float: right; line-height: 25px; margin-left: 70px;">
												<span style="color: #386780">当前用户:</span> <span
													style="color: #FFFFFF">${upper_admin_session.username}</span>&nbsp;&nbsp;&nbsp;&nbsp;
												<span style="color: #386780">角色:</span> <span
													style="color: #FFFFFF">${upper_admin_session.role}</span>
											</div>
										</div>
									</td>
								</tr>
								<tr style="height: 40px;">
									<td colspan="2">
										<ul class="shortcut" id="shortcut-bd">
											<!-- 动态生成并赋值过来 -->
											<li><img class="imag1"
												src="${webRoot}/plug-in/login/images/default_info.png"
												style="display: none;"> <img class="imag2"
												src="${webRoot}/plug-in/login/images/default_up_info.png"
												style="display: line;">
												<!-- <div class="cur">信息管理</div> --></li>
											<li><img class="imag1"
												src="${webRoot}/plug-in/login/images/default_user.png"
												style="display: inline;"> <img class="imag2"
												src="${webRoot}/plug-in/login/images/default_up_user2.png"
												style="display: none;">
												<!-- <div>用户管理</div> --></li>
											<li><img class="imag1"
												src="${webRoot}/plug-in/login/images/default_item.png"
												style="display: inline;"> <img class="imag2"
												src="${webRoot}/plug-in/login/images/default_up_item2.png"
												style="display: none;">
												<!-- <div>栏目管理</div> --></li>
											<li><img class="imag1"
												src="${webRoot}/plug-in/login/images/default_message.png"
												style="display: inline;"> <img class="imag2"
												src="${webRoot}/plug-in/login/images/default_up_message.png"
												style="display: none;">
												<!-- <div>留言管理</div> --></li>
										</ul>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- 左侧-->
	<div region="west" split="false" href="upperMainController.do?left"
		title="导航菜单" style="width: 200px; padding: 0px;"></div>
	<!-- 中间-->
	<div id="mainPanle" region="center" style="overflow: hidden;">
		<div id="maintabs" class="easyui-tabs" fit="true" border="false">
			<div class="easyui-tab" title="首页" href="upperMainController.do?home"
				style="padding: 2px; overflow: hidden;"></div>
			<c:if test="${map=='1'}">
				<div class="easyui-tab" title="地图"
					style="padding: 1px; overflow: hidden;">
					<iframe name="myMap" id="myMap" scrolling="no" frameborder="0"
						src="mapController.do?map" style="width: 100%; height: 99.5%;"></iframe>
				</div>
			</c:if>
		</div>
	</div>
	<!-- 右侧 -->
	<div collapsed="true" region="east" iconCls="icon-reload" title="辅助工具"
		split="true" style="width: 190px;"
		data-options="onCollapse:function(){easyPanelCollapase()},onExpand:function(){easyPanelExpand()}">
		<div id="tabs" class="easyui-tabs" border="false"
			style="height: 240px">
			<div title="日历" style="padding: 0px; overflow: hidden; color: red;">
				<div id="layout_east_calendar"></div>
			</div>
		</div>
	</div>
	<div id="layout_north_zxMenu" style="width: 100px; display: none;">
		<div class="menu-sep"></div>
		<div onclick="exit('loginController.do?logout','确定退出该系统吗 ?',1);">退出系统</div>
	</div>
	<!-- 底部 -->
	<div region="south" border="false"
		style="height: 25px; overflow: hidden;">
		<div align="center" style="color: #CC99FF; padding-top: 2px">
			&copy; 合肥达达马蹄信息技术有限公司版权所有</div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
	</div>
</body>
<script type="text/javascript">
	$("#shortcut-bd li").click(
			function() {
				var img1 = $(this).find("img:eq(0)");
				if (img1.is(":visible")) {
					img1.hide().next().show().next().toggleClass("cur");
					$(this).siblings().find("img:eq(0)").show().next().hide()
							.next().removeClass("cur");
					$("#nav .lpanel").eq($(this).index()).slideDown().find(
							"ul li a:eq(0)").click().closest("div").siblings()
							.slideUp();
				}
			});
</script>
</html>