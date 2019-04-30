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
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<title>教研教材申报</title>
</head>

<body>
		<div class="four steps">
			<span id="buzou1" class="active step" >第一步  填写信息</span>
			<span id="buzou2" class="step">第二步 绩点分配</a></span>
			<span id="buzou3" class="step">第三步 提交审核</a></span> 
		</div>

	<article class="page-container">
	<form class="form form-horizontal" id="" action="${pageContext.request.contextPath}/jy_book/Add_book.do" method="post">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>教材名称：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S"name="book_name">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>专业名称：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" value="" name="book_object">
			</div>
		</div>
		
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>教材级别：</label>
			<div class="formControls col-xs-6 col-sm-3">
				<span class="select-box"> <select name="book_lev" class="select">
				<option value="">---请选择---</option>
				<c:forEach items="${book_lev}" var="book_lev">
					<option value="${book_lev.project_id} ">${book_lev.projectlev_name}</option>		
				</c:forEach>
						
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>主编身份：</label>
			<div class="formControls col-xs-6 col-sm-3">
				<span class="select-box"> <select name="T_name" class="select">
							<option value="">---请选择---</option>
				<c:forEach items="${zhubian}" var="zhubian">
					<option value="${zhubian.project_id} ">${zhubian.projectlev_name}</option>		
				</c:forEach>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>总字数：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" value="" name="book_Enum"
					id="" >
			</div>
		</div>
		<div class="row cl">
			<table id="UserTable"
				class="table table-border table-bordered table-hover table-bg table-sort" style="width: 500px;margin-left: 214px;">
				<thead>
					<tr class="text-c">
						<th width="25">序号</th>
						<th width="80">执笔人</th>
						<th width="80">执笔字数</th>
					</tr>
				</thead>
				<tbody  id="UserTableBody">
					<tr>
							<td class="td">1</td>
							<td>张三</td>
							<td class="text-center">
								<%-- <button  type="button" class="btn bg-olive btn-xs" onclick="" id="${x.index+1}">字数</button> --%>
								<input type="text" class="input-text radius size-S" id="" >
							</td>
						</tr>
				</tbody>
			</table>
		</div>
		<input type="hidden" name="book_Pname">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>语言类型：</label>
			<div class="formControls col-xs-6 col-sm-3">
				<span class="select-box"> <select name="language" class="select">
							<option value="">---请选择---</option>
				<c:forEach items="${language}" var="language">
					<option value="${language.project_id} ">${language.projectlev_name}</option>		
				</c:forEach>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>是否立项：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<span class="select-box"> <select name="builed" class="select">
							<option value="">---请选择---</option>
				<c:forEach items="${builed}" var="builed">
					<option value="${builed.project_id} ">${builed.projectlev_name}</option>		
				</c:forEach>
				</select>
				</span>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>是否再版：</label>
			<div class="formControls col-xs-8 col-sm-3" style="width: 20px;">
				<input type="checkbox" value=""  style="width: 18px;height: 18px;margin-top: 6px;" >
				<input type="hidden" name="repiblic" value="">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>出版社：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text radius size-S" value="" name="book_press">
			</div>
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
</script>
	
</body>
</html>