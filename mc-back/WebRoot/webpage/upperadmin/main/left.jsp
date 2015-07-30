<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet"
	href="<c:out value="${webRoot}"></c:out>/plug-in/accordion/css/icons.css" type="text/css"></link>
<link rel="stylesheet"
	href="<c:out value="${webRoot}"></c:out>/plug-in/accordion/css/accordion.css"
	type="text/css"></link>
<script type="text/javascript"
	src="<c:out value="${webRoot}"></c:out>/plug-in/accordion/js/leftmenu.js">
</script>
<div id="nav">
   <div class="lpanel">
		<ul class="tree-submenu">
			<li style="list-style:none">
				<a href="javascript:void(0);" onclick="addTab('管理员基本信息','upperAdminController.do?info&isIframe','icon-redo')"><i></i>管理员基本信息</a>
			</li>
			<li>
				<a href="javascript:void(0);" onclick="addTab('密码修改','upperAdminController.do?changePwd&isIframe','icon-redo')"><i></i>密码修改</a>
			</li>
		</ul>
	</div>
	<div class="lpanel" style="display:none">
		<ul class="tree-submenu">
		   <li>
				<a href="javascript:void(0);" onclick="addTab('地区信息','upperDistrictController.do?district&isIframe','icon-redo')"><i></i>地区信息</a>
			</li>
			<li>
				<a href="javascript:void(0);" onclick="addTab('二级管理员信息','commonController.do?commonAdmin&isIframe','icon-redo')"><i></i>二级管理员信息</a>
			</li>
		</ul>
	</div>
	<div class="lpanel" style="display:none">
		<ul class="tree-submenu">
			<li>
				<a href="javascript:void(0);" onclick="addTab('栏目列表','upperItemController.do?item&isIframe','icon-redo')"><i></i>栏目列表</a>
			</li>
			<li>
				<a href="javascript:void(0);" onclick="addTab('各单位栏目列表','upperItemController.do?itemSecond&isIframe','icon-redo')"><i></i>各单位栏目列表</a>
			</li>
		</ul>
	</div>
	<div class="lpanel" style="display:none">
		<ul class="tree-submenu">
	        <li>
	            <a href="javascript:void(0);" onclick="addTab('接收留言','upperLeaveController.do?leave&isIframe','icon-redo')"><i></i>接收留言</a>
	        </li>
	        <li>
	            <a href="javascript:void(0);" onclick="addTab('发送通告','upperLeaveController.do?myleave&isIframe','icon-redo')"><i></i>发送通告</a>
	        </li>
	    </ul>
	</div>
</div>


