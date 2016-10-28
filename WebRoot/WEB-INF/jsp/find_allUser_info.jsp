<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="jsp/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="jsp/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title></title>
</head>
<body>
<nav class="breadcrumb"><a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container">
<div class="cl pd-5 bg-1 bk-gray mt-20"> 
		<span class="r">共有<strong>${count}</strong>个村民</span> 
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					
					<th width="80">帐号</th>
					<th width="80">姓名</th>
					<th width="80">单位</th>
					<th width="80">电话</th>
					<th width="120">邮箱</th>
					<th width="75">性别</th>
					<th width="60">年龄</th>
					<th width="60">帐号角色</th>
					<th width="60">所属村庄</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="user" items="${users}">
				<tr class="text-c">
					<td id="_account">${user.user.account}</td>
					<td>${user.user.name }</td>
					<td>${user.user.unit}</td>
					<td>${user.user.tel}</td>
					<td title="${user.user.email}">${user.user.email}</td>
					<td>${user.user.sex}</td>
					<td>${user.user.age}</td>
					<td>${user.rollName}</td>
					<td class="td-status">${user.villageName}</td>
					<td class="f-14 td-manage">
					<i style="cursor:pointer" title="重置密码" class="Hui-iconfont" onClick="resetPwd('${user.user.account}')">&#xe63f;</i>
					&nbsp;&nbsp;&nbsp;
					<a style="cursor:pointer" title="删除用户" href="basicInfoControl/delete_user?account=${user.user.account}&page=${page}&id=${user.user.villageinfoId}" class="Hui-iconfont" onClick="return deleteUser()">&#xe6e2;</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	<div style="float:right;margin-top:20px;">
	<span class="l">
	<a href="basicInfoControl/find_allUser_info?page=${page-1}&id=${id}" onClick="return last()"
	  class="btn btn-danger radius">
	上一页</a>&nbsp;
	<span>
	<c:set var="p" value="${pageCount}"/>
	<c:forEach var="num" begin="1" end="${p}" step="1">
		<c:if test="${ num==p}">
			<a href="basicInfoControl/find_allUser_info?page=${num}&id=${id}">	${num}</a>
		</c:if>
		<c:if test="${ num<p}">
			<a href="basicInfoControl/find_allUser_info?page=${num}&id=${id}">	${num}</a>
			、
		</c:if>
	</c:forEach> 
	</span>
	<a href="basicInfoControl/find_allUser_info?page=${page+1}&id=${id}" onClick="return next()"
		class="btn btn-danger radius">
	&nbsp;下一页</a>
	</span>
	</div>
	</div>
</div>
<input type="hidden" value="${page}" id="page">
<input type="hidden" value="${count}" id="count">
<script type="text/javascript" src="jsp/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="jsp/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="jsp/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="jsp/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript">
function deleteUser(){
	var con=window.confirm("是否删除该用户");
	return con;
}
function resetPwd(date){
	var con=window.confirm("是否重置该密码");
	if(con){
		$.post("basicInfoControl/reset_pwd",{account:date},function(){
		
		});
		alert("重置成功");
	}
}
/*资讯-编辑*/
function article_edit(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
function last(){
	var page=$("#page").val();
	if(page==1){
		alert("已经是第一页了");
		return false;
	}
	return true;
}
function next(){
	var count=$("#count").val();
	var page=$("#page").val();
	if(Math.ceil(count/8)<=page){
			alert("已经是最后一页了");
			return false;
	}
	return true;
}

</script> 
</body>
</html>