<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 民主管理 <span class="c-gray en">&gt;</span> 管理填报 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="mt-20">
	<form action="IDemocraticManage/findAll_guanlitianbao">
<!-- 	<div class="text-c">
		变更时间：<input type="date" id="biangengshijian" name="biangengshijian" readonly="readonly" class="input-text">
		<button name="" id="findBtn" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜民事</button>
		<div style="height:15px" id="idError"></div>
	</div> -->
	<div class="text-c">
		变更时间：<input type="date" id="biangengshijian" readonly="readonly" name="biangengshijian" style="width:250px" class="input-text">
		<button name="" id="findBtn" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜民事</button>
		<div style="height:15px" id="idError"></div>
	</div>
	</form>
			<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">			
					<th width="100">填报年度</th>
					<th width="80">人事变更</th>
					<th width="80">村委自治章程</th>
					<th width="80">姓名</th>
					<th width="80">原因</th>
					<th width="120">职位</th>
					<th width="120">变更时间</th>
					<th width="120">所在村</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${idme}" var="list" >
				<tr class="text-c">	
					<td>${list.demoManager.tianbaoniandu}</td>
					<td>${list.demoManager.renshibiangeng }</td>
					<td>${list.demoManager.cunweizizhizhangcheng}</td>
					<td>${list.name}</td>
					<td>${list.demoManager.reason}</td>
					<td>${list.demoManager.position}</td>
					<td>${list.demoManager.biangengshijian}</td>
					<td>${list.villageinfoName}</td>
					<td class="f-14 td-manage">

<a style="text-decoration:none" class="ml-5" onClick="article_edit('修改民事信息','ForwardManager/forward_update?id=${list.demoManager.id}')" href="javascript:;" title="编辑">
<i class="Hui-iconfont">&#xe6df;</i></a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${idme.size()!=0}">
		共${idme.get(0).countrow}条记录
		共${idme.get(0).countpage}页 
		<a href="IDemocraticManage/findAll_guanlitianbao?thispage=${idme.get(0).firstpage}&biangeng=${biangeng}">首页</a>
		<a href="IDemocraticManage/findAll_guanlitianbao?thispage=${idme.get(0).prepage}&biangeng=${biangeng}">上一页</a>
		<a href="IDemocraticManage/findAll_guanlitianbao?thispage=${idme.get(0).nextpage}&biangeng=${biangeng}">下一页</a>
		<a href="IDemocraticManage/findAll_guanlitianbao?thispage=${idme.get(0).lastpage}&biangeng=${biangeng}">尾页</a>
		</c:if>
	</div>
</div>
 
<script type="text/javascript" src="jsp/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="jsp/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="jsp/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="jsp/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui.admin/js/H-ui.admin.js"></script>
<script src="jsp/laydate/laydate.js"></script>
<script type="text/javascript">
	laydate({
	   elem: '#biangengshijian'
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
</script> 
</body>
</html>