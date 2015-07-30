<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>类目信息</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <link rel="stylesheet" href="${webRoot}/plug-in/Validform/css/style.css" type="text/css" />
	<link rel="stylesheet" href="${webRoot}/plug-in/Validform/css/tablefrom.css" type="text/css" />
	<script type="text/javascript" src="${webRoot}/plug-in/Validform/js/Validform_v5.3.1_min.js"></script>
	<script type="text/javascript" src="${webRoot}/plug-in/Validform/js/Validform_Datatype.js"></script>
	<script type="text/javascript" src="${webRoot}/plug-in/Validform/js/datatype.js"></script>
	<script type="text/javascript" src="${webRoot}/plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/kindeditor/css/layout.css">
    <link rel="stylesheet" href="${webRoot}/plug-in/kindeditor/themes/default/default.css" />
    <link rel="stylesheet" href="${webRoot}/plug-in/kindeditor/plugins/code/prettify.css" />
    <script charset="utf-8" src="${webRoot}/plug-in/kindeditor/kindeditor.js"/>
    <title>My JSP 'article2.jsp' starting page</title>
    <script type="text/javascript">
    </script>
  </head>
  
  <body>
    <t:formvalid formid="formobj" dialog="true"
		layout="table" action="upperItemController.do?articleSaveSec">
		<input id="id" name="id" type="hidden" value="${itemPage.id }">
		<input type="hidden" id="btn_sub" class="btn_sub"> 
		<table style="width: 100%;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="center" width="10%"><label class="Validform_label">
						添加位置: </label></td>
				<td class="value"><select id="first_page" style="width:118px;">
						<c:forEach items="${firstPage}" var="t">
							<option value="${t.id}"
								<c:if test="${first==t.id }">selected</c:if>>${t.title}
						</c:forEach>
				</select> 
				<select id="second_page" style="width:119px;">
				       <option value="000100">所在目录</option>
				       <c:forEach items="${secondPage}" var="t">
							<option value="${t.id}"
								<c:if test="${second==t.id }">selected</c:if>>${t.title}
						</c:forEach>
				</select> 
				<span class="Validform_checktip"> </span>
				<input type="hidden" name="parentId" id="parentId">
				</td>
			</tr>

			<tr>
				<td align="center"><label class="Validform_label"> 标题:
				</label></td>
				<td class="value"><input class="inputxt" id="title"
					name="title" value="${itemPage.title}" datatype="*" style="width:250px"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>	
				<td align="center"><label class="Validform_label">
						内容:</label></td>
			   <td class="value" width="90%">
				<textarea id="content" name="content"
						 style="height:250px;width:100%">${itemPage.article.content}
				</textarea>
			 <input type="hidden" name="contentTxt" id="contentTxt">
			 <input type="hidden" name="upfile" id="upfile">
			  <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label">预览:
				</label></td>
				<td class="value">
				<a href="javascript:void(0)" onclick="preview()"><img alt="" src="${webRoot}/files/images/preview.png" style="width:30px;"></a><span
					class="Validform_checktip"></span></td>
			</tr>

			<tr>
				<td align="center"><label class="Validform_label"> 排序:
				</label></td>
				<td class="value"><input class="inputxt" id="sort"  value="1" name="sort"
					value="${itemPage.sort}" datatype="n"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> </label></td>
				<td class="value"><a class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" id="save">保存</a>&nbsp;&nbsp;<a
					class="easyui-linkbutton" data-options="iconCls:'icon-back'"
					id="back">返回</a></td>
			</tr>
		</table>
		<input type="hidden" id="districtCode" name="districtCode" value="${districtCode}">
	</t:formvalid>
  </body>
  <script type="text/javascript">
  var editor;
  KindEditor.ready(function(K) {
	    editor = K.create('textarea[name="content"]', {
		cssPath : '${webRoot}/plug-in/kindeditor/plugins/code/prettify.css',
		uploadJson : '${webRoot}/plug-in/kindeditor/jsp/upload_json.jsp',
		fileManagerJson : '${webRoot}/plug-in/kindeditor/jsp/file_manager_json.jsp',
		allowFileManager : true,
		allowImageUpload : true, 
		autoHeightMode : true,
		afterCreate : function() {
		}
	});
});
    
	$("#formobj").Validform(
			{
				tiptype : 4,
				btnSubmit : "#btn_sub",
				btnReset : "#btn_reset",
				ajaxPost : true,
				usePlugin : {

				},
				beforeSubmit : function(form) {
					$("#content").val(editor.html());
					$("#contentTxt").val(editor.text());
					if($("#second_page").val()=="000100") {
						$("#parentId").val($("#first_page").val());
					} else {
						$("#parentId").val($("#second_page").val());
					}
					
				},
				callback : function(data) {
					tip(data.msg);
					if (data.success) {
						setTimeout(
								function() {
									var districtCode = $("#districtCode").val();
									location.href = "upperItemController.do?itemSecond&districtCode="+districtCode;
								}, 1500);
					}
				}
			});
	
	$("#first_page").change(
			function(o) {
				$("#second_page").empty();
				var cStr = "<option value='000100'>所在目录</option>";
				if ($(this).val() == "0") {
					$("#second_page").html(cStr);
				} else {
					$.ajax({
						url : "upperItemController.do?reqItems&parentId="
								+ $(this).val(),
						dataType : "json",
						success : function(data) {
							var result = data.obj;
							for (var i = 0; i < result.length; i++) {
								cStr += "<option value='"+result[i].id+"'>"
										+ result[i].title + "</option>";
							}
							$("#second_page").html(cStr);
						}
					});
				}
			});
	
	$("#back").click(function(){
		var districtCode = $("#districtCode").val();
		location.href = "upperItemController.do?itemSecond&districtCode="+districtCode;
	});
	
	 
    $("#save").click(function(){
    	$.dialog.confirm('你确定修改该内容吗?', function(r) {
    	$("#btn_sub").click();
    	});
    });
	
	function preview() {
		/* alert("hello"); */
		$.ajax({
			url:"upperItemController.do?previewEditing",
		    dataType:"json",
		    type:"post",
		    data:{
		    	"content":editor.html(),
		    },
			success : function(data) {
				/* alert("hello2"); */
				window.open("upperItemController.do?previewEditing2", "文章预览", "toolbar=no,location=no", false);
			}
		});
	}
</script>
</html>
  
</html>
