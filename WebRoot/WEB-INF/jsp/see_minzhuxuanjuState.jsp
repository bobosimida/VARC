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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 民主选举 <span class="c-gray en">&gt;</span> 处理民主选举申请 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="mt-20">
		<h1 >第<span id="jieshu">${maxJieShu }</span>届民主选举进度</h1>
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">村庄名</th>
					<th width="80">申请选举村委会人数</th>
					<th width="80">填报申请年度</th>
					<th width="75">选举进度</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td style="height:15px">${userExtension.villageName}</td>
					<td>${minZhuXuanJu.cunweihuirenshu}</td>
					<td>${tianbaoniandu}</td>
					<td id="shenqingzhuangtai">${minZhuXuanJu.shenqingzhuangtai}</td>
				</tr>
			</tbody>
			<span>${minzhuxuanjuErr}</span>
		</table>
	</div>
</div>
<br>
<div class="page-container">
	<div class="mt-20">
		<h3>本人投票状态</h3>
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">届数</th>
					<th width="75">投票人</th>
					<th width="80">被投票人</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td style="height:15px" id="jieshu__">${maxJieShu }</td>
					<td>${userExtension.user.name}</td>
					<td>${touPiaoExtension.beVoteUserName}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<br>
<div class="page-container">
	<div class="mt-20">
		<h3>第<span id="jieshu_">${maxJieShu }</span>届民主选举候选人票数</h3>
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					
					<th width="75" >候选人名字</th>
					<th width="80">候选人票数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${houXuanRenExtensions}" var="bean">
					<tr class="text-c">
						<td style="height:15px">${bean.name}</td>
						<td>${bean.houXuanRen.piaoshu}</td>
					</tr>
				</c:forEach>
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
		$("#shenqingzhuangtai").html("民主选举投票中...");
	} else if($("#shenqingzhuangtai").html()==2) {
		$("#shenqingzhuangtai").html("该次民主选举申请不通过，无效的民主选举！！！");
		var jieshu=$("#jieshu").html();
		$("#jieshu").html(jieshu-1);
		$("#jieshu_").html(jieshu-1);
		$("#jieshu__").html(jieshu-1);
	} else if($("#shenqingzhuangtai").html()==3) {
		$("#shenqingzhuangtai").html("民主选举结果等待中...");
	} else if($("#shenqingzhuangtai").html()==4) {
		$("#shenqingzhuangtai").html("该次民主选举结束");
		var jieshu=$("#jieshu").html();
		$("#jieshu").html(jieshu-1);
		$("#jieshu_").html(jieshu-1);
		$("#jieshu__").html(jieshu-1);
	} else if($("#shenqingzhuangtai").html()==5) {
		$("#shenqingzhuangtai").html("该次民主选举结束，无效的民主选举!！！");
		var jieshu=$("#jieshu").html();
		$("#jieshu").html(jieshu-1);
		$("#jieshu_").html(jieshu-1);
		$("#jieshu__").html(jieshu-1);
	}
});
</script> 
</body>
</html>