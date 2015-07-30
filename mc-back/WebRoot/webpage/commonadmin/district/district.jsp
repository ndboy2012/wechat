<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>地区基本信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="commonDistrictController.do?save">
			<input id="id" name="id" type="hidden" value="${districtPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							地区编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="districtCode" name="districtCode" 
							   value="${districtPage.districtCode}" datatype="*" disabled="disabled">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地区名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="districtName" name="districtName" 
							   value="${districtPage.districtName}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							email:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="email" name="email" ignore="ignore"
							   value="${districtPage.email}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							电话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="telephone" name="telephone" ignore="ignore"
							   value="${districtPage.telephone}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							详细地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="address" name="address" 
							   value="${districtPage.address}" datatype="*">
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
							     value="<fmt:formatDate value='${districtPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" disabled="disabled">
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
							     value="<fmt:formatDate value='${districtPage.updateTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" disabled="disabled">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
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
		$("#formobj").Validform(
				{
					tiptype : 4,
					btnSubmit : "#save",
					btnReset : "#btn_reset",
					ajaxPost : true,
					usePlugin : {

					},
					beforeSubmit : function(form) {
						return true;
					},
					callback : function(data) {
						tip(data.msg);
						if (data.success) {
							setTimeout(
									function() {
										location.href = "commonDistrictController.do?info";
									}, 1500);
						}
					}
				});
		$("#back").click(function(){
			location.href="commonDistrictController.do?info";
		});
		</script>
 </body>