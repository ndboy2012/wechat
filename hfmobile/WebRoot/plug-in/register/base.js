$(function(){
	var pop = $(".pop");
	var pop2 = $(".pop2");
	var apply_pop = $(".apply_sucess");
	var alert_pop = $(".alert_wrap");
	var validate_pop = $(".validate_tel");
	
	//pop2点击后影藏
	pop2.on("click",function(){
		$(this).hide();
	});
	
	$("#get").click(function(){
		apply_pop.hide();
		alert_pop.hide();
		validate_pop.hide();
		pop.hide();
	});
	
	function show_pop2() {
		pop.hide();
		apply_pop.hide();
		alert_pop.hide();
		pop2.show();
	}
	//点击分享按钮
	$(".share_btn").click(function() {
		show_pop2();
	});
	
	//点击申请按钮
	$(".apply").click(function() {
		var telephone = $("#temp").val();
		$.ajax({
			url:"apply.do",
		    type:"get",
		    dataType:"json",
		    data:{
		    	"telephone":telephone,
		    },
		   success:function(data) {
			  if(data.success) {  //如果领取成功
				  if(data.msg=="hasget") {   //如果已经领取过了
					  $("#get_failure").html("您已经领取过了");
					  apply_pop.hide();
					  validate_pop.hide();
					  alert_pop.show();
					  pop.show();
				  } else {     //领取成功
					  $("#apply_txt").html(data.msg);
					  apply_pop.show();
					  validate_pop.hide();
					  alert_pop.hide();
					  pop.show(); 
				  }
			  } else {           //如果领取失败
				  $("#get_failure").html(data.msg);
				  apply_pop.hide();
				  validate_pop.hide();
				  alert_pop.show();
				  pop.show();
			  }
		   }
		});
	});
	
	$(".bind").click(function() {
		pop.show();
		validate_pop.show();
	});
	
	$("#validate").click(function(){
		var message = "验证号码不能为空";
		var telephone = $("#validate_tel").val();
		if(telephone=="") {
			 $("#validate_msg").html(message);
		} else {
			$.ajax({
				url:"validate.do",
			    type:"get",
			    dataType:"json",
			    data:{
			    	"telephone":telephone,
			    },
			   success:function(data) {
				   $("#validate_msg").html(data.msg);
				   $("#temp").val(data.obj);
			   }
			});
		}
	});
	
	$("#apply_sucess").click(function(){
		apply_pop.hide();
		pop.hide();
		pop2.show();
	});
});