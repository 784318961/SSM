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
    
    <title>My JSP 'roleList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.12.4.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/roleList.js"></script>
  </head>
  
  <body>
    <div>
    <a href="${pageContext.request.contextPath}/role/sys/addRole.html">添加用户</a>
    	<table>
    		<tr>
    			<th>角色ID</th>
    			<th>角色编码</th>
    			<th>角色名称</th>
    			<th>创建人</th>
    			<th>创建时间</th>
    			<th>更改人</th>
    			<th>更改时间</th>
    			<th colspan="2">操作</th>
    		</tr>
    		<c:forEach var="role" items="${roleList}">
    			<tr>
    				<td>${role.id}</td>
    				<td>${role.roleCode}</td>
    				<td>${role.roleName}</td>
    				<td>${role.createdBy}</td>
    				<td>${role.creationDate}</td>
    				<td>${role.modifyBy}</td>
    				<td>${role.modifyDate}</td>
    				<td><a href="${pageContext.request.contextPath}/role/sys/update/${role.id}">修改</a></td>
    				<td><a href="${pageContext.request.contextPath}/role/sys/delete/${role.id}">删除</a></td>
    			</tr>
    		</c:forEach>
    	</table>
    </div>
    
   
  </body>
</html>
