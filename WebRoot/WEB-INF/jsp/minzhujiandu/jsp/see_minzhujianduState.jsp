<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页  <span class="c-gray en">&gt;</span> 民主监督<span class="c-gray en">&gt;</span> 查看民主监督<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="ˢ��" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="mt-20">
		<form action="democraticSuperviseControl/see_minzhujianduState">
		<div class="text-c">
		<c:if test="${rollId==3 }">
			村庄id：
			<input type="text" name="id" id="vId" placeholder=" 村庄id" style="width:250px" class="input-text">
			</c:if>
			填报年度:
			<input type="text" name="date" id="vYear" placeholder="填报年度" style="width:250px" class="input-text">
			<button name="" id="findBtn" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜监督信息</button>
			<div style="height:15px;margin-left:-400px;" id="idError">${errorId}</div>
		</div>
	</form>
	&nbsp;&nbsp;&nbsp;
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="75">填报年度</th>
					<th width="75">公开时间</th>
					<th width="75">财务公开</th>
					<th width="75">村务公开</th>
					<th width="75">政务公开</th>
					<th width="75">公开形式</th>
					<th width="75">村委会成员被评议次数</th>
					<th width="75">村管理人员误工补贴次数</th>
					<th width="75">所属村</th>
					<c:if test="${rollId==3}">
					<th width="75">操作</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${minzhujiandus}" var="minzhujiandu" >
				<tr class="text-c">
					<td id="_year">${minzhujiandu.minZhuJianDu.tianbaoniandu}</td>
					<td id="_time" >${minzhujiandu.minZhuJianDu.gongkaishijian}</td>
					<td id="_caiwu">${minzhujiandu.minZhuJianDu.caiwu}</td>
					<td id="_cunwu">${minzhujiandu.minZhuJianDu.cunwu}</td>
					<td id="_zhengwu">${minzhujiandu.minZhuJianDu.zhengwu}</td>
					<td id="_gongkaixingshi">${minzhujiandu.minZhuJianDu.gongkaixingshi}</td>
					<td id="_cwjdcybpy">${minzhujiandu.minZhuJianDu.cwjdcybpy}</td>
					<td id="_cglrywgbt">${minzhujiandu.minZhuJianDu.cglrywgbt}</td>
					<td id="_villageinfoname" style="cursor:pointer">${minzhujiandu.name}</td>
					<c:if test="${rollId==3}">
					<td class="f-14 td-manage">
<a style="text-decoration:none" class="ml-5" onClick="article_edit('修改监督信息','forwardSupervise/forward_update?id=${minzhujiandu.minZhuJianDu.id}')" href="javascript:;" title="编辑">
<i class="Hui-iconfont">&#xe6df;</i></a>
					</td>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>

		</table>
<div style="float:right;margin-top:20px;">
	<span class="l">
	<a href="democraticSuperviseControl/see_minzhujianduState?page=${page-1}&id=${id}&date=${date}" onClick="return last()"
	  class="btn btn-danger radius">
	上一页</a>&nbsp;
	<span>
	<c:set var="p" value="${pageCount}"/>
	<c:forEach var="num" begin="1" end="${p}" step="1">
		<c:if test="${ num==p}">
			<a href="democraticSuperviseControl/see_minzhujianduState?page=${num}&id=${id}&date=${date}">	${num}</a>
		</c:if>
		<c:if test="${ num<p}">
			<a href="democraticSuperviseControl/see_minzhujianduState?page=${num}&id=${id}&date=${date}">	${num}</a>
			、
		</c:if>
	</c:forEach> 
	</span>
	<a href="democraticSuperviseControl/see_minzhujianduState?page=${page+1}&id=${id}&date=${date}" onClick="return next()"
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
<script src="jsp/laydate/laydate.js"></script>
<script type="text/javascript">
	laydate({
	   elem: '#vYear'
	});
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