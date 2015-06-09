<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../context/mytags.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>千名学子见习计划</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
  <link href="${webRoot}/plug-in/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <script src="${webRoot}/plug-in/jquery/jquery.min.js" type="text/javascript"></script>
  <script src="${webRoot}/plug-in/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <link href="${webRoot}/plug-in/button/buttons.css" type="text/css" media="screen"
	rel="stylesheet">
  <style type="text/css">
  .head{
       position:absolute;
       top:0;
       left:0;
       width:100%;
       height:150px;
  }
      
   .down {
     position:absolute;
     width:100%;
     height:50px;
     background: #eb0080;
   } 
   
   .button {
	width: 60%;
	line-height: 15px;
	text-align: center;
	font-weight: bold;
	color: #ffffff;
	background:rgb(6,142,190);
	border-radius: 5px;
	margin: 0 20px 40px 0;
	position: relative;
	overflow: hidden;
	left : 20%;
	height:40px;
   } 
  </style>
  </head>
  <body>
    <div class="head">
       <img alt="" src="${webRoot}/files/images/employee/logo.jpg" style="width:100%;height:80px">
       <img alt="" src="${webRoot}/files/images/employee/2.jpg" style="width:100%;height:480px">
       <img alt="" src="${webRoot}/files/images/employee/1.jpg" style="width:100%;height:380px">
       <div class="down">
           <button class="button" onclick="skip()">我要报名</button>
       </div>
    </div>
  </body>
  <script type="text/javascript">
      function skip() {
    	location.href="employer.do";  
      }
  </script>
</html>