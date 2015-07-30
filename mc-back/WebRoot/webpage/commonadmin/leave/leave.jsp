<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>留言信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="leaveController.do?save">
			<input id="id" name="id" type="hidden" value="${leavePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							性质:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="property" name="property" 
							   value="${leavePage.property}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发留言人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sources" name="sources" 
							   value="${leavePage.sources}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收留言人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="target" name="target" ignore="ignore"
							   value="${leavePage.target}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							content:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="content" name="content" 
							   value="${leavePage.content}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否处理:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isHandle" name="isHandle" ignore="ignore"
							   value="${leavePage.isHandle}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							createTime:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="createTime" name="createTime" 
							     value="<fmt:formatDate value='${leavePage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>