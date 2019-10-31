
$(function(){
var roleCode = $("#roleCode");
var path = $("#path").val();
roleCode.bind("blur",function(){
	$.ajax({
		type:"GET",
		url:path+"/role/roleCode.json",
		data:{roleCode:roleCode.val()},
		dataType:"json",
		success:function(data){
			if(data.roleCode == "exist"){
				$("#roleCodeError").html("该编码不能使用！！！");
			}else{
				$("#roleCodeError").html("该编码可以使用！！！");
			}
		},
		error:function(data){
			$("#roleCodeError").html("查询出错");
		}
	});
});
});