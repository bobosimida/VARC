<%@page import="com.varc.domain.MinZhuXuanJu"%>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 民主选举 <span class="c-gray en">&gt;</span> 申请民主选举结束 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="75">村庄编号</th>
					<th width="80">村庄名</th>
					<th width="80">届数</th>
					<th width="60">申请选举状态</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td>${minZhuXuanJu.villageinfoId}</td>
					<td>${villageName}</td>
					<td title="${maxJieShu + 1}">${maxJieShu + 1}</td>
					<td id="shenqingzhuangtai">${minZhuXuanJu.shenqingzhuangtai}</td>
					<%MinZhuXuanJu minZhuXuanJu = (MinZhuXuanJu)request.getAttribute("minZhuXuanJu");
					int zhuangtai = Integer.parseInt(minZhuXuanJu.getShenqingzhuangtai());
					if(zhuangtai == 1){
					%>
					<td class="f-14 td-manage">
<a style="text-decoration:none" class="ml-5" onClick="article_edit('提交民主选举结束申请','democraticElectionControl/applyXuanJuFinish/${minZhuXuanJu.id}')" href="javascript:;" title="编辑">
<i class="Hui-iconfont">&#xe6df;</i></a>
					</td>
					<%} else { %>
						<td>不能进行任何操作</td>
					<%} %>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript" src="jsp/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="jsp/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="jsp/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="jsp/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript">

/*资讯-编辑*/
function article_edit(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
$(document).ready(function(){
	if($("#shenqingzhuangtai").html()==0){
		$("#shenqingzhuangtai").html("民主选举申请中...");
	} else if($("#shenqingzhuangtai").html()==1) {
		$("#shenqingzhuangtai").html("民主选举申请通过");
	} else if($("#shenqingzhuangtai").html()==2) {
		$("#shenqingzhuangtai").html("民主选举申请不通过！！！");
	} else if($("#shenqingzhuangtai").html()==3) {
		$("#shenqingzhuangtai").html("民主选举结束申请中...");
	} else if($("#shenqingzhuangtai").html()==4) {
		$("#shenqingzhuangtai").html("民主选举结束申请通过");
	} else if($("#shenqingzhuangtai").html()==5) {
		$("#shenqingzhuangtai").html("民主选举结束申请不通过！！！");
	}
});
</script> 
</body>
</html>