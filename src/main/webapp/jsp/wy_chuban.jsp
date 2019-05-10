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

<title>公开出版作品</title>
</head>

<body>
		<div class="four steps">
			<span id="buzou1" onclick="javascript:window.history.back(-1);" class="active step" >第一步  填写信息</span>
			<span id="buzou2" onclick="javascript:window.history.back(-1);" class="step">第二步 绩点分配</a></span>
			<span id="buzou3" onclick="javascript:window.history.back(-1);" class="step">第三步 提交审核</a></span> 
		</div>

	<article class="page-container">
	<form class="form form-horizontal" id="" action="${pageContext.request.contextPath}/wenyi/Add_chuban.do" method="Post">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>作品名称：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" value=""
					placeholder="" id="" name="project_name">
			</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>类型：</label>
			<div class="formControls col-xs-6 col-sm-3">
				<span class="select-box"> <select name="project_type" class="select">
							<option value="1">学术类型</option>
							<option value="0">非学术类型</option>
				</select>
				</span>
			</div>
		</div>
		
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>非学术类等级：</label>
			<div class="formControls col-xs-6 col-sm-3">
				<span class="select-box"> <select name="lev_select" class="select">
							<option value="">---请选择---</option>
				<c:forEach items="${Fxueshu_lev}" var="Fxueshu_lev">
					<option value="${Fxueshu_lev.project_id} ">${Fxueshu_lev.projectlev_name}</option>		
				</c:forEach>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>中原工学院署名：</label>
			<div class="formControls col-xs-6 col-sm-3">
				<span class="select-box"> <select name="wy_zutsign" class="select">
							<option value="">---请选择---</option>
				<c:forEach items="${wy_zutsign}" var="wy_zutsign">
					<option value="${wy_zutsign.project_id} ">${wy_zutsign.projectlev_name}</option>		
				</c:forEach>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>立项：</label>
			<div class="formControls col-xs-6 col-sm-3">
				<span class="select-box"> <select name="builed_lev" class="select">
							<option value="">---请选择---</option>
					<c:forEach items="${builed_lev}" var="builed_lev">
						<option value="${builed_lev.project_id} ">${builed_lev.projectlev_name}</option>		
					</c:forEach>
				</select>
				</span>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>出版社名称：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" value=""
					placeholder="" id="" name="press_name">
			</div>
			
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>出版社等级：</label>
			<div class="formControls col-xs-6 col-sm-3">
				<span class="select-box"> <select name="press_lev" class="select">
							<option value="">---请选择---</option>
				<c:forEach items="${press_lev}" var="press_lev">
					<option value="${press_lev.project_id} ">${press_lev.projectlev_name}</option>		
				</c:forEach>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>期刊名称：</label>
			<div class="formControls col-xs-8 col-sm-3">
			<span class="select-box"> <select name="qk_name" class="select">
							<option value="">---请选择---</option>
				<c:forEach items="${qk_name}" var="qk_name">
					<option value="${qk_name.project_id} ">${qk_name.projectlev_name}</option>		
				</c:forEach>
				</select>
			</div>
			
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>期刊期数：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" value=""
					placeholder="" id="" name="qk_num">
			</div>
			
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>作品页数/曲数：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" value=""
					placeholder="" id="" name="zp_num">
			</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>页面大小：</label>
			<div class="formControls col-xs-6 col-sm-3">
				<span class="select-box"> <select name="Zp_size" class="select">
							<option value="">---请选择---</option>
				<c:forEach items="${zp_size}" var="zp_size">
					<option value="${zp_size.project_id} ">${zp_size.projectlev_name}</option>		
				</c:forEach>
				</select>
				</span>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>出版时间：</label> 
				<input type="text" onfocus="WdatePicker()"
				id="logmin" name="CB_time" class="input-text Wdate" style="width: 120px;">
			
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