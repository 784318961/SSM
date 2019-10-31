<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'update.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/updateUser.js"></script>
<body>
	<div>
		<form
			action="${pageContext.request.contextPath}/user/sys/updateUserSave.html"
			method="post" enctype="multipart/form-data">
			
			<input type="hidden" name="id" value="${u.id}" />
			
			<p>
				用户编码: <input type="text" name="userCode" value="${u.userCode}" /> <font></font>

			</p>
			<p>
				用户名称: <input type="text" name="userName" value="${u.userName}" />

			</p>
			<p>
				性别： <input type="radio" name="gender"
					<c:if test="${u.gender==1}">checked="checked"</c:if> value="1" />
				男 <input type="radio" name="gender"
					<c:if test="${u.gender==2}">checked="checked"</c:if> value="2" />
				女
			</p>
			<p>
				出生日期: 
				<input type="text" Class="Wdate" id="birthday" name="birthday" value="${u.birthday}"
					readonly="readonly" onclick="WdatePicker();">
			</p>
			<p>
				电话: <input type="text" name="phone" value="${u.phone}" />

			</p>
			<p>
				地址: <input type="text" name="address" value="${u.address}" />

			</p>
			<p>
				<input type="hidden" id="path" value="${pageContext.request.contextPath}"/>
				<input type="hidden" id="roleId" value="${u.userRole}"/>
				用户角色: <select name="userRole" id="userRole">
						
					<%-- <c:forEach var="role" items="${roleList}">
						<option <c:if test="${role.id==3}">selected="selected"</c:if>
							value="${role.id}">${role.roleName}</option>
					</c:forEach> --%>
				</select>
				<font id="font1"></font>
			</p>
			<p>
				证件照:<input type="file" name="file" />
			</p>
			<p>
				工作证照片:<input type="file" name="file" />
			</p>
			<p>
				<input type="submit" value="确定" /> <input type="reset" value="重置" />
			</p>
		</form>
	</div>
</body>
</html>
