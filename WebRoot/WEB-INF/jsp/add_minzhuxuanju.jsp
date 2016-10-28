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
	<form class="form form-horizontal" id="form-article-add" method="post" 
		action="democraticElectionControl/add_minzhuxuanju" enctype="multipart/form-data" onsubmit="return check()">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2" >村庄编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="" name="villageinfoId">
				<div style="height:15px" id="">${villageinfoIdError}</div>
			</div>
		</div>
		
		 <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">下届村委会预计人数：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="" name="cunweihuirenshu">
				<div style="height:15px" id="">${cunWeiHuiRenShuError}</div>
			</div>
		</div>
		
		
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">候选人帐号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" id="datemin"  class="input-text"  placeholder="候选人帐号以    、隔开" name="accounts">
				<div style="height:15px" id="">${accountsError}</div>
			</div>
		</div>
		
	  	 <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">选举日期：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input  type="text" class="input-text" readonly="readonly"
				 id="xuanjuri" name="xuanjuri"/>
				<div style="height:15px" id="xuanjuriError">${xuanjuriError}</div>
			</div>
		</div>  
	 	<%-- <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">选举日期：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" readonly="readonly" onfocus=
				"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'datemin\')}'})" id="datemax"
				class="input-text Wdate" name="xuanjuri">
				<div style="height:15px" id="xuanjuriError">${xuanjuriError}</div>
			</div>
		</div> --%>
		 <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">实施方案：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="file"   placeholder="" id="" name="file">
				<div style="height:15px" id="">请上传*.txt、*.doc、*.docx、*.xls、*.xlsx、*.ppt、*.pptx文件</div>
				<div style="height:15px" id="">${fileError}</div>
			</div>
		</div> 
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<div style="height:15px" id=""></div>
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
<script src="jsp/laydate/laydate.js"></script>
<script type="text/javascript">
	function check(){
		 var datemax=$("#xuanjuri");
		if(datemax.val()==""){
			$("#xuanjuriError").html("请选择选举日期");
			return false;
		}else{
			return true;
		} 
		
	}
	laydate({
	   elem: '#xuanjuri'
	});
</script>
</body>
</html>