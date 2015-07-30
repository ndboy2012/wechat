<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>留言信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="upperLeaveController.do?save">
			<input id="id" name="id" type="hidden" value="${leavePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			 <c:if test="${msgType=='district' }">
				<tr>
					<td align="right">
						<label class="Validform_label">
							收留言地区:
						</label>
					</td>
					<td class="value">
						<select name="target">
						<c:forEach items="${districtList}" var="district">
							<option value="${district.districtCode}"
								<c:if test="${district.districtCode==leavePage.target}">selected</c:if>>${district.districtName}</option>
						</c:forEach>
				</select>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				</c:if>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容:
						</label>
					</td>
					<td class="value">
						<textarea class="inputxt" id="content" name="content" 
							  datatype="*" width="100%" style="width:80%;height:150px">${leavePage.content}</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<%-- <tr>
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
				</tr> --%>
				<tr>
					<td align="right">
						<label class="Validform_label">
							时间:
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