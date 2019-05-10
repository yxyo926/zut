< 8905%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/static/h-ui.admin/skin/default/skin.css"id="skin" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/static/h-ui.admin/css/style.css" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<title>教研项目申报管理</title>
</head>

<body>
		<div class="four steps">
			<span id="buzou1" class="active step" >第一步  填写信息</span>
			<span id="buzou2" class="step">第二步 绩点分配</a></span>
			<span id="buzou3" class="step">第三步 提交审核</a></span> 
		</div>
	<article class="page-container">
	<form class="form form-horizontal" id="" action="${pageContext.request.contextPath}/jy_pingtai/Add_pingtai.do" method="post">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>平台名称：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" name="pt_name">
			</div>
			
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>平台类型：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" name="pt_type">
			</div>
			
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>主持单位：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" name="pt_place">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>教研平台级别：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<span class="select-box"> <select id="select_lev" name="pingtai_lev" class="select">
				<option value="">---请选择---</option>
				<c:forEach items="${list_lev}" var="list">
					<option value="${list.project_id} ">${list.projectlev_name}</option>		
				</c:forEach>
				</select>
				</span>
				
			</div>
			</div>
				<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>考核等级：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<span class="select-box"> <select id="select_lev2" name="kaohe_lev" class="select" onchange="gpa()">
					<option value="1">合格</option>
					<option value="1.5">优秀</option>
					<option value="1.2">良好</option>
					<option value="0.5">基本合格</option>
					<option value="0">不合格</option>		
				</select>
				</span>
			</div>
			</div>


		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>时间范围：</label> 
				<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
				id="logmin" name="logmin" class="input-text Wdate" style="width: 120px;">
			- <input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
				id="logmax" name="logmax" class="input-text Wdate" style="width: 120px;">
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户组：</label>
			<div class="formControls col-xs-6 col-sm-4">
						<input type="text" class="input-text" value="" placeholder=""
					   id="userteam_name" name="userteam_name">
			</div>
			<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户组人数：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder=""
					   id="userteam_num" name="userteam_num">

			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button	class="btn btn-primary radius" type="submit" onclick="Add_PingTai()">下一步</button>

				<button onClick="removeIframe();" class="btn btn-default radius"
					type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
		
	</form>
	</article>

	<script type="text/javascript" src="../../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="../../static/h-ui.admin/js/H-ui.admin.page.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/webuploader/0.1.5/webuploader.min.js"></script> 
	<script type="text/javascript"src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>

	<script type="text/javascript">
	
	function gpa(){
		var pt_levgpa=document.getElementById("select_lev").value;
		var pt_khgpa=document.getElementById("select_lev2").value;
		document.getElementsByName("pingtai_lev").value=pt_levgpa;
		document.getElementsByName("kaohe_lev").value=pt_khgpa;
		
	}
	</script>
	
	
</body>
</html>