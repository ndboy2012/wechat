window.sncode = "null";
window.prize = "谢谢参与";
var zjl = false;
var num = 0;
var goon = true;
var sncode = "";
var prize = -4;
var running = false;
var session_id = "";

function get_request_url(paras) {
	var url = location.href;
	var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
	var paraObj = {};
	for (var i = 0; j = paraString[i]; i++) {
		paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j
				.indexOf("=") + 1, j.length);
	}
	var returnValue = paraObj[paras.toLowerCase()];
	if (typeof (returnValue) == "undefined") {
		return "";
	} else {
		return returnValue;
	}
}

$(function() {
	  $.ajax({		 
		    url: "flashLoginVerify.do?open_id="+$('#openId').val(),
		    type:"get",
		    dataType: "json",    
		    success: function(data) {         
		         if(data.msg=="false") {
		        	 alert("亲！您还没有登记，无法抽奖");
		         }
		      }, 
			error: function() {
			 	alert("没有登入抽奖失败!");
				running=true;
				}			
	});//登陆验证	
	  
	$.ajax({
		url : "registerInfo.do",// 调用接口
		type : 'get',
		dataType : "json",
		data:{
            "open_id":$('#openId').val()			
		},
		success : function(datate) {
			$('#prize').html("no chances");
			if(datate.obj != null) {
			$('#all_num').html(datate.obj.monthSignAccounts);
			} else {
				$('#all_num').html("0");
			}
			$('#lottery_num').html(datate.msg);
		},
		error : function() {
			$('#lottery_num').html("0");
		},
		timeout : 4000
	});

	if (running) {
		$('#scratchpad').html("");
		$('#prize').html("没有刮奖机会了~"); // 刮奖后所显示的东西
		return;
	}
	
	/*
	 * 检测刮奖的动作
	 */
	$("#scratchpad").wScratchPad({
		width : 150,
		height : 40,
		color : "#a9a9a7",
		scratchMove : function() { // 刮开后触发
			if (num == 0) {
				ajaxPrize();
			}
			num++;
			var percent_scratched = $("#percent_scratched").html(); // 中奖率
			if (goon && percent_scratched > 20) {// 刮奖百分比率
				// $("#zjl").fadeIn();
				$("#zjl").slideToggle(500); // 中奖率
				$("#outercont").slideUp(500);
				goon = false;
			}
		}
	});

	// 抽到奖后显示
	function getprize(prize) {
		/*
		 * 显示奖品信息
		 */
		if (prize != null) {
			if (prize == -1) {
				$("#prizetype").html("您今天已经没有抽奖机会了");
				sncode = "今天没有机会了";
			}			
			if(prize == -3) {
				$("#prizetype").html("您还没登记无法抽奖");
				sncode = "无法抽奖";
			}
			if (prize == 2) {
				$("#prizetype").html("人品爆发，大奖到手，1G流量红包！ 继续刮下一张，相信好运会延续的哦！点击下方【活动细则】，立即了解领奖流程吧！");
				sncode = "1G流量红包";
			}
			if (prize == 3) {
				$("#prizetype").html("人品爆发，大奖到手，200M流量包！点击下方【活动细则】，立即了解领奖流程！");
				sncode = "200M流量包";
			}
			if (prize == 4) {
				$("#prizetype").html("人品爆发，大奖到手，免费电子书！点击下方【活动细则】，立即了解领奖流程！");
				sncode = "免费电子书";
			}
			if (prize == 5) {
				$("#prizetype").html("人品爆发，大奖到手，免费铃音！点击下方【活动细则】，立即了解领奖流程！");
				sncode = "免费铃音！";
			}
			if (prize == 1) {
				$("#prizetype").html("人品爆发，大奖到手，电影票通票2张 !点击下方【活动细则】，立即了解领奖流程！");
				sncode = "免费电影票2张"
			}
			if (prize == 6) {
				$("#prizetype").html("人品爆发，大奖到手，安徽手机报免费体验60日！点击下方【活动细则】，立即了解领奖流程！");
				sncode = "安徽手机报免费体验60日安徽手机报免费体验60日";
			}
			if (prize == 7) {
				sncode = "感谢参与";
				$("#prizetype").html("别灰心，就差一点点，继续刮下一张吧！");
			}
		} else {
			alert("抽奖失败");
		}
		$('#title_prize').html(sncode);
		$('#prize').html(sncode);
		$('#scratchpad').html("");
	}

	$("#save-btn").bind("click", function() {
		var btn = $(this);
		window.location.reload();
	});

	/**
	 * 用户刮开将后将调用，用于更新后台数据库抽奖情况和签到情况 随机产生奖品
	 */
	function ajaxPrize() {
		
		$.ajax({
			url : "newRandomPrize.do?open_id="+ $('#openId').val(),// 调用接口
			dataType : "json",
			data:{
				
			},
			success : function(data) {
				prize2 = parseInt(data.msg);
				getprize(prize2);
				/*
				 * 更新抽奖次数,并把用户所获奖项记录到数据库中
				 */
				$.ajax({		
					url : "updatePrizeInfo.do",// 调用接口
					dataType : "json",
					data:{
                        "openid":$("#openId").val()						
					},
					success : function(data) {
						$('#all_num').html(data.msg);
						$('#lottery_num').html("0");
					},
					error : function() {
						$('#lottery_num').html("0");
					},
					timeout : 4000
				});
			},
			error : function() {
				prize = null;
			},
			timeout : 40000
		});
	}

});