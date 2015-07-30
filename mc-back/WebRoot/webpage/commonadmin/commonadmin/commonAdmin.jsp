<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
 <t:base type="jquery,easyui,tools,autocomplete"></t:base>
  <title>二级管理员基本信息</title>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="commonAdminController.do?save">
			<input id="id" name="id" type="hidden" value="${commonAdminPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户名登入名:
						</label>
					</td>
					<td class="value">
					    <label class="Validform_label">${commonAdminPage.username}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
					    <label class="Validform_label">${commonAdminPage.name}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							电话号码:
						</label>
					</td>
					<td class="value">
					    <label class="Validform_label">${commonAdminPage.telephone}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属地区:
						</label>
					</td>
					<td class="value">
					   <label class="Validform_label">${commonAdminPage.district.districtName}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							角色:
						</label>
					</td>
					<td class="value">
					    <label class="Validform_label">${role}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
					    <label class="Validform_label">${commonAdminPage.content}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
					    <label class="Validform_label">${status}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="createTime" name="createTime" 
							     value="<fmt:formatDate value='${commonAdminPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" disabled="disabled">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							更新时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="updateTime" name="updateTime" 
							     value="<fmt:formatDate value='${commonAdminPage.updateTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" disabled="disabled">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>