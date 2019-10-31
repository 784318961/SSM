$(function() {

	var userCode = $("#userCode");
	var userRole = $("#userRole");
	var path = $("#path").val();

	$.ajax({
		type : "GET",
		url : path + "/user/roleList.json",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				var options = "<option value=0>--请选择--</option>";
				for ( var i = 0; i < data.length; i++) {

					options += "<option value=\"" + data[i].id + "\">"
							+ data[i].roleName + "</option>";

				}
				userRole.html(options);
			}
		},
		error : function(data) {
			var font1 = $("#font1");
			font1.html("出错了");
		}
	});

	userCode.bind("blur", function() {
		alert("cc");
		$.ajax({
			type : "GET",
			url : path + "/user/userCode.json",

			data : {
				userCode : userCode.val()
			},
			dataType : "json",
			success : function(resultMap) {
				if (resultMap.userCode == "exist") {
					$("#font").html("账号不能使用！！！");
				} else {
					$("#font").html("可以使用！");
				}
			},
			error : function(resultMap) {
				$("#font").html("查询失败");
			}
		});
	});

})