<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 基本信息管理 <span class="c-gray en">&gt;</span> 查找用户 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 用户帐号：
		<input type="text" name="" id="vAccount" placeholder=" 用户帐号" style="width:250px" class="input-text">
		<button name="" id="findBtn" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
		<div style="height:15px" id="accountError"></div>
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">帐号</th>
					<th width="80">姓名</th>
					<th width="80">单位</th>
					<th width="80">电话</th>
					<th width="120">邮箱</th>
					<th width="75">性别</th>
					<th width="60">年龄</th>
					<th width="60">帐号角色</th>
					<th width="60">所属村庄</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td id="_account" style="height:20px"></td>
					<td id="_name"></td>
					<td id="_unit"></td>
					<td id="_tel"></td>
					<td id="_email"></td>
					<td id="_sex"></td>
					<td id="_age"></td>
					<td id="_rollName"></td>
					<td class="td-status" id="_villageName"></td>
					<td class="f-14 td-manage" id="update">
					</td>
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
var oldRollId;
var  villageinfoId;
/*资讯-编辑*/
function article_edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url+"?account="+$("#_account").html()+"&oldRollId="+oldRollId+"&villageinfoId="+villageinfoId
	});
	layer.full(index);
}
$(document).ready(function(){
	var findBtn=$("#findBtn");
	findBtn.click(function(){
		$.post("basicInfoControl/find_update_user",{account:$("#vAccount").val()},function(data){
		  var vil= eval("(" + data + ")");
		 if(vil.user==undefined){
			$("#accountError").html("该帐号不存在、输入有误或该帐号权限无法被修改");	 	
		 }else{
			 oldRollId=vil.user.user.rollId;
			 villageinfoId=vil.user.user.villageinfoId;
		    $("#_account").html(vil.user.user.account); 
			$("#_name").html(vil.user.user.name);
			$("#_unit").html(vil.user.user.unit); 
			$("#_tel").html(vil.user.user.tel); 
			$("#_email").html(vil.user.user.email); 
			$("#_sex").html(vil.user.user.sex);
			$("#_age").html(vil.user.user.age);
		 	$("#_rollName").html(vil.user.rollName);
			$("#_villageName").html(vil.user.villageName); 
			$("#update").html("<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_edit"+
								"('修改用户信息','forward/forward_system_update_roll')\" href=\"javascript:;\" title=\"编辑\">"+
								"<i class=\"Hui-iconfont\">&#xe6df;</i></a>");
		} 
			
		});
	});
});
</script> 
</body>
</html>