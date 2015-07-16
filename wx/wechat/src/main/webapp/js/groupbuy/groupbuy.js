$(function(){
	$.ajax({
		url:"product.do",
		type:"get",
		dataType:"json",
		success : function(data){
			var list = data.obj;
			var text = "";
			var imageUrl = $("#picture").val();
			$.each(list,function(i,item) {
				text = text+"<div class=\"content\">"+
		                     "<div class=left>"+
			                  "<div class=\"left-img\">"+
				              "<img alt=\"\" src=\""+imageUrl+item.picture+"\" style=\"width:100%\">"+
			                  "</div>"+
			                  "<div class=\"left-content\">"+
			                  "<ul>"+
					          "<li>"+item.name+"</li>"+
					          "<li>机型："+item.descrieb+"</li>"+
					          "<li class=\"li-font\">当前裸机价</li>"+
					          "<li class=\"li-font\">￥："+item.nakePrice+"</li>"+
					          "<li class=\"li-font\">拼团最低价</li>"+
					          "<li class=\"li-font\">￥："+item.groupPrice+"</li>"+
				              "</ul>"+
			                  "</div>"+
		                      "</div>"+
		                      "<div class=\"price\">"+
			                  "<button>立即抢购</button>"+
		                      "</div>"+
		                      "</div>"+
	                          "</div>"+
	                          "<div><HR></div>";
			});
			$("#content").html(text);
		}
	});
});