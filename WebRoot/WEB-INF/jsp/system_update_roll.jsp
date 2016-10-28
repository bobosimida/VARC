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
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="jsp/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="jsp/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="jsp/static/h-ui.admin/css/style.css" />
<title></title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-article-add" method="post" action="basicInfoControl/system_update_roll" onsubmit="return check()">
		<input type="hidden" value="${updateAccount}" name="updateAccount">
		<input type="hidden" value="${oldRollId}" name="oldRollId">
		<input type="hidden" value="${villageinfoId}" name="villageinfoId" id="villageinfoId">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"></label>
			<div class="formControls col-xs-8 col-sm-9">
				<div style="height:15px" id="">${zhiWeiIdError}</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">角色：</label>
			<div class="formControls col-xs-8 col-sm-9" id="box">
				<span class="select-box" >
				<select name="rollId" class="select" id="rollId">
					<option value="1">村民</option>
					<option value="2">村委会成员</option>
				</select>
				</span>
			</div>
		</div>
		<input type="hidden" name="oldUserId" id="oldUserId">
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button  class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button  class="btn btn-default radius" type="reset">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="jsp/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="jsp/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="jsp/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="jsp/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script> 
<script type="text/javascript" src="jsp/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="jsp/lib/jquery.validation/1.14.0/messages_zh.min.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="jsp/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="jsp/lib/My97DatePicker/WdatePicker.js"></script>  
<script type="text/javascript" src="jsp/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="jsp/lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="jsp/lib/ueditor/1.4.3/ueditor.all.min.js"> </script> 
<script type="text/javascript" src="jsp/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
var flag=true;
function check(){
	return flag;
}
	$(document).ready(function(){
	$("#rollId").change(function(){
		if($("#rollId").val()==2){
			$("#box").append("<span class=\"select-box\" id=\"zhiwei\" ><select name=\"zhiweiId\" class=\"select\" id=\"zhiWeiId\">"+
			"<option value=\"0\">选择职位</option>"+
			"<option value=\"1\">村书记</option>"+
			"<option value=\"2\">村长</option>"+
			"<option value=\"3\">村副书记</option>"+
			"<option value=\"4\">副村长</option>"+
			"<option value=\"5\">妇女主任</option>"+
			"<option value=\"6\">财务主任</option>"+
			"<option value=\"7\">宣传委员</option>"+
			"<option value=\"8\">组织委员</option>"+
			"<option value=\"9\">治保会委员</option>"
			);
			$("#zhiwei").change(function(){
			 $.post("basicInfoControl/find_zhiwei_isUse",{zhiWeiId:$("#zhiWeiId").val(),villageinfoId:$("#villageinfoId").val()},function(data){
			 	var vil= eval("(" + data + ")");
			 	if(vil.userId!=undefined){
			 		var w=window.confirm("该职位已有人，是否替换？");
			 		if(w){
			 			flag=true;
			 			$("#oldUserId").val(vil.userId);
			 		}else{
			 			flag=false;
			 			
			 		}
			 	}else{
			 		$("#oldUserId").val("");
			 	}
			 });
		
			}); 
		}else{
			$("#zhiwei").remove();
			flag=true;
			$("#oldUserId").val();
		}
	});
	
});
</script>
</body>
</html>