<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<head>
</head>
  <div style="width:100%">
	<img alt="" src="${webRoot}/files/images/bg.jpg" style="width:100%">
	<script type="text/javascript">
	  $(function(){
		  $.ajax({
			  url:"commonMainController.do?queryLeave",
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