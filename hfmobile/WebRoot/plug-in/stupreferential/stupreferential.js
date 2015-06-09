$(function(){
	//点击提交按钮响应事件
	$("#submitbtn").click(function() {
		var flag = true;
		var message = "";
		
		var name = $("#name").val();
		if(name=="") {
			message = "请填写您的姓名";
			flag = false;
		}
		
		var regBox = {regMobile : /^0?1[3|4|5|7|8][0-9]\d{8}$/};
		var telephone = $("#telephone").val();
		
		var mflag = regBox.regMobile.test(telephone);
		if (telephone == "" || !mflag) {
			message = "请正确填写您的电话号码";
	    	flag = false;
		}
		
	    var college = $("#college").val();
	    if(college =="") {
	    	message = "请填写所在学校";
	    	flag = false;
	    }
	    	 
	    var department = $("#department").val();
	    if(department =="") {
	    	message = "请填写所在院系";
	    	flag = false;
	    }
	    
	    var grade = $("#grade").val();
	    
	    var sex = $("#sex").val();
	    
	    if(flag) {
	    	$divbtn = $("#sub_btn");
	    	$divbtn.attr("disabled", true);
	    	$.ajax({
	        	url:'submit.do',
	        	dataType:'json',
	        	type:'post',
	        	data:{
	        		"name":name,
	        		"telephone":telephone,
	        		"sex":sex,
	        		"college":college,
	        		"department":department,
	        		"grade":grade
	        	},
	        	success : function(data) { 
	        		var x=data.msg;
	        		if(x.match("提交成功")){
	        			window.location.href='success.do';
	        		}else if(x.match("很遗憾")){
	        			window.location.href='fail.do';
	        		}else{
	        			alert(x);
	        			$divbtn.removeAttr("disabled");
	        		}
	        	},
	        	error : function(e) {
	        		$divbtn.removeAttr("disabled");
	        		alert("error");
	        	}
	        });
		}else{
			alert(message);
		}
	});
	
	
	
	
	$("#alert_btn").click(function() {
		$(".alert_wrap").hide();
		$(".pop").hide();
	});
	
	$("#alert_btn_suc").click(function() {
		location.href=$("#success_href").val();
	});	
});