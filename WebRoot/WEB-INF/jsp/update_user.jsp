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
<!--/meta 作为公共模版分离出去-->

<title></title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-article-add" method="post" action="basicInfoControl/update_user" onsubmit="return check()">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">所在单位：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="unit" name="unit">
				<div style="height:15px" id="unitError">${unitError}</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">联系电话：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="电话输入格式：xxx-xxxxx、xxxxxxxxxxx" id="tel" name="tel">
				<div style="height:15px" id="telError">${telError}</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">电子邮箱：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="email" name="email">
				<div style="height:15px" id="emailError">${emailError}</div>
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button  class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button  class="btn btn-default radius" type="reset">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
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
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="jsp/lib/My97DatePicker/WdatePicker.js"></script>  
<script type="text/javascript" src="jsp/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="jsp/lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="jsp/lib/ueditor/1.4.3/ueditor.all.min.js"> </script> 
<script type="text/javascript" src="jsp/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
var flags=false;
var flagName=true;
var flagAge=true;
var flagUnit=true;
var flagTel=true;
var flagEmail=true;
var flag1=false;
var flag2=false;
var flag3=false;
var flag4=false;
var flag5=false;
$(document).ready(function(){
	$("#name").blur(function(){
			var name=$("#name");
			if(name.val().trim()==""){
				flag1=false;
			}else{flag1=true;}
		});
		
		$("#age").blur(function(){
			var age=$("#age");
			if(age.val().trim()==""){
					flag2=false;
			}else{flag2=true;}
		});
		
		$("#unit").blur(function(){
			var unit=$("#unit");
			if(unit.val().trim()==""){
						flag3=false;
			}else{flag3=true;}
			
		});
		
		$("#tel").blur(function(){
			if($("#tel").val().trim()==""){
						flag4=false;
			}else{flag4=true;}
		});
		
		$("#email").blur(function(){
			if($("#email").val().trim()==""){
						flag5=false;
			}else{flag5=true;}
		});
});
function check(){
		if(flag1 || flag2 || flag3 || flag4 || flag5){
			flags=true;
		}else{flags=false;}
		return flags;
	}
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>