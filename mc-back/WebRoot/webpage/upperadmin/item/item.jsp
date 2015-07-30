<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>类目基本信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="upperItemController.do?save">
			<input id="id" name="id" type="hidden" value="${itemPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否公开编辑权限:
						</label>
					</td>
					<td class="value">
					<input type="radio" name="isPublic" id="isPublic" value="1" checked/>是
					<input type="radio" name="isPublic" id="isPublic" value="0" />否
					<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							添加位置:
						</label>
					</td>
					<td class="value">
				 <select name="parentId">
						<option value="0">首页</option>
						<c:forEach items="${firstItem}" var="item">
							<option value="${item.id}" <c:if test="${item.id==itemPage.parentId}">selected</c:if>>${item.title}</option>
						</c:forEach>
				</select>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<c:if test="${formType=='add' }">
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title" 
							   value="${itemPage.title}" datatype="*">(如需添加多个使用英文","号隔开)
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				</c:if>
				 <c:if test="${formType=='update' }">
				 <tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title" 
							   value="${itemPage.title}" datatype="*">
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
						<c:forEach items="${itemStatus}" var="item">
							<option value="${item.value}" <c:if test="${item.value==itemPage.status}">selected</c:if>>${item.name}</option>
						</c:forEach>
				</select>
						<span class="Validform_checktip"></span>
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
							     value="<fmt:formatDate value='${itemPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" disabled="disabled" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							修改时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="updateTime" name="updateTime" 
							     value="<fmt:formatDate value='${itemPage.updateTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" disabled="disabled" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				</c:if>
				<tr>
				<td align="right"><label class="Validform_label"> </label></td>
				<td class="value"><a class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" id="save">保存</a>&nbsp;&nbsp;<a
					class="easyui-linkbutton" data-options="iconCls:'icon-back'"
					id="back">返回</a></td>
			</tr>
			</table>
		</t:formvalid>
		<script type="text/javascript">
$("#formobj").Validform({
	tiptype : 4,
	btnSubmit : "#btn_sub",
	btnReset : "#btn_reset",
	ajaxPost : true,
	beforeSubmit : function(curform) {
		$("#isPublic").val($("input[name='isPublic']:checked").val());
		return true;
	},
	usePlugin : {
		
	},
	callback : function(data) {
		tip(data.msg);
		if (data.success) {
			setTimeout(
					function() {
						location.href = "upperItemController.do?item";
					}, 1500);
		}
	}
});
$("#save").click(function() {
    $("#btn_sub").click();
});

$("#back").click(function(){
	window.location.href="upperItemController.do?item";
});
</script>
 </body>