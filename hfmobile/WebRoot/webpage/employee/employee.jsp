<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>千名学子见习计划</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
  <link href="${webRoot}/plug-in/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <script src="${webRoot}/plug-in/jquery/jquery.min.js" type="text/javascript"></script>
  <script src="${webRoot}/plug-in/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
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
       <img alt="" src="${webRoot}/files/images/employee/head.png" style="height:150px;width:100%">
     </div>
     <div class="center">
         <div class="infopos"><h4>个人信息:</h4></div> 
         <div class="panel panel-default">
            <div class="panel-body">
                <div style="height:48px">
                    <div style="width:40%;float:left">
			          <input type="text" class="form-control" id="name" placeholder="姓名"> 
			        </div>
                    <div style="position:relative;float:right;width:45%;margin-top:5px;margin-left:0px">
			              <label>
                              <input type="radio" name="sex" id="sex" value="男" checked><strong>男&nbsp&nbsp&nbsp&nbsp</strong>
                          </label>
                          <label>
                              <input type="radio" name="sex" id="sex" value="女" ><strong>女 &nbsp</strong>
                          </label>
                    </div>
			    </div>
			     <div style="height:48px">
			        <input type="text" class="form-control" id="telephone" placeholder="联系方式"> 
			    </div>
			     <div style="height:48px">
			        <div style="width:60%;float:left">
			        <input type="text" class="form-control" id="college" placeholder="学校"> 
			        </div>
			        <div style="width:35%;float:right">
			        <input type="text" class="form-control" id="collegeRegion" placeholder="校区"> 
			        </div>
			    </div>
			    <div style="height:48px">
			        <input type="text" class="form-control" id="department" placeholder="院系"> 
			    </div>
			     <div style="height:48px">
			        <div style="width:60%;float:left">
			        <input type="text" class="form-control" id="major" placeholder="专业"> 
			        </div>
			        <div style="width:35%;float:right">
			        <input type="text" class="form-control" id="level" placeholder="年级"> 
			        </div>  
			    </div>
			    <div style="height:48px">
			        <div style="width:60%;float:left">
			        <input type="text" class="form-control" id="association" placeholder="参与社团"> 
			        </div>
			        <div style="width:35%;float:right">
			        <input type="text" class="form-control" id="carreer" placeholder="现任职务"> 
			        </div>
			    </div>
			    <div style="height:110px">
			       <textarea class="form-control" rows="4" id="personalIntroduct" placeholder="个人简历及实践经历"></textarea>
			    </div>
			    <div style="height:12%">
			       <textarea class="form-control" rows="2" id="prize" placeholder="曾获奖励(选填)"></textarea>
			    </div>
            </div>
         </div>   
         <div style="height:12%;width:80%;margin-left:10%">
			  <button type="button" class="btn btn-primary btn-lg btn-block" onclick="submit()" id="submit">点击提交</button>
		 </div> 
     </div>
  </body>
  <script type="text/javascript">
     function submit() {
    	 var flag = true;
    	 var message = "";
    	 
    	 var name = $("#name").val();
    	 if(name=="") {
    		 message = "请填写您的姓名";
    		 flag = false;
    	 }
    	 
    	 var sex = document.getElementsByName("sex");
    	 for(var i=0;i<sex.length;i++) {
				if(sex[i].checked) {
					var applicantSex = sex[i].value;
				}
			}
    	 
    	 var telephone = $("#telephone").val();
    	 var regBox = { regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/}; 
		 var mflag = regBox.regMobile.test(telephone);
		 if(telephone=="") {
			 message = "请填写您的联系方式";
			 flag = false; 
		 } else if(!(mflag)) {
			 message = "您输入号码有误，请重新输入";
			 flag = false; 
		 }
			
    	 var college = $("#college").val();
    	 if(college =="") {
    		 message = "请填写所在学校";
    		 flag = false;
    	 }
    	 
    	 var collegeRegion = $("#collegeRegion").val();
    	 if(college =="") {
    		 message = "请填写所在学校校区";
    		 flag = false;
    	 }
    	 var department = $("#department").val();
    	 if(department =="") {
    		 message = "请填写所在院系";
    		 flag = false;
    	 }
    	 
    	 var major = $("#major").val();
    	 if(major =="") {
    		 message = "请填写所读专业";
    		 flag = false;
    	 }
    	 
    	 var level = $("#level").val();
    	 if(level =="") {
    		 message = "请填写您所在年级";
    		 flag = false;
    	 }
    	 
    	 var association = $("#association").val();
    	 var carreer = $("#carreer").val();
    	 var personalIntroduct = $("#personalIntroduct").val();
    	 var prize = $("#prize").val();
    	 
    	 if(flag) {
    		 $.ajax({
        		 url:'submit.do',
        		 dataType:'json',
        		 type:'post',
        		 data:{
        			"name":name,
        			"sex":applicantSex,
        			"telephone":telephone,
        			"college":college,
        			"collegeRegion":collegeRegion,
        			"department":department,
        			"major":major,
        			"level":level,
        			"association":association,
        			"carreer":carreer,
        			"personalIntroduct":personalIntroduct,
        			"prize":prize
        		 },
        		 success : function(date) {
        			 alert(date.msg);
        			 if(date.success) {
        				location.href = "http://weibodl.55zhe.net/wz.php?pageid=135103&openid=fromuserid&aw=wx.qq.com"; 
        			 } else {
        				 location.href = "employer.do"; 
        			 }
        			
        		 },
        		 errot : function(e) {
        			 alert("error");
        		 }
        	 });
    	 } else {
    		 alert(message);
    		 location.href = "employer.do";
    	 }
     }
  </script>
</html>
