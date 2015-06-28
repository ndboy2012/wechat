$(function(){
	$(".bwidth span").last().css("margin-bottom","5%");
	$(".bwidth span:odd").css("color","#0166b4");
});
/*下拉框*/
 $(function(){
   $(".select_box").click(function(event){   
    event.stopPropagation();
    $(this).find(".option").toggle();
    $(this).parent().siblings().find(".option").hide();
   });
   $(document).click(function(event){
    var eo=$(event.target);
    if($(".select_box").is(":visible") && eo.attr("class")!="option" && !eo.parent(".option").length)
    $('.option').hide();           
   });
   $(".option a").click(function(){
    var value=$(this).text();
    $(this).parent().siblings(".select_txt").text(value);
    $("#select_value").val(value);
    });
});

/*输入框长度*/
$(function(){
	var w1=$(".bodys a.next.bk1").width();	
	var w2=w1-14;	
	$(".bodys .txtsr1").width(w2+"px");	
	$(".bodys .twidth").width(w2+"px");
});

//绑定手机页面的输入框宽度设置及文字标签的行高设置
$(function(){
	var w=$(".wapper").width();
	var w1=$(".wapper .sr").width();
	var w2=$(".wapper .sr label").width();
	var w4=$(".wapper .checkcode a").width();
	var w3=w*0.9-w2-14;
	var w5=w3-w4-22;
	//alert("w1:"+w1+"px-w2:"+w2+"px="+w3+";w:"+w+"px");	
	$(".wapper .tel input").width(w3+"px");
	$(".wapper .checkcode input").width(w5+"px");
	
	var h2=$(".wapper .sr input").height();
	$(".wapper .sr label").css("line-height",h2+"px");
});

//绑定手机页面验证
$(function(){
	//手机号码判断
	$(".wapper .tel input").blur(function(){
		var regBox = {regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
		};	
		var telValue=$(".wapper .tel input").val();
		var mflag = regBox.regMobile.test(telValue);
		if(telValue=="")
		{
			telValue=$(".wapper .tel input").val();
			$(".wapper .tel input").focus();
			$("#tips").text("手机号码不能为空，请重新填写。");
		}
		else if (!(mflag)) {
		telValue=$(".wapper .tel input").val();
		$(".wapper .tel input").focus();
		$("#tips").text("手机号码有误，请重新填写。");		
    }else{
        //alert("信息正确！");
    };
	});
	
	$(".wapper .tel input").focus(function(){
		$("#tips").text("");
	});

});

   /*
	$(".wapper .next").click(function(){
		var regBox = {regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
		};	
		var telValue=$(".wapper .tel input").val();
		var mflag = regBox.regMobile.test(telValue);
		if($(".wapper .tel input").val()==""){
			telValue=$(".wapper .tel input").val();
			$(".wapper .tel input").focus();
			$("#tips").text("手机号码不能为空，请重新填写。");
		}	
		else if(!(mflag)){
			telValue=$(".wapper .tel input").val();
			$(".wapper .tel input").focus();
			$("#tips").text("手机号码有误，请重新填写。");
		}
		else if($(".wapper .checkcode input").val()==""){
			telValue=$(".wapper .checkcode input").val();
			$(".wapper .checkcode input").focus();
			$("#tips").text("验证码不能为空，请重新填写。");
		}
		else{
			 $("#LoginForm").submit(function(){
				$ajax({
					url:"telephone/bind.do",
					type:"post",
					data:$("#LoginForm").serialize(),
					success:function() {
						
					}
				});
			});			
		}
	});*/
