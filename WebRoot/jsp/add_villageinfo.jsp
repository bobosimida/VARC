<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8"><base href="<%=basePath%>">
<link href="jsp/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
<link href="jsp/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="jsp/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<title></title>
</head>
<body>
<section class="container-fluid page-404 minWP text-c">
  <p class="error-title"><i class="Hui-iconfont va-m" style="font-size:80px">&#xe668;</i>
  <span class="va-m"> 添加成功,村庄id为<strong style="color:red">${villageinfoId}</strong></span>
  </p>
</section>
</body>
</html>