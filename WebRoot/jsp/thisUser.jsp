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
    
    <title>My JSP 'thisUser.jsp' starting page</title>
    
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
  	<div>
  		<ul>
  			<li>用户编码:${u.userCode}</li>
  			<li>用户名称:${u.userName}</li>
  			<li>出生日期:${u.birthday}</li>
  			<li>性别:<c:if test="${u.gender==1}">男</c:if>
					<c:if test="${u.gender==2}">女</c:if>
			</li>
  			<li>电话:${u.phone}</li>
  			<li>地址:${u.address}</li>
  			<li>用户角色:${u.userRoleName}</li>
  			<li>创建者:${u.createdBy}</li>
  			<li>创建时间:${u.creationDate}</li>
  			<li>更新者:${u.modifyBy}</li>
  			<li>更新时间:${u.modifyDate}</li>
  			<li>证件照:
  				<img src="${u.idPicPath}">
  			</li>
  			<li>工作证照片:
  				<img src="${u.workPicPath}">
  			</li>
  		</ul>
  	</div>
  </body>
</html>
