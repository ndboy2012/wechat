<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'home.jsp' starting page</title>
<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>   
     <div style="margin:5% 5% 5% 5%;">
       <span id="clock"></span>
       <table class="gridtable">
	    <tr>
		  <th>总共登记人数</th>
		  <th>今日登记人数</th>
		  <th>昨天登记人数</th>
	    </tr>
	   <tr>
	     <td><%=request.getSession().getAttribute("totalRegisterCount")%></td>
	     <td><%=request.getSession().getAttribute("todayRegisterCount")%></td>
	     <td>0</td>
	   </tr>
      </table>
    </div>
    <script type="text/javascript">    
        	$(function() {
        		showtime();
        	});
    </script>
</html>


