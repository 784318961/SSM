
$(function(){
var userPassword = $("#userPassword");
var id1=$("#id");
var path = $("#path").val();
userPassword.bind("blur",function(){
	
	$.ajax({
		type:"GET",
		url:path+"/user/ChPwd.json",
		data:{userPassword:userPassword.val(),id:id1.val()},
		dataType:"json",
		success:function(data){
			if(data.userPassword == "exist"){
				$("font").html("原始密码正确！！！");
			}else{
				$("font").html("原始密码错误！");
			}
		},
		error:function(data){
			$("font").html("查询失败！！！");
		}
	});
});
});