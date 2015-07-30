<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="districtList" title="地区基本信息" actionUrl="upperDistrictController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="地区编号" field="districtCode" hidden="false"></t:dgCol>
   <t:dgCol title="地区名称" field="districtName" ></t:dgCol>
   <t:dgCol title="email" field="email" ></t:dgCol>
   <t:dgCol title="电话" field="telephone" ></t:dgCol>
   <t:dgCol title="详细地址" field="address" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="更新时间" field="updateTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="upperDistrictController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="upperDistrictController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="upperDistrictController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="upperDistrictController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>