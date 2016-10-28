<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	response.sendRedirect(basePath+"forward/index_system");
%>
<!DOCTYPE HTML>
<html>
  <head>
<base href="<%=basePath%>">
<title></title>
</head>
<body>
</body>
</html>