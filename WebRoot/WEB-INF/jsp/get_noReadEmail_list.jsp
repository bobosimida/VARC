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
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
		<span class="r">共有<strong>${emailNum }</strong>封邮件</span> </div>
		<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="9">邮件列表</th>
			</tr>
			<tr class="text-c">
				<th width="80">发件人帐号</th>
				<th width="50">发件人姓名</th>
				<th width="300">邮件标题</th>
				<th width="130">发送时间</th>
				<th width="50">是否已读</th>
				<th width="50">操作</th>
			</tr>
		</thead>
		<tbody id="wdinfo">
			<c:forEach var="email" items="${readEmails}">
				<tr class="text-c">
					<td>${email.user.account}</td>
					<td>${email.user.name}</td>
					<td style="text-align:left" class="checkmail">
					<i class="Hui-iconfont">&#xe616;</i>
					<a class="title"
						onClick="article_edit('查看邮件内容','workAndMessageControl/look_email?id=${email.email.id}&receiveAccount=${email.user.account}')" 
						
						href="javascript:;">${email.email.title}</a>
					</td>
					<td >${email.email.sendTime}</td>
					<td class="isread">${email.email.isread}</td>
					<td class="td-manage">
						<a title="删除" href="workAndMessageControl/get_readEmail_list?emailId=${email.email.id}&page=${page}" onClick="return deleteConfirm()" class="ml-5" style="text-decoration:none">
						<i class="Hui-iconfont">&#xe6e2;</i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="float:right;margin-top:20px;">
	<span class="l">
	<a href="workAndMessageControl/get_readEmail_list?page=${page-1}" onClick="return last()"
	  class="btn btn-danger radius">
	上一页</a>&nbsp;
	<span>
	<c:set var="p" value="${pageCount}"/>
	<c:forEach var="num" begin="1" end="${p}" step="1">
		<c:if test="${ num==p}">
			<a href="workAndMessageControl/get_readEmail_list?page=${num}">	${num}</a>
		</c:if>
		<c:if test="${ num<p}">
			<a href="workAndMessageControl/get_readEmail_list?page=${num}">	${num}</a>
			、
		</c:if>
	</c:forEach> 
	</span>
	<a href="workAndMessageControl/get_readEmail_list?page=${page+1}" onClick="return next()"
		class="btn btn-danger radius">
	&nbsp;下一页</a>
	</span>
	</div>
</div>
<input type="hidden" value="${page}" id="page">
<input type="hidden" value="${emailNum}" id="emailNum">
<script type="text/javascript" src="jsp/lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="jsp/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="jsp/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="jsp/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript">
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
	var emailNum=$("#emailNum").val();
	var page=$("#page").val();
	if(Math.ceil(emailNum/8)<=page){
			alert("已经是最后一页了");
			return false;
	}
	return true;
}
function deleteConfirm(){
	var con=window.confirm("确认删除该邮件？");
	return con;
}
$(document).ready(function(){
	for(i=0;i<8;i++){
		if($(".isread:eq(+"+i+")").html()==0){
			$(".isread:eq(+"+i+")").html("<strong><span style='color:red'>未读<span></strong>");
		}else{
			$(".isread:eq(+"+i+")").html("已读");
		}
	}
});
$("#wdinfo tr .checkmail").click(function(){
	$.post("workAndMessageControl/get_email_count",{},function(data){
		var num=eval("("+data+")");
		if(num.count!=0){
			parent.setnoread(num.count);
		}else{
			parent.setnoread("");
		}
	});
	
});
</script>
</body>
</html>