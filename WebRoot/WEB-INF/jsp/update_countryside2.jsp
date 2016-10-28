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
	<form class="form form-horizontal" id="form-article-add" action="basicInfoControl/update_countryside" method="post">
		<input type="hidden" value="2" name="num">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"></label>
			<div class="formControls col-xs-8 col-sm-9">
				<div style="height:15px" id="">${nochange}</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">是否通网络:</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="tongwangluo" class="select" >
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">是否通电话：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="tongdianhua" class="select">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">是否通有线：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="tongyouxian" class="select">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">自然村：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="zirancun" class="select">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">是否通水：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="tongshui" class="select">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				</span>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">是否饮水困难：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="yinshuikunnan" class="select">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">是否通电：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="tongdian" class="select">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">村集体债务：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="" name="cunzhaiwu">
				<div style="height:15px" id="">${cunzhaiwuError}</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">耕地总面积：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="" name="gendimianji">
				<div style="height:15px" id="">${gendiMianjiError}</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">辖区总面积：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="" name="xiaqumianji">
				<div style="height:15px" id="">${xiaquMianjiError}</div>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button  class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
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
</body>
</html>