<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'frame.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <frameset rows="15%,*,15%" frameborder="yes" framespacing="0">
  	<frame src="jsp/commons/head.jsp"  noresize="noresize" />
  	<frameset cols="18%,*">
  		<frame src="jsp/commons/left.jsp" noresize="noresize" />
  		<frame src="jsp/commons/right.jsp" name="right" noresize="noresize" />
  	</frameset>
  	<frame src="jsp/commons/foot.jsp" noresize="noresize" />
  </frameset>
</html>
