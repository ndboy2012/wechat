<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,autocomplete"></t:base>
<script type="text/javascript">
	$(function() {
		$('#commonAdminList')
				.datagrid(
						{
							idField : 'id',
							title : '二级管理员基本信息',
							url : 'commonController.do?datagrid&field=id,username,password,name,telephone,district.districtName,role,content,status,createTime,updateTime,',
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
										field : 'username',
										title : '用户名',
										sortable : true
									},
									{
										field : 'name',
										title : '姓名',
										sortable : true
									},
									{
										field : 'telephone',
										title : '电话号码',
										sortable : true
									},
									{
										field : 'district.districtName',
										title : '单位',
										sortable : true,
										formatter:function(value, rec, index){
											return "<div style='width:190px' class='overflow'>"+value+"</div>";
										}
									},
									{
										field : 'role',
										title : '角色',
										sortable : false,
										formatter : function(value, rec, index) {
											var sa = "${roleReplace}"
													.split(",");
											var s = "";
											for (var i = 0, len = sa.length; i < len; i++) {
												var ia = sa[i].split("_");
												if (value == ia[1]) {
													s = ia[0];
												}
											}
											return s;
										}
									},
									{
										field : 'content',
										title : '描述',
										sortable : true,
										formatter:function(value, rec, index){
											return "<div style='width:150px' class='overflow'>"+value+"</div>";
										}
									},
									{
										field : 'status',
										title : '状态',
										sortable : false,
										formatter : function(value, rec, index) {
											var sa = "${statusReplace}"
													.split(",");
											var s = "";
											for (var i = 0, len = sa.length; i < len; i++) {
												var ia = sa[i].split("_");
												if (value == ia[1]) {
													s = ia[0];
												}
											}
											return s;
										}
									},
									{
										field : 'createTime',
										title : '创建时间',
										sortable : true,
										formatter : function(value, rec, index) {
											return new Date().format(
													'yyyy-MM-dd hh:mm:ss',
													value);
										}
									},
									{
										field : 'updateTime',
										title : '更新时间',
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
											href += "[<a href='#' onclick=delObj('commonController.do?del&id="
													+ rec.id
													+ "','commonAdminList')>";
											href += "删除</a>]";
											href += "[<a href='javascript:void(0)' onclick=resetPwd('"
													+ rec.id + "')>重置密码]";
											if (rec.status == '1') {
												href += "[<a href='javascript:void(0)' onclick=forbid('"
														+ rec.id + "')>";
												href += "禁止</a>]";
											}
											if (rec.status == '-1') {
												href += "[<a href='javascript:void(0)' onclick=allow('"
														+ rec.id + "')>";
												href += "启用</a>]";
											}
											return href;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$("#commonAdminList").datagrid(
										"clearSelections");
							},
							onClickRow : function(rowIndex, rowData) {
								rowid = rowData.id;
								gridname = 'commonAdminList';
							}
						});
		$('#commonAdminList').datagrid('getPager').pagination({
			beforePageText : '',
			afterPageText : '/{pages}',
			displayMsg : '{from}-{to}共{total}条',
			showPageList : true,
			showRefresh : true
		});
		$('#commonAdminList').datagrid('getPager').pagination({
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
	function reloadcommonAdminList() {
		$('#commonAdminList').datagrid('reload');
	}
	function getcommonAdminListSelected(field) {
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
	function getcommonAdminListSelections(field) {
		var ids = [];
		var rows = $('#commonAdminList').datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i][field]);
		}
		ids.join(',');
		return ids
	};
	function getSelectRows() {
		return $('#commonAdminList').datagrid('getChecked');
	}
	function commonAdminListsearch() {
		/* var queryParams = $('#commonAdminList').datagrid('options').queryParams;
		$('#commonAdminListtb').find('*').each(function() {
			queryParams[$(this).attr('name')] = $(this).val();
		});
		$('#commonAdminList')
				.datagrid(
						{
							url : 'commonController.do?datagrid&field=id,username,password,district,role,describe,status,createTime,updateTime,',
							pageNumber : 1
						}); */
		$('#commonAdminList').datagrid("reload", {
			"district.districtCode" : $("#districtCode").val(),
			"status" : $("#status").val()
		});
	}
	function dosearch(params) {
		var jsonparams = $.parseJSON(params);
		$('#commonAdminList')
				.datagrid(
						{
							url : 'commonController.do?datagrid&field=id,username,password,district,role,describe,status,createTime,updateTime,',
							queryParams : jsonparams
						});
	}
	function commonAdminListsearchbox(value, name) {
		var queryParams = $('#commonAdminList').datagrid('options').queryParams;
		queryParams[name] = value;
		queryParams.searchfield = name;
		$('#commonAdminList').datagrid('reload');
	}
	$('#commonAdminListsearchbox').searchbox({
		searcher : function(value, name) {
			commonAdminListsearchbox(value, name);
		},
		menu : '#commonAdminListmm',
		prompt : '请输入查询关键字'
	});
	function searchReset(name) {
		$("#" + name + "tb").find(":input").val("");
		commonAdminListsearch();
	}
</script>
<table width="100%" id="commonAdminList" toolbar="#commonAdminListtb"></table>
<div id="commonAdminListtb" style="padding:3px; height: auto">
	<div name="searchColums">
		<span style="display:-moz-inline-box;display:inline-block;"> <span
			style="display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
			title="地区"> 地区： </span> <select id="districtCode"
			name="district.districtCode" WIDTH="100" style="width: 104px">
				<option value="">---请选择---</option>
				<c:forEach items="${fn:split(districtReplace, ',')}" var="r">
					<option value="${fn:split(r, '_')[1]}">${fn:split(r, '_')[0]}
					</option>
				</c:forEach>
		</select> <span style="display:-moz-inline-box;display:inline-block;"> <span
				style="display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
				title="状态"> 状态： </span> <select id="status" name="status"
				WIDTH="100" style="width: 104px">
					<option value="">---请选择---</option>
					<c:forEach items="${fn:split(statusReplace, ',')}" var="r">
						<option value="${fn:split(r, '_')[1]}">${fn:split(r, '_')[0]}
						</option>
					</c:forEach>
			</select>
		</span>
	</div>
	<div style="height:30px;" class="datagrid-toolbar">
		<span style="float:left;">
		
		<a href="#" class="easyui-linkbutton"
			plain="true" icon="icon-add"
			onclick="add('录入','commonController.do?addorupdate','commonAdminList',null,null)">录入</a><a
			href="#" class="easyui-linkbutton" plain="true" icon="icon-edit"
			onclick="update('编辑','commonController.do?addorupdate','commonAdminList',null,null)">编辑</a><a
			href="#" class="easyui-linkbutton" plain="true" icon="icon-search"
			onclick="detail('查看','commonController.do?addorupdate','commonAdminList',null,null)">查看</a></span>
		<span style="float:right"> <a href="#"
			class="easyui-linkbutton" iconCls="icon-search"
			onclick="commonAdminListsearch()"> 查询 </a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-reload"
			onclick="searchReset('commonAdminList')"> 重置 </a>
		</span>
	</div>
	<script type="text/javascript">
		function forbid(id) {
			$.ajax({
				url : "commonController.do?forbid",
				type : "get",
				dataType : "json",
				data : {
					"id" : id,
				},
				success : function(data) {
					if (data.success) {
						$('#commonAdminList').datagrid('reload');
						tips(data.msg);
					}
				}
			});
		}

		function allow(id) {
			$.ajax({
				url : "commonController.do?allow",
				type : "get",
				dataType : "json",
				data : {
					"id" : id,
				},
				success : function(data) {
					if (data.success) {
						$('#commonAdminList').datagrid('reload');
						tips(data.msg);
					}
				}
			});
		}

		function resetPwd(id) {
			$.dialog.confirm('你确定进行密码重置吗?', function(r) {
				$.ajax({
					url : "commonController.do?reset",
					type : "get",
					dataType : "json",
					data : {
						"id" : id,
					},
					success : function(data) {
						tip(data.msg);
					}
				});
			});
		}
		
		//这个地方实现刷新按钮点击后的方法
		function refresh() {
			location.reload();
		}
	</script>