$(function() {

	$("#alert_btn").click(function() {
		$(".alert_wrap").hide();
		$(".pop").hide();
	});

	$("#achieveCode").click(function() {
		var telephone = $("#telephone").val();
		if (telephone == "") {
			alertMsg("请填写手机号码");
		} else {
			$.ajax({
				url : "register.do?code",
				type : "post",
				dataType : "json",
				data : {
					"telephone" : telephone,
				},
				success : function(data) {
					data = eval(data);
					if (data.success) {
						var wait = 60;
						countDown(wait);
						alertMsg(data.msg);
					} else {
						alertMsg(data.msg);
					}

				},
				error : function(data) {
					alertMsg("网络错误");
				}
			});
		}
	});

	$("#submit").click(function() {
		var telephone = $("#telephone").val();
		var validate = $("#validate").val();
		if (telephone == "") {
			alertMsg("请输入电话号码");
		} else if (validate == "") {
			alertMsg("验证码不能为空");
		} else {
			$.ajax({
				url : "register.do?recorde",
				type : "post",
				dataType : "json",
				data : {
					"telephone" : telephone,
					"validate" : validate,
					"recommender" : $("#recommender").val(),
				},
				success : function(data) {
					alertMsg(data.msg);
				}
			});
		}
	});
});

function alertMsg(message) {
	$("#alert_msg").html(message);
	$(".alert_wrap").show();
	$(".pop").show();
}

function countDown(wait) {
	if (wait == 0) {
		$("#achieveCode").html("获取验证码");
		$("#achieveCode").removeAttr("disabled");
		removeSession();
	} else {
		$("#achieveCode").html(wait + "秒后再试");
		wait--;
		$("#achieveCode").attr("disabled", true);
		setTimeout(function() {
			countDown(wait);
		}, 1000);
	}
}