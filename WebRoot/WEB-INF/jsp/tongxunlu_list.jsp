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
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
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
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title></title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 工作与信息交流 <span class="c-gray en">&gt;</span>邮箱<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container">
<div class="text-c"> 用户帐号：
		<input type="text" name="" id="oAccount" placeholder="用户帐号" style="width:250px" class="input-text">
		<a  id="findBtn" class="btn btn-success"  href="javascript:;" 
			onClick="findOAccount()">
			<i class="Hui-iconfont">&#xe665;</i>添加联系人
		</a>
		<div style="height:15px" id="idError">${otherIdError}${otherIdNumError}</div>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
		<a href="workAndMessageControl/tongxunlu_export" onclick="return check()" class="btn btn-danger radius">
		<i class="Hui-iconfont">&#xe640;</i> 导出所有联系人</a> 
		</span> 
		<span class="r">共有<strong>${tongxunluNum}</strong>个联系人</span> </div>
		<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="20">联系人列表</th>
			</tr>
				<tr class="text-c">
					
					<th width="80">帐号</th>
					<th width="80">姓名</th>
					<th width="80">单位</th>
					<th width="80">电话</th>
					<th width="120">邮箱</th>
					<th width="75">性别</th>
					<th width="60">年龄</th>
					<th width="60">所属村庄</th>
					<th width="120">操作</th>
				</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}">
				<tr class="text-c">
					<td>${user.user.account}</td>
					<td>${user.user.name}</td>
					<td>${user.user.unit}</td>
					<td>${user.user.tel}</td>
					<td>${user.user.email}</td>
					<td>${user.user.sex}</td>
					<td>${user.user.age}</td>
					<td>${user.villageName}</td>
						<td class="td-manage">
						<a title="删除" href="workAndMessageControl/tongxunlu_list?deleteotherId=${user.user.id}&page=${page}" onClick="return deleteConfirm()" class="ml-5" style="text-decoration:none">
						<i class="Hui-iconfont">&#xe6e2;</i></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a title="发送邮件" href="forwardWAM/forward_send_email?receiveAccount=${user.user.account}"><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="float:right;margin-top:20px;">
	<span class="l">
	<a href="workAndMessageControl/tongxunlu_list?page=${page-1}" onClick="return last()"
	  class="btn btn-danger radius">
	上一页</a>&nbsp;
	<span>
	<c:set var="p" value="${pageCount}"/>
	<c:forEach var="num" begin="1" end="${p}" step="1">
		<c:if test="${ num==p}">
			<a href="workAndMessageControl/tongxunlu_list?page=${num}">	${num}</a>
		</c:if>
		<c:if test="${ num<p}">
			<a href="workAndMessageControl/tongxunlu_list?page=${num}">	${num}</a>
			、
		</c:if>
	</c:forEach> 
	</span>
	<a href="workAndMessageControl/tongxunlu_list?page=${page+1}" onClick="return next()"
		class="btn btn-danger radius">
	&nbsp;下一页</a>
	</span>
	</div>
</div>
<input type="hidden" value="${page}" id="page">
<input type="hidden" value="${tongxunluNum}" id="tongxunluNum">
<script type="text/javascript" src="jsp/lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="jsp/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="jsp/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="jsp/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript">
function check(){
	var con=window.confirm("确认导出所有联系人？");
	/* if(con){
		$.post("workAndMessageControl/tongxunlu_export",{},function(data){
			
		});
	} */
	return con;
}
function findOAccount(){
 	$.post("workAndMessageControl/find_user",{otherAccount:$("#oAccount").val()},function(data){
		var id=eval("("+data+")"); 
		if(id.id==undefined){
			alert("该用户不存在");
		}else{
			var con = window.confirm("确认添加该用户为联系人？");
			if(con){
				location.href="workAndMessageControl/tongxunlu_list?page=${page}&addotherId="+id.id;
			}
		}
	});
	return flag;
}
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
	var tongxunluNum=$("#tongxunluNum").val();
	var page=$("#page").val();
	if(Math.ceil(tongxunluNum/7)<=page){
			alert("已经是最后一页了");
			return false;
	}
	return true;
}
function deleteConfirm(){
	var con=window.confirm("确认删除该联系人？");
	return con;
}
</script>
</body>
</html>