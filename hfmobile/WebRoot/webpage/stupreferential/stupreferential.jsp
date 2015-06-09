<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>大学生寒假特惠</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
  <link href="${webRoot}/plug-in/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${webRoot}/plug-in/stupreferential/stupreferential.css" rel="stylesheet">
  
  <script src="${webRoot}/plug-in/jquery/jquery.min.js" type="text/javascript"></script>
  <script src="${webRoot}/plug-in/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="${webRoot}/plug-in/stupreferential/stupreferential.js" type="text/javascript"></script>
  <style type="text/css">
  .head{
       position:absolute;
       top:0;
       left:0;
       width:100%;
       height:150px;
      }
   
   .center {
	position: relative;
	top: 120px;
	height: 100%;
	width:100%;
	left:0;
   }
   .infopos {
      position:relative;
      padding:10px;
      top:30px;
      height:80px;
   }
  </style> 
  </head>
  <body>
     <div class="head">
       <img alt="" src="${webRoot}/files/images/stupreferential/pagehead.png" style="height:200px;width:100%">
     </div>
     <div style="height:220px"></div>
     <div style="margin:5%;">
     	<div style="width:100%">
     		<div style="height:35px">
     			<div style="float:left;width:45%;">
     				<input type="text" id="name" class="textinputb" name="name" placeholder="&nbsp姓名">
     			</div>
     			<div style="float:right;width:45%;">
					<label>
           				<input type="radio" name="sex" id="sex" value="男" checked><strong>男&nbsp&nbsp&nbsp&nbsp</strong>
            		</label>
            		<label>
            			<input type="radio" name="sex" id="sex" value="女" ><strong>女 &nbsp</strong>
            		</label>
           		</div>
           	</div>
		<div style="height:25px;width:100%"></div>
     		<div style="height:35px;">
     			<input type="text" id="telephone" class="textinputb" name="telephone" placeholder="&nbsp手机号码">
     		</div>
           	<div style="height:25px;width:100%"></div>
     		<div style="height:35px;">
     			<input type="text" id="college" class="textinputb" name="college" placeholder="&nbsp学校">
     		</div>
		<div style="height:25px;width:100%"></div>
     		<div style="height:35px;">
     			<input type="text" id="department" class="textinputb" name="department" placeholder="&nbsp院系">
     		</div>
		<div style="height:25px;width:100%"></div>
     		<div style="height:35px;">
     			<input type="text" id="grade" class="textinputb" name="grade" placeholder="&nbsp年级">
     		</div>
     		<div style="height:50px;width:100%;"></div>
		<div style="height:50px;width:100%;">
			<center><p><font color="#d9d6cf">成功提交以上信息您就可以登记参与本次特惠活动<font/></p></center>
		</div>
     		<div style="text-align:center;height:40px;" id="sub_btn"><center><button id="submitbtn" class="submitbtn">点击提交</button></center></div>
     	</div>
     </div>
     <div style="height:50px"></div>
  </body>
</html>
