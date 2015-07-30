<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<script type="text/javascript" src="${webRoot}/plug-in/date/dateFormat.js"></script>
<body>
	<div region="center" style="padding:1px;">
	   <div style="height:30px;" class="datagrid-toolbar">
		<div name="searchColums">
		<span style="display:-moz-inline-box;display:inline-block;"> 
		 <span style="float:left;"> <a href="#"
				class="easyui-linkbutton" plain="true" icon="icon-reload"
				onclick="refresh()">刷新 </a>
			</span>
		<span
			style="display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
			title="地区"> 单位： </span> <select id="districtCode"
			name="district.districtCode" WIDTH="200" style="width: 244px">
				<option value="">------------------请选择-----------------</option>
				<c:forEach items="${districtList}" var="district">
							<option value="${district.districtCode}"
								<c:if test="${district.districtCode==districtCode}">selected</c:if>>${district.districtName}</option>
						</c:forEach>
		 </select>
		  &nbsp&nbsp&nbsp
		    <span style="float:right">
				 <a href="#" class="easyui-linkbutton" iconCls="icon-search"
					onclick="upperItemListsearch()"> 查询 </a>
			</span>
	   </div>
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
												formatter : function(value,rec,index) {
													if(rec.belongs!="public") {
														return "<div style='width:400px' class='overflow'><h1>"+value+"</h1></div>";
													} else {
														return "<div style='width:400px' class='overflow'>"+value+"</div>";
													}
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
												field : 'belongs',
												title : '属性',
												formatter : function(value,
														rec, index) {
													if(value=="public") {
														return "公共所有";
													} else {
														return "<h1>地区添加</h2>";
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
                url: 'upperItemController.do?Sectreegried&districtCode=${districtCode}',
                method: 'get',
                rownumbers: true,
                idField: 'id',
                treeField: 'title',
                animate: true,
                fitColumns: true,
                showFooter: true
            ">
		</table>
		<input type="hidden" id="districtCode" value="${districtCode}">
	</div>

	<script type="text/javascript">
            
	function refresh() {
		location.reload();
	}
	
		function itemDel(id) {
			$.dialog.confirm('你确定删除该内容吗?', function(r) {
				$.ajax({
					url:'upperItemController.do?delSec',
					dataType:'json',
					type:"post",
					data: {
						"districtCode":$("#districtCode").val(),
						"id":id
					},
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
		
		function articleEdit(id) {
			var districtCode = $("#districtCode").val();
			location.href="upperItemController.do?addArticleSec&id="+id+"&districtCode="+districtCode;
		}
		
		function upperItemListsearch() {
			var districtCode = $("#districtCode").val();
			location.href = "upperItemController.do?itemSecond&districtCode="+districtCode;
		}
	</script>
</body>
</html>
