$(function(){
var userRole = $("#userRole");
var roleId=$("#roleId").val();
var path = $("#path").val();

	$.ajax({
		type:"GET",
		url:path+"/user/roleList.json",
		data:{},
		dataType:"json",
		success:function(data){
			if(data!=null){
				var options="<option value=0>--请选择--</option>";
				for(var i=0;i<data.length;i++){
					if(roleId!=null && roleId==data[i].id){
						options +="<option value=\""+data[i].id+"\" selected=\"selected\">"+data[i].roleName+"</option>";
					}else{
						options +="<option value=\""+data[i].id+"\">"+data[i].roleName+"</option>";
					}
					
				}
				userRole.html(options);
			}
		},
		error:function(data){
			var font1=$("#font1");
			font1.html("出错了");
		}
	});
});