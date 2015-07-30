<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="itemList" title="类目基本信息" actionUrl="itemController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="所属地区" field="belongs" ></t:dgCol>
   <t:dgCol title="创建者" field="creator" ></t:dgCol>
   <t:dgCol title="是否公有" field="isPublic" ></t:dgCol>
   <t:dgCol title="显示标题" field="title" ></t:dgCol>
   <t:dgCol title="内容编号" field="article" ></t:dgCol>
   <t:dgCol title="类型" field="type" ></t:dgCol>
   <t:dgCol title="状态" field="status" ></t:dgCol>
   <t:dgCol title="parentId" field="parentId" ></t:dgCol>
   <t:dgCol title="等级" field="level" ></t:dgCol>
   <t:dgCol title="排序" field="sort" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="修改时间" field="updateTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="itemController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="itemController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="itemController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="itemController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>