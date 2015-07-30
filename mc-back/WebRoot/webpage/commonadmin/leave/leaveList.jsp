<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<script type="text/javascript">
	$(function() {
		$('#leaveList')
				.datagrid(
						{
							idField : 'id',
							title : '通告信息',
							url : 'commonLeaveController.do?datagrid&field=id,target,content,property,isHandle,createTime,',
							fit : true,
							loadMsg : '数据加载中...',
							pageSize : 10,
							pagination : true,
							pageList : [ 10, 20, 30 ],
							sortOrder : 'asc',
							rownumbers : true,
							singleSelect : true,
							fitColumns : true,
							showFooter : true,
							frozenColumns : [ [] ],
							columns : [ [
									{
										field : 'id',
										title : '编号',
										hidden : true,
										sortable : true
									},
									{
										field : 'content',
										title : '内容',
										formatter : function(value,rec,index) {
											return "<div style='width:280px' class='overflow'>"+value+"</div>";
										} 
									},
									{
										field : 'property',
										title : '通告性质',
										formatter : function(value,
												rec, index) {
											var sa = "${propertyReplace}"
													.split(",");
											var s = "";
											for (var i = 0, len = sa.length; i < len; i++) {
												var ia = sa[i]
														.split("_");
												if (value == ia[1]) {
													s = ia[0];
												};
											}
											return s;
										}
									},
									{
										field : 'createTime',
										title : '通告时间',
										sortable : true,
										formatter : function(value, rec, index) {
											return new Date().format(
													'yyyy-MM-dd hh:mm:ss',
													value);
										}
									},
									{
										field : 'opt',
										title : '操作',
										width : 100,
										formatter : function(value, rec, index) {
											if (!rec.id) {
												return '';
											}
											var href = '';
											if(rec.isHandle=="0"&&rec.property=="1") {
												href += "[<a href='#' onclick=mark('"
													+ rec.id
													+ "')>";
											    href += "标记处理</a>]";
											} else if(rec.isHandle=="1"&&rec.property=="1") {
												href +="[已经处理]";
											}
											return href;
										}
									}
									] ],
							onLoadSuccess : function(data) {
								$("#leaveList").datagrid("clearSelections");
							},
							onClickRow : function(rowIndex, rowData) {
								rowid = rowData.id;
								gridname = 'leaveList';
							}
						});
		$('#leaveList').datagrid('getPager').pagination({
			beforePageText : '',
			afterPageText : '/{pages}',
			displayMsg : '{from}-{to}共{total}条',
			showPageList : true,
			showRefresh : true
		});
		$('#leaveList').datagrid('getPager').pagination({
			onBeforeRefresh : function(pageNumber, pageSize) {
				$(this).pagination('loading');
				$(this).pagination('loaded');
			}
		});
	});
	function reloadTable() {
		try {
			$('#' + gridname).datagrid('reload');
			$('#' + gridname).treegrid('reload');
		} catch (ex) {
		}
	}
	function reloadleaveList() {
		$('#leaveList').datagrid('reload');
	}
	function getleaveListSelected(field) {
		return getSelected(field);
	}
	function getSelected(field) {
		var row = $('#' + gridname).datagrid('getSelected');
		if (row != null) {
			value = row[field];
		} else {
			value = '';
		}
		return value;
	}
	function getleaveListSelections(field) {
		var ids = [];
		var rows = $('#leaveList').datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i][field]);
		}
		ids.join(',');
		return ids
	};
	function getSelectRows() {
		return $('#leaveList').datagrid('getChecked');
	}
	function leaveListsearch() {
		var queryParams = $('#leaveList').datagrid('options').queryParams;
		$('#leaveListtb').find('*').each(function() {
			queryParams[$(this).attr('name')] = $(this).val();
		});
		$('#leaveList')
				.datagrid(
						{
							url : 'UpperLeaveController.do?datagrid&field=id,property,sources,target,content,isHandle,createTime,',
							pageNumber : 1
						});
	}
	function dosearch(params) {
		var jsonparams = $.parseJSON(params);
		$('#leaveList')
				.datagrid(
						{
							url : 'UpperLeaveController.do?datagrid&field=id,property,sources,target,content,isHandle,createTime,',
							queryParams : jsonparams
						});
	}
	function leaveListsearchbox(value, name) {
		var queryParams = $('#leaveList').datagrid('options').queryParams;
		queryParams[name] = value;
		queryParams.searchfield = name;
		$('#leaveList').datagrid('reload');
	}
	$('#leaveListsearchbox').searchbox({
		searcher : function(value, name) {
			leaveListsearchbox(value, name);
		},
		menu : '#leaveListmm',
		prompt : '请输入查询关键字'
	});
	function searchReset(name) {
		$("#" + name + "tb").find(":input").val("");
		leaveListsearch();
	}
</script>
<table width="100%" id="leaveList" toolbar="#leaveListtb"></table>
<div id="leaveListtb" style="padding:3px; height: auto">
	<div style="height:30px;" class="datagrid-toolbar">
		<span style="float:left;">
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search"
			onclick="detail('查看','upperLeaveController.do?addorupdate','leaveList',null,null)">查看</a></span>
	</div>
	<script type="text/javascript">
	  function mark(id) {
		  $.ajax({
			  url:"commonLeaveController.do?mark",
		      dataType:"json",
		      type:"get",
		      data:{
		    	"id":id,  
		      },
			  success:function(data) {
				  $('#leaveList').datagrid('reload');
			  }
		  });
	  }
	
	</script>
	
	
	