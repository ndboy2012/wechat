<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<script type="text/javascript" src="${webRoot}/plug-in/date/dateFormat.js"></script>
<body>
	<div region="center" style="padding:1px;">
		<div style="height:30px;" class="datagrid-toolbar">
			 <span style="float:left;"> <a href="#"
				class="easyui-linkbutton" plain="true" icon="icon-add"
				onclick="articleAdd()">添加文章 </a>
			</span>
		</div>
		<script type="text/javascript">
			$(function() {
				$('#tg')
						.treegrid(
								{
									columns : [ [
											{
												field : 'title',
												title : '标题',
												formatter:function(value, rec, index){
													return "<div style='width:350px' class='overflow'>"+value+"</div>";
												}
											},
											{
												field : 'type',
												title : '类型',
												formatter : function(value,
														rec, index) {
													var sa = "${itemReplace}"
															.split(",");
													var s = "";
													for (var i = 0, len = sa.length; i < len; i++) {
														var ia = sa[i]
																.split("_");
														if (value == ia[1]) {
															s = ia[0];
														}
													}
													return s;
												}
											},
											{
												field : 'isPublic',
												title : '是否开放',
												formatter : function(value,
														rec, index) {
													var sa = "${isPublicReplace}"
															.split(",");
													var s = "";
													for (var i = 0, len = sa.length; i < len; i++) {
														var ia = sa[i]
																.split("_");
														if (value == ia[1]) {
															s = ia[0];
														}
													}
													return s;
												}
											},
											{
												field : 'belongs',
												title : '属性',
												formatter : function(value,
														rec, index) {
													if(value=="public") {
														return "公共所有";
													} else {
														return "地区添加";
													}
													return s;
												}
											},
											{
												field : 'status',
												title : '状态',
												formatter : function(value,
														rec, index) {
													var sa = "${statusReplace}"
															.split(",");
													var s = "";
													for (var i = 0, len = sa.length; i < len; i++) {
														var ia = sa[i]
																.split("_");
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
												formatter : function(value,
														rec, index) {
													var date = new Date(value);
													return date
															.format("yyyy-MM-dd hh:mm:ss");
												}
											},
											{
												field : 'updateTime',
												title : '更新时间',
												formatter : function(value,
														rec, index) {
													var date = new Date(value);
													return date
															.format("yyyy-MM-dd hh:mm:ss");
												}
											},
											{
												field : 'opt',
												title : '操作',
												width : 100,
												formatter : function(value,
														rec, index) {
													if (!rec.id) {
														return '';
													}
													var href = '';
													if(rec.belongs!="public") {
														href += "[<a href='#' onclick=itemDel('"
															+ rec.id+"')>";
													    href += "删除</a>]";
													    href += "[<a href='#' onclick=articleEdit('"
															+ rec.id + "')>";
													    href += "编辑</a>]";
													}
													if(rec.type=="item") {
													} else {
														href += "[<a href='#' onclick=preview('"
															+ rec.id + "')>";
													    href += "预览文章</a>]";
													}
													return href;
												}
											} ] ]
								});
			});
		</script>
		<table id="tg" class="easyui-treegrid"
			style="width:1110px;height:650px;"
			data-options="
                url: 'commonItemController.do?treegried',
                method: 'get',
                rownumbers: true,
                idField: 'id',
                treeField: 'title',
                animate: true,
                fitColumns: true,
                showFooter: true
            ">
		</table>
	</div>

	<script type="text/javascript">
        
		function itemDel(id) {
			$.dialog.confirm('你确定删除该内容吗?', function(r) {
				$.ajax({
					url:'commonItemController.do?del&id='+id,
					dataType:'json',
					success:function(data){
						tip(data.msg);
						setTimeout(
								function() {
									location.reload();;
								}, 1500);
					}
				});
			});
		}
		
		function articleAdd() {
			location.href = "commonItemController.do?addArticle";
		}
		
		function articleEdit(id) {
			location.href="commonItemController.do?addArticle&id="+id;
		}
		
		function preview(id) {
			window.open("commonItemController.do?preview&id="+id, "文章预览", "toolbar=no,location=no", false);
		}
		
		function itemPublic(id) {
			$.ajax({
				url:"commonItemController.do?isPublic",
			    dataType:"json",
			    type:"post",
			    data:{
			    	"id":id,
			    },
			    success : function(data) {
			    	$('#tg').treegrid('reload');
			    }
			});
		}
	</script>
</body>
</html>
