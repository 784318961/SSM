<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateRole.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/updateRole.js"></script>

  </head>
  
  <body>
    <div>
    	<form action="${pageContext.request.contextPath}/role/sys/updateRoleSave.html" method="post">
    		<input type="hidden" name="id" value="${role.id}" />
    		<input type="hidden" id="path" value="${pageContext.request.contextPath}" />
    		<p><input type="text" name="roleCode" value="${role.roleCode}" id="roleCode" />
    			<font id="roleCodeError"></font>
    		</p>
    		<p><input type="text" name="roleName" value="${role.roleName}"  /></p>
    		<p><input type="submit" value="确认"/><input type="reset" value="重置"/></p>
    	</form>
    </div>
  </body>
</html>
