<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<link rel="Bookmark" href="${pageContext.request.contextPath}/favicon.ico" >
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />

<title>文学艺术项目申报管理</title>
</head>

<body>
		<div class="four steps">
			<span id="buzou1" onclick="javascript:window.history.back(-1);" class="active step" >第一步  填写信息</span>
			<span id="buzou2" onclick="javascript:window.history.back(-1);" class="step">第二步 绩点分配</a></span>
			<span id="buzou3" onclick="javascript:window.history.back(-1);" class="step">第三步 提交审核</a></span> 
		</div>

	<article class="page-container">
	<form class="form form-horizontal" id="" action="${pageContext.request.contextPath}/wenyi/Add_biaoyan.do" method="Post">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>项目类别：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<span class="select-box"> <select name="project_type" class="select">
				<option value="">-----请选择-----</option>
				<option value="艺术展览">艺术展览</option>
				<option value="作品收藏">作品收藏</option>
				<option value="文艺演出">文艺演出</option>
				<option value="录制播出">录制播出</option>	
				<option value="录制播出">录制播出</option>				
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>项目名称：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" value=""
					placeholder="" id="" name="project_name">
			</div>
			
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>场所：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<span class="select-box"> <select name="project_lev" class="select">
				<!-- <option value="">-----请选择-----</option>
				<option value="200">展览-全国美术作品展</option>
				<option value="80">展览-全国美术作品单项展</option>	
				<option value="100">展览-中国文联下属一级协会</option>	
				<option value="35">展览-省文联下属一级协会</option>
				<option value="600">演出-国家级专场</option>
				<option value="60">演出-国家级演出</option>	
				<option value="60*35%">演出-省级演出</option>					
				<option value="120">录制-国家级</option>
				<option value="120*35%">录制-省级</option>	 -->
				<option value="">---请选择---</option>
				<c:forEach items="${project_lev}" var="project_lev">
					<option value="${project_lev.project_id} ">${project_lev.projectlev_name}</option>		
				</c:forEach>
				</select>
				</span>
			</div>

		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>项目时间：</label> 
				<input type="text" onfocus="WdatePicker()"
				id="logmin" name="project_Time" class="input-text Wdate" style="width: 120px;">
		
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
				<button	class="btn btn-primary radius" type="submit">下一步</button>

				<button onClick="removeIframe();" class="btn btn-default radius"
					type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
		
	</form>
	</article>

	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.page.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
	<script type="text/javascript"src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>

	<script type="text/javascript">
	</script>
	
</body>
</html>