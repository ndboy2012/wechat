<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>修改支付密码</title>
<t:base type="jquery,easyui,tools,autocomplete"></t:base>
<link rel="stylesheet" href="${webRoot}/plug-in/Validform/css/style.css"
	type="text/css" />
<link rel="stylesheet"
	href="${webRoot}/plug-in/Validform/css/tablefrom.css" type="text/css" />
<script type="text/javascript"
	src="${webRoot}/plug-in/Validform/js/Validform_v5.3.1_min.js">
	
</script>
<script type="text/javascript"
	src="${webRoot}/plug-in/Validform/js/Validform_Datatype.js">
	
</script>
<script type="text/javascript"
	src="${webRoot}/plug-in/Validform/js/datatype.js">
	
</script>
<SCRIPT type="text/javascript"
	src="${webRoot}/plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js">
	
</SCRIPT>
<script type="text/javascript">
	$(function() {
		$("#formobj").Validform(
				{
					tiptype : 4,
					btnSubmit : "#btn_sub",
					btnReset : "#btn_reset",
					ajaxPost : true,
					usePlugin : {
						passwordstrength : {
							minLen : 6,
							maxLen : 18,
							trigger : function(obj, error) {
								if (error) {
									obj.parent().next().find(
											".Validform_checktip").show();
									obj.find(".passwordStrength").hide();
								} else {
									$(".passwordStrength").show();
									obj.parent().next().find(
											".Validform_checktip").hide();
								}
							}
						}
					},
					callback : function(data) {
						tip(data.msg);
					}
				});
	});
</script>

</head>
<body style="overflow-y: hidden" scroll="no">

	<form id="formobj" action="upperAdminController.do?changePassword"
		layout="table">
		<input id="id" name="id" type="hidden" value="${upper_admin_session.id}">
		<table style="width:100%" cellpadding="0" cellspacing="1"
			class="formtable">
			<tbody>
				<tr>
					<td align="right" width="15%"><span class="filedzt">用户名:</span></td>
					<td class="value" width="85%">
					<span id="username" name="username" ignore="ignore" value="${upper_admin_session.username}"
						readonly> <font size="16"><a>${upper_admin_session.username}</a></font></span>
					</td>
				</tr>
				<tr>
					<td align="right"><span class="filedzt">旧密码:</span></td>
					<td class="value"><input type="password" name="password"
						class="inputxt" plugin="passwordStrength" datatype="*6-18"
						errormsg="密码至少6个字符,最多18个字符！"><span
						class="Validform_checktip"> 密码至少6个字符,最多18个字符！ </span> <span
						class="passwordStrength" style="display: none;"> <b>密码强度：</b>
							<span>弱</span><span>中</span><span class="last">强</span></span></td>
				</tr>
				<tr>
					<td align="right"><span class="filedzt">新密码:</span></td>
					<td class="value"><input type="password" value=""
						name="newPassword" class="inputxt" plugin="passwordStrength"
						datatype="*6-18" errormsg="密码至少6个字符,最多18个字符！" /> <span
						class="Validform_checktip"> 密码至少6个字符,最多18个字符！ </span> <span
						class="passwordStrength" style="display: none;"> <b>密码强度：</b>
							<span>弱</span><span>中</span><span class="last">强</span>
					</span></td>
				</tr>
				<tr>
					<td align="right"><span class="filedzt">重复密码:</span></td>
					<td class="value"><input type="password"
						name="newpayPassword1" class="inputxt" recheck="newPassword"
						datatype="*6-18" errormsg="两次输入的密码不一致！"> <span
						class="Validform_checktip"></span></td>
				</tr>
				<tr>
					<td></td>
					<td align="left">
						<div style="margin:10px;">
							<a href="javascript:void(0);" class="easyui-linkbutton"
								id="btn_sub" iconCls="icon-save">保存</a> <a
								href="javascript:void(0);" class="easyui-linkbutton"
								id="btn_reset" iconCls="icon-reload">重置</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>