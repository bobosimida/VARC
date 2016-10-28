<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 基本信息管理 <span class="c-gray en">&gt;</span> 村委会成员个人信息 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">届数</th>
					<th width="75">性别</th>
					<th width="60">年龄</th>
					<th width="80">电话</th>
					<th width="120">邮箱</th>
					<th width="80">任职时间</th>
					<th width="80">卸任时间</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					
					<td>${cunWeiHui.jieshu}</td>
						<td>${user.sex}</td>
					<td>${user.age}</td>
					<td title="">${user.tel}</td>
					<td>${user.email}</td>
					<td>${cunWeiHui.starttime}</td>
					<td id="overtime">${cunWeiHui.overtime}</td>
				</tr>
			</tbody>
		</table>
		<br>
		
				<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					
					<th width="80">选举信息</th>
					<th width="75">填报年度</th>
					<th width="80">选举日</th>
					<th width="80">选举进度</th>
					<th width="80">实施方案</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					
					<td>选举信息</td>
					<td class="minzhuxuanju" id="minzhuxuanju">${minZhuXuanJu.tianbaoniandu}</td>
					<td class="minzhuxuanju">${minZhuXuanJu.xuanjuri}</td>
					<td class="minzhuxuanju">${minZhuXuanJu.xuanjujindu}</td>
					<td class="minzhuxuanju">${minZhuXuanJu.shishifangan}</td>
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
	if($("#overtime").html()==""){
		$("#overtime").html("至今");
	}
	if($("#minzhuxuanju").html()==""){
		$(".minzhuxuanju").html("该村委会成员无选举信息");
	}
});
</script> 
</body>
</html>