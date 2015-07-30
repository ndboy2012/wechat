<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>二级管理员基本信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="commonController.do?save">
			<input id="id" name="id" type="hidden" value="${commonAdminPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户名登入名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="username" name="username" 
							   value="${commonAdminPage.username}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属地区:
						</label>
					</td>
					<td class="value">
						<select name="district.districtCode">
						<c:forEach items="${districtList}" var="district">
							<option value="${district.districtCode}"
								<c:if test="${district.districtCode==commonAdminPage.district.districtCode}">selected</c:if>>${district.districtName}</option>
						</c:forEach>
				</select>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
				  <td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
						<textarea class="inputxt" id="content" name="content" ignore="ignore"
							   value="">${commonAdminPage.content}</textarea>
						<span class="Validform_checktip"></span>
					</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${commonAdminPage.name}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							电话号码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="telephone" name="telephone" 
							   value="${commonAdminPage.telephone}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
						<select name="status">
						<c:forEach items="${statusList}" var="status">
							<option value="${status.value}"
								<c:if test="${status.value==commonAdminPage.status}">selected</c:if>>${status.name}</option>
						</c:forEach>
				</select>
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
							     value="<fmt:formatDate value='${commonAdminPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*">
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
							     value="<fmt:formatDate value='${commonAdminPage.updateTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>