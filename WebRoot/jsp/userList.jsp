<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 	<div align="center">
 		<form action="${pageContext.request.contextPath}/user/sys/userList.html" method="post">
 			用户名：<input type="text" name="userName" value="${sessionScope.userName}" />
 			用户角色：<select name="userRole">
 				<c:if test="${roleList!=null}">
 					<option value="0">--请选择--</option>
 					<c:forEach var="role" items="${roleList}">
 						<option <c:if test="${userRole==role.id}">selected="selected"</c:if> value="${role.id}" >${role.roleName}</option>
 					</c:forEach>
 				</c:if>
 			</select>
 			<input type="submit" value="搜索"/><input type="reset" value="重置"/>
 		</form>
 	</div>
 	<a href="${pageContext.request.contextPath}/user/sys/addUser.html">添加用户</a>
    <div align="center">
    	<table width="100%">
    		<tr>
    			<th>用户编码</th>
    			<th>用户名称</th>
    			<th>用户性别</th>
    			<th>手机</th>
    			<th>地址</th>
    			<th>用户角色</th>
    			<th colspan="3">操作</th>
    		</tr>
    		<c:forEach var="user" items="${userList}">
    			<tr>
    				<td>${user.userCode}</td>
					<td>${user.userName}</td>
					<td>
					<c:if test="${user.gender==1}">男</c:if>
					<c:if test="${user.gender==2}">女</c:if>
					</td>
					<td>${user.phone}</td>
					<td>${user.address}</td>
					<td>${user.userRoleName}</td>
					<td><a href="${pageContext.request.contextPath}/user/sys/view/${user.id}">查看</a></td>
					<td><a href="${pageContext.request.contextPath}/user/sys/update/${user.id}">修改</a></td>
					<td><a href="${pageContext.request.contextPath}/user/sys/delete/${user.id}">删除</a></td>
    			</tr>
    		</c:forEach>
    	</table>
    	
    	<div align="center">
    		<p>
    			共${totalCount}条记录，${currentPageNo}/${totalPageCount}页
    			<a href="${pageContext.request.contextPath}/user/sys/userList.html?currentPageNo=1">首页</a>
    			<a href="${pageContext.request.contextPath}/user/sys/userList.html?currentPageNo=${currentPageNo-1}">上一页</a>
    			<a href="${pageContext.request.contextPath}/user/sys/userList.html?currentPageNo=${currentPageNo+1}">下一页</a>
    			<a href="${pageContext.request.contextPath}/user/sys/userList.html?currentPageNo=${totalPageCount}">尾页</a>
    		</p>
    	</div>
    </div>
  </body>
</html>
