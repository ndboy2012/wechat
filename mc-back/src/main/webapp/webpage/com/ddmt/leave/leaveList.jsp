<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="leaveList" title="留言信息" actionUrl="leaveController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="性质" field="property" ></t:dgCol>
   <t:dgCol title="发留言人" field="sources" ></t:dgCol>
   <t:dgCol title="收留言人" field="target" ></t:dgCol>
   <t:dgCol title="content" field="content" ></t:dgCol>
   <t:dgCol title="是否处理" field="isHandle" ></t:dgCol>
   <t:dgCol title="createTime" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="leaveController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="leaveController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="leaveController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="leaveController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>