$(function(){
	// 点击获取验证码按钮
	$("#valid_btn").click(function() {
		var telephone = $("#telephone").val();
		if (telephone == "") {
			alertMsg("请填写您的手机号");
		} else {
			$.ajax({
				url : "code.do",
				type : "get",
				dataType : "json",
				data : {
					"telephone" : telephone
				},
				success : function(data) {
					if (data.success) {
						var wait = 60;
						countDown(wait);
					} else {
						alertMsg(data.msg);
					}
				}
			});
		}
	});
	
	//点击提交按钮响应事件
	$(".submit_btn").click(function(){
		var telephone = $("#telephone").val();
		var validateCode = $("#validateCode").val();
		var recommend = $("#recommend_telephone").val();
		if(telephone=="") {
			alertMsg("请填写您的手机号");
		} else if(validateCode=="") {
			alertMsg("请填写您的验证码");
		} else {
		    $.ajax({
		    	url:"submit.do",
		        type:"get",
		        dataType:"json",
		        data: {
		        	"telephone":telephone,
		            "validateCode":validateCode,
		            "recommendTelephone":recommend,
		        },
		        success:function(data){
		           if(data.success) {
		        	   //如果登记成功，提示跳转进行流量领取
		        	   $("#alert_msg_suc").html(data.msg);
		        	   $(".alert_wrap_suc").show();
		        	   $(".pop_suc").show();
		           } else {
		        	   alertMsg(data.msg);
		           }
		        }
		    });
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

function countDown(wait) {
	if (wait == 0) {
		$("#valid_btn").val("获取验证码");
		$("#valid_btn").removeAttr("disabled");
		removeSession();
	} else {
		$("#valid_btn").val(wait + "秒后再试");
		wait--;
		$("#valid_btn").attr("disabled", true);
		setTimeout(function() {
			countDown(wait);
		}, 1000);
	}
}

function removeSession() {
	$.ajax({
		url:"remove.do",
		type:"get",
	    dataType:"json",
	});
}

function alertMsg(message) {
	$("#alert_msg").html(message);
	$(".alert_wrap").show();
	$(".pop").show();
}