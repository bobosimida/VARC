<%@page import="com.varc.domain.MinZhuXuanJu"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 民主选举 <span class="c-gray en">&gt;</span> 投票 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<form:form class="form form-horizontal" id="form-article-add" method="post" action="democraticElectionControl/minzhuxuanju_submit" onsubmit="return check()">
	<div class="mt-20">
		<h1 align="center" color="yellow"> 请对以下候选人进行投票，投票前请确认是否投选该人</h1><br><br>
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr>
					<td>参选人</td>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${houXuanRenExtensions}" var="bean">
				<tr >
					<td>
						<%-- <form:radiobutton path="userId" value="${bean.houXuanRen.userId }"/>${bean.name } --%>
						<input type="radio" name="userId" value="${bean.houXuanRen.userId }">${bean.name }
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<input type="hidden" name="minzhuxuanjuId" value="${houXuanRenExtensions[0].houXuanRen.minzhuxuanjuId }">
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
			<button  class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 提交</button>
			<button  class="btn btn-default radius" type="reset">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
		</div>
	</div>
	</form:form>
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
		$("#shenqingzhuangtai").html("申请民主选举");
	} else if($("#shenqingzhuangtai").html()==1) {
		$("#shenqingzhuangtai").html("民主选举申请通过");
	} else if($("#shenqingzhuangtai").html()==2) {
		$("#shenqingzhuangtai").html("民主选举申请不通过");
	} else if($("#shenqingzhuangtai").html()==3) {
		$("#shenqingzhuangtai").html("民主选举结束申请");
	} else if($("#shenqingzhuangtai").html()==4) {
		$("#shenqingzhuangtai").html("民主选举结束申请通过");
	} else if($("#shenqingzhuangtai").html()==5) {
		$("#shenqingzhuangtai").html("民主选举结束申请不通过");
	}
});
</script> 
</body>
</html>