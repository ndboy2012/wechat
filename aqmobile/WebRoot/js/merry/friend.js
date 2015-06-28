$(function(){
	
	$.fn.snow({ 
		minSize: 3,		//雪花的最小尺寸
		maxSize: 25, 	//雪花的最大尺寸
		newOn: 300		//雪花出现的频率 这个数值越小雪花越多
	});
	
   $.ajax({
	    url:'numbers.do',
		dataType:'json',
		type:'get',
		data:{
			"openId":$("#openId").val(),
		},
		success : function(data) {
			if(!data.success) {
				$("#friend").attr("disabled","true");
				document.getElementById("friend").style.background="#d3d3d3";
			}
			$("#content").html(data.msg);
		} 
   });
   
   $("#friend").click(function(){
	     $.ajax({
	    	 url:'apply.do',
	    	 dataType:'json',
	    	 type:'get',
	    	 data:{
	    		 "openId":$("#openId").val(),
	    	 },
	    	 success : function(data) {
	    		 $("#content").html(data.msg);
	    		 document.getElementById("friend").style.background="#d3d3d3";
	    		 $("#friend").html("您已经拆过了");
	    		 $("#friend").attr("disabled","true");
	    	 }
	     });
   });
    
});

function iwant() {
	   location.href="http://weibodl.55zhe.net/wz.php?pageid=145452&openid=oBlK0uHRNzKX9_IRsv0U9fOD4H5E&aw=wx.qq.com";
}

