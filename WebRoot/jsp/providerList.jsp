<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<title>My JSP 'providerList.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="bootstrap.min.css">


</head>

<body>
	<div align="center">
		<form method="post"
			action="${pageContext.request.contextPath}/provider/sys/providerList.html">
			供应商编码： <input name="proCode" type="text" value="${proCode}" /> 供应商名称：
			<input name="proName" type="text" value="${proName}" /> <input
				value="查 询" type="submit" /><input value="重 置" type="reset" /> <a
				href="${pageContext.request.contextPath }/provider/sys/add.html">添加供应商</a>
		</form>
	</div>
	
	<div align="center">
	<table cellpadding="0" cellspacing="0">
		<tr>
			<th width="10%">供应商编码</th>
			<th width="20%">供应商名称</th>
			<th width="10%">联系人</th>
			<th width="10%">联系电话</th>
			<th width="10%">传真</th>
			
			<th width="30%" colspan="3">操作</th>
		</tr>
		<c:forEach var="provider" items="${providerList}" varStatus="status">
			<tr>
				<td>${provider.proCode}</td>
				<td>${provider.proName }</td>
				<td>${provider.proContact}</td>
				<td>${provider.proPhone}</td>
				<td>${provider.proFax}</td>
				
				<td><a href="${pageContext.request.contextPath }">查看</a>
				 <a href="${pageContext.request.contextPath }">修改</a>
				 <a href="${pageContext.request.contextPath }">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<div align="center">
    		<p>
    			共${totalCount}条记录，${currentPageNo}/${totalPageCount}页
    			<a href="${pageContext.request.contextPath}/provider/sys/providerList.html?currentPageNo=1">首页</a>
    			<a href="${pageContext.request.contextPath}/provider/sys/providerList.html?currentPageNo=${currentPageNo-1}">上一页</a>
    			<a href="${pageContext.request.contextPath}/provider/sys/providerList.html?currentPageNo=${currentPageNo+1}">下一页</a>
    			<a href="${pageContext.request.contextPath}/provider/sys/providerList.html?currentPageNo=${totalPageCount}">尾页</a>
    		</p>
    	</div>
</body>
</html>
