<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
<base href="<%=basePath%>"><meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="jsp/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="jsp/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui.admin/css/style.css" />
<title>资讯列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 基本信息管理 <span class="c-gray en">&gt;</span> 查找村子 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 村庄id：
		<input type="text" name="" id="vId" placeholder=" 村庄id" style="width:250px" class="input-text">
		<button name="" id="findBtn" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜村庄</button>
		<div style="height:15px" id="idError"></div>
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">村庄ID</th>
					<th width="80">村庄名称</th>
					<th width="80">省</th>
					<th width="80">市</th>
					<th width="120">区</th>
					<th width="75">乡</th>
					<th width="120">查看详情</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td id="_id" style="height:20px"></td>
					<td id="_name"></td>
					<td id="_sheng"></td>
					<td id="_shi"></td>
					<td id="_qu"></td>
					<td id="_xiang"></td>
					<td class="f-14 td-manage" id="_op">
					
					</td>
				</tr>
			</tbody>
		</table>
		
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
		<span class="r">共有<strong>${count}</strong>个村庄</span> 
	</div>
	<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">村庄ID</th>
					<th width="80">村庄名称</th>
					<th width="80">省</th>
					<th width="80">市</th>
					<th width="120">区</th>
					<th width="75">乡</th>
					<th width="120">查看详情</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="v" items="${vil}">
				<tr class="text-c">
					<td  style="height:15px">${v.id}</td>
					<td  style="cursor:pointer">${v.name}</td>
					<td >${v.sheng}</td>
					<td >${v.shi}</td>
					<td >${v.qu}</td>
					<td >${v.xiang}</td>
					<td >
					<a style="text-decoration:none" class="ml-5"  onClick="article_edit('村庄用户','basicInfoControl/find_allUser_info?id=${v.id}')" href="javascript:;" title="村庄用户"> 
					<i class="Hui-iconfont">&#xe60d;</i></a>
					&nbsp;&nbsp;&nbsp;
					
					<a style="text-decoration:none" class="ml-5"  onClick="article_edit('查看村庄信息','basicInfoControl/find_countrysideinfo_byId?id=${v.id}')" href="javascript:;" title="编辑"> 
					<i class="Hui-iconfont">&#xe6df;</i></a>
					
					&nbsp;&nbsp;&nbsp;
					<a style="text-decoration:none" class="ml-5"  onClick="article_edit('添加村民','forward/forward_add_user?villageinfoId=${v.id}')" href="javascript:;" title="添加村民"> 
					<i class="Hui-iconfont">&#xe604;</i></a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	<div style="float:right;margin-top:20px;">
	<span class="l">
	<a href="basicInfoControl/forward_find_countrysides_info?page=${page-1}" onClick="return last()"
	  class="btn btn-danger radius">
	上一页</a>&nbsp;
	<span>
	<c:set var="p" value="${pageCount}"/>
	<c:forEach var="num" begin="1" end="${p}" step="1">
		<c:if test="${ num==p}">
			<a href="basicInfoControl/forward_find_countrysides_info?page=${num}">	${num}</a>
		</c:if>
		<c:if test="${ num<p}">
			<a href="basicInfoControl/forward_find_countrysides_info?page=${num}">	${num}</a>
			、
		</c:if>
	</c:forEach> 
	</span>
	<a href="basicInfoControl/forward_find_countrysides_info?page=${page+1}" onClick="return next()"
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
/*资讯-编辑*/
function article_edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
$(document).ready(function(){
	var findBtn=$("#findBtn");
	findBtn.click(function(){
		$.post("basicInfoControl/find_countrysides_info",{id:$("#vId").val()},function(data){
		
		 var vil= eval("(" + data + ")");
		 if(vil.villageinfo==undefined){
			$("#idError").html("该村庄不存在或输入有误");	 
			$("#_id").html(""); 
			$("#_name").html("");
			$("#_sheng").html(""); 
			$("#_shi").html(""); 
			$("#_qu").html(""); 
			$("#_xiang").html("");
			$("#_op").html("");		
		 }else{
		 	$("#idError").html("");	
		    $("#_id").html(vil.villageinfo.id); 
			$("#_name").html(vil.villageinfo.name);
			$("#_sheng").html(vil.villageinfo.sheng); 
			$("#_shi").html(vil.villageinfo.shi); 
			$("#_qu").html(vil.villageinfo.qu); 
			$("#_xiang").html(vil.villageinfo.xiang);
			$("#_op").html("<a style=\"text-decoration:none\" class=\"ml-5\" " +
			"onClick=\"article_edit('村庄用户','basicInfoControl/find_allUser_info?id="+vil.villageinfo.id+"')\""+
			" href=\"javascript:;\" title=\"编辑\"> " +"<i class=\"Hui-iconfont\">&#xe60d;</i></a> &nbsp;&nbsp;&nbsp;"+
			"<a style=\"text-decoration:none\" class=\"ml-5\" " +
			"onClick=\"article_edit('查看村庄信息','basicInfoControl/find_countrysideinfo_byId?id="+vil.villageinfo.id+"')\" "+
			" href=\"javascript:;\" title=\"编辑\"> " +"<i class=\"Hui-iconfont\">&#xe6df;</i></a>&nbsp;&nbsp;&nbsp;"+
			"<a style=\"text-decoration:none\" class=\"ml-5\" " +
			"onClick=\"article_edit('添加村民','forward/forward_add_user?villageinfoId="+vil.villageinfo.id+"')\" "+
			" href=\"javascript:;\" title=\"编辑\"> " +"<i class=\"Hui-iconfont\">&#xe604;</i></a>");	
		}
			
		});
	});
});
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
	if(Math.ceil(count/6)<=page){
			alert("已经是最后一页了");
			return false;
	}
	return true;
}
</script> 
</body>
</html>