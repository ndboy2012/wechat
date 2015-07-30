<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<head>
</head>
<div class="infopos">
	<div style="width:100%">
	<img alt="" src="${webRoot}/files/images/bg.jpg" style="width:100%">
	<input type="hidden" id="fistTimeLogin" value="">
  </div>
  <script type="text/javascript">
     $(function(){
    	 $.ajax({
    		  url:"upperMainController.do?queryLeave",
			  dataType:"json",
			  type:"get",
			  success: function(data) {
				  if(data.success) {
					  tip(data.msg);
				  }
			  }
    	 });
     });
  </script>
</div>
<div style="margin-top: 50px;"></div>
