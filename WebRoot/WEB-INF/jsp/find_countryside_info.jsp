<%@ page language="java" import="java.util.*,com.varc.domain.extension.CunWeiHuiExtension " pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 基本信息管理 <span class="c-gray en">&gt;</span> 村庄信息 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<form>
<div class="page-container">
	<div class="mt-20">
	<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
				<!-- 村委会成员信息，根据数据库数据动态添加 -->
					<th width="80">村委会成员</th>
					<c:forEach items="${cunWeiHui}" var="cunWeiHui">
					<th width="80">${cunWeiHui.zhiwei}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					
					<td>成员名字</td>
					<% List<CunWeiHuiExtension> cunWeiHui=(List<CunWeiHuiExtension>)request.getAttribute("cunWeiHui");
						for(int i=0;i<cunWeiHui.size();i++){
						//request.setAttribute("cunWeiHui"+i, cunWeiHui.get(i).getCunWeiHui());
					%>
					<td><u  onClick="article_edit('查看详情','basicInfoControl/find_cunWeiHuiMerber?id=<%=cunWeiHui.get(i).getCunWeiHui().getUserId() %>')"
											 title="查看详情"><%=cunWeiHui.get(i).getName() %></u></td>
					
					<%} %>
</td>
				</tr>
			</tbody>
		</table>
		
		
		<br>
		
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">村庄编号</th>
					<th width="80">村庄名字</th>
					<th width="80">省</th>
					<th width="80">市</th>
					<th width="80">区</th>
					<th width="75">乡</th>
					<th width="60">低保人数</th>
					<th width="60">五保人数</th>
					<th width="100">残疾人数</th>
					<th width="120">生产总值</th>
					<%if(Integer.parseInt(session.getAttribute("rollId").toString())==2) {%>
						<th width="120">操作</th>
					<%} %>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					
					<td>${villageInfo.id}</td>
					<td>${villageInfo.name}</td>
					<td>${villageInfo.sheng}</td>
					<td>${villageInfo.shi}</td>
					<td>${villageInfo.qu} </td>
					<td>${villageInfo.xiang}</td>
					<td>${villageInfo.dibao}</td>
					<td>${villageInfo.wubao}</td>
					<td>${villageInfo.canji}</td>
					<td class="td-status">${villageInfo.shengchanzongzhi}</td>
					<%if(Integer.parseInt(session.getAttribute("rollId").toString())==2) {%>
					<td class="f-14 td-manage">
<a style="text-decoration:none" class="ml-5" onClick="article_edit('村庄基本信息修改','forward/forward_update_countrysideinfo?num=1')" href="javascript:;" title="修改">
<i class="Hui-iconfont">&#xe6df;</i></a>
</td>
<%} %>
				</tr>
			</tbody>
		</table>
		
		<br>
		
		
		
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">是否通网络</th>
					<th width="80">是否通电话</th>
					<th width="80">是否通有线</th>
					
				
					<th width="80">自然村</th>
					<th width="80">是否通水</th>
					<th width="75">是否饮水困难</th>
					<th width="60">是否通电</th>
					
					<th width="80">村集体债务</th>
					<th width="120">耕地总面积</th>
					<th width="120">辖区总面积</th>
						<%if(Integer.parseInt(session.getAttribute("rollId").toString())==2) {%>
					<th width="120">操作 </th><%} %>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					
					
					<td>${villageInfo.tongwangluo}</td>
					<td>${villageInfo.tongdianhua}</td>
					<td>${villageInfo.tongyouxian}</td>
					
					
					<td>${villageInfo.zirancun}</td>
					<td>${villageInfo.tongshui}</td>
					<td>${villageInfo.yinshuikunnan}</td>
					<td class="td-status">${villageInfo.tongdian}</td>
					
					<td>${villageInfo.cunzhaiwu}</td>
					<td>${villageInfo.gendimianji}</td>
					<td>${villageInfo.xiaqumianji}</td>
					<%if(Integer.parseInt(session.getAttribute("rollId").toString())==2) {%>
					<td class="f-14 td-manage">

<a style="text-decoration:none" class="ml-5" onClick="article_edit('村庄基本信息修改','forward/forward_update_countrysideinfo?num=2')" href="javascript:;" title="修改">
<i class="Hui-iconfont">&#xe6df;</i></a></td>
<%} %>
				</tr>
			</tbody>
		</table>
		
		
		
		
		<br>
		
		
		
		
		
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					
					<th width="80">人均纯收入</th>
					<th width="80">农业产值</th>
					<th width="80">村集体收入</th>
					
					<th width="80">村小学个数</th>
					<th width="80">村中学个数</th>
					<th width="75">人口总数</th>
					<th width="60">常住人口数</th>
					<th width="60">户籍人口数</th>
					<th width="100">农业人口数</th>
					<%if(Integer.parseInt(session.getAttribute("rollId").toString())==2) {%>
					<th width="120">操作 </th><%} %>
					</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					
					<td>${villageInfo.renjunshouru}</td>
					<td>${villageInfo.nongyechanzhi}</td>
					<td>${villageInfo.cunshouru}</td>
					
					
					<td>${villageInfo.cunxiaoxue}</td>
					<td>${villageInfo.cunzhongxue}</td>
					<td>${villageInfo.renkouzongshu}</td>
					<td>${villageInfo.changzhurenkoushu}</td>
					<td>${villageInfo.hujirenkoushu}</td>
					<td>${villageInfo.nongyerenkoushu}</td>
					<%if(Integer.parseInt(session.getAttribute("rollId").toString())==2) {%>
					<td class="f-14 td-manage">

<a style="text-decoration:none" class="ml-5" onClick="article_edit('村庄基本信息修改','forward/forward_update_countrysideinfo?num=3')" href="javascript:;" title="修改">
<i class="Hui-iconfont">&#xe6df;</i></a></td>
<%} %>
				</tr>
			</tbody>
		</table>
		
		
		
		
		
		
	</div>
</div>
</form>
<script type="text/javascript" src="jsp/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="jsp/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="jsp/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="jsp/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
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
</script> 
</body>
</html>