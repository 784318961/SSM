$(function() {
	
		var add = $("#add");
		var update = $(".update");
		var path=$("#path");
		add.bind("click", function(){
			$("#addsave").css("display", "block");
			$("#updatesave").css("display", "none");
		});

		update.click(function(){
			$("#updatesave").css("display", "block");
			$("#addsave").css("display", "none");

			var obj=$(this);
			$.ajax({
				type:"GET",
				url:path+"/role/roles.json",
				data:{id:obj.attr("roleId")},
				dataType:"json",
				success:function(result){
					alert("成功了");
						$("#userCode").val(result.userCode);
						$("#userName").val(result.userName);
				},
				error:function(result){
					alert("error!");
				}
				
			});
			
		});

	
})