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
				<a href="javascript:void(0);" onclick="addTab('管理员基本信息','commonAdminController.do?info&isIframe','icon-redo')"><i></i>管理员基本信息</a>
			</li>
			<li>
				<a href="javascript:void(0);" onclick="addTab('密码修改','commonAdminController.do?changePwd&isIframe','icon-redo')"><i></i>密码修改</a>
			</li>
		</ul>
	</div>
	<div class="lpanel" style="display:none">
		<ul class="tree-submenu">
		<li>
				<a href="javascript:void(0);" onclick="addTab('地区基本信息','commonDistrictController.do?info&isIframe','icon-redo')"><i></i>地区基本信息</a>
			</li>
			<li>
				<a href="javascript:void(0);" onclick="addTab('信息修改','commonDistrictController.do?addorupdate&isIframe','icon-redo')"><i></i>信息修改</a>
			</li>
		</ul>
	</div>
	<div class="lpanel" style="display:none">
		<ul class="tree-submenu">
			<li>
				<a href="javascript:void(0);" onclick="addTab('类目总览','commonItemController.do?item&isIframe','icon-redo')"><i></i>类目总览</a>
			</li>
			<li>
				<a href="javascript:void(0);" onclick="addTab('文章添加','commonItemController.do?addArticle&isIframe','icon-redo')"><i></i>文章添加</a>
			</li>
		</ul>
	</div>
	<div class="lpanel" style="display:none">
	    <ul class="tree-submenu">
	        <li>
	            <a href="javascript:void(0);" onclick="addTab('最新通告','commonLeaveController.do?leave&isIframe','icon-redo')"><i></i>最新通告</a>
	        </li>
	        <li>
	            <a href="javascript:void(0);" onclick="addTab('给超级管理员留言','commonLeaveController.do?myleave&isIframe','icon-redo')"><i></i>给超级管理员留言</a>
	        </li>
	    </ul>
	</div>
	<div class="lpanel" style="display:none">
		<ul class="tree-submenu">
			<li>
				<a href="javascript:void(0);" onclick="addTab('用户版本', 'clientVersionController.do?clientCustomerVersion&isIframe', 'icon-redo')"><i></i>通知公告</a>
			</li>
			<li>
				<a href="javascript:void(0);" onclick="addTab('商户版本', 'clientVersionController.do?clientMerchantVersion&isIframe', 'icon-redo')"><i></i>商户版本</a>
			</li>
		</ul>
	</div>
	<div class="lpanel" style="display:none">
		<ul class="tree-submenu">
			<li>
				<a href="javascript:void(0);" onclick="addTab('FAQ管理', 'faqController.do?faq&isIframe', 'icon-redo')"><i></i>FAQ管理</a>
			</li>
		</ul>
	</div>
</div>


