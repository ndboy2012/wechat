$(function() {
	
	$.fn.snow({ 
		minSize: 3,		//雪花的最小尺寸
		maxSize: 25, 	//雪花的最大尺寸
		newOn: 300		//雪花出现的频率 这个数值越小雪花越多
	});
	
	$.ajax({
		url:'queryNum.do',
	    type:'get',
	    dataType:'json',
	    data : {
	    	"openId":$("#openId").val(),
	    },
	    success:function(data) {
	    	$("#content").html(data.msg);
	    	if(data.success) {
	    		$("#expose").show();
	    	}
	    }
	});
	
	$("#friend").click(function(){
		 $(".share_friend").show();
		 $(".pop").show();
	});
	
	$("#expose").click(function(){
		$.ajax({
			url:'open.do',
		    type:'get',
		    dataType:'json',
		    data : {
		    	"openId":$("#openId").val(),
		    },
		    success:function(data) {
		    	$("#prizeContent").html(data.msg);
		    	$(".prizeShow").show();
		    	$(".pop").show();
		    },
		    error : function(e) {
		    	alert(e);
		    }
		});
	});
	
	
	$(".pop").click(function() {
		$(".pop").hide();
	});
	
});