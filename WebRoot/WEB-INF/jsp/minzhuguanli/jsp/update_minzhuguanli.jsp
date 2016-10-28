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
	<form class="form form-horizontal" id="form-article-add" method="post" action="IDemocraticManage/update_guanlitianbao" onsubmit="return check()">
		<input type="hidden" name="id" value="${id}">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">人事变更：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select class="input-text" name="renshibiangeng" id="renshibiangeng">
					<option value="任职">任职</option>
					<option value="罢免">罢免</option>
				</select>
				<div style="height:15px" id="unitError"></div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">村委自治章程：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select class="input-text" name="cunweizizhizhangcheng" id="cunweizizhizhangcheng">
					<option value="有">有</option>
					<option value="没有">没有</option>
				</select>
				<div style="height:15px" id="telError"></div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">帐号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="account" name="account">
				<div style="height:15px" id="accountError">${accountError}</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">原因：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="reason" name="reason">
				<div style="height:15px" id="reasonError">${reasonError}</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">职位：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select class="input-text" name="position" id="position">
					<option value="村书记">村书记</option>
					<option value="村长">村长</option>
					<option value="村副书记">村副书记</option>
					<option value="副村长">副村长</option>
					<option value="妇女主任">妇女主任</option>
					<option value="财务主任">财务主任</option>
					<option value="宣传委员">宣传委员</option>
					<option value="组织委员">组织委员</option>
					<option value="宣传委员">宣传委员</option>
					<option value="治保会主任">治保会主任</option>
				</select>
				<div style="height:15px" id="unitError"></div>
			</div>
		</div>
	<%-- 	<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">变更时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="date" placeholder="" id="biangengshijian" name="biangengshijian">
				<div style="height:15px" id="biangengshijianError">${biangengshijianError}</div>
			</div>
		</div> --%>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">变更时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input  type="text" class="input-text" readonly="readonly" id="biangengshijian" name="biangengshijian"/>
				<div style="height:15px" id="xuanjuriError">${biangengshijianError}</div>
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
<script src="jsp/laydate/laydate.js"></script>
<script type="text/javascript">
laydate({
	   elem: '#biangengshijian'
	});
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>