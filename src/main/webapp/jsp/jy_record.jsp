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

<title>申报记录</title>

</head>

<body>
	<article class="page-container">
	<form class="form form-horizontal" id="team">

		<table id="UserTable"
				class="table table-border table-bordered table-hover table-bg table-sort" style="width: 850px;margin:20px auto">
				<thead>
					<tr class="text-c">
						<th width="100">序号</th>
						<th width="100">项目名称</th>
						<th width="100">项目类型</th>
						<th width="100">项目等级</th>
						<th width="100">小组名称</th>
						<th with="100">个人业绩点</th>
						<th width="100">状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list_user}" var="list_user">
						<tr>
							<td>1</td>
							<td>中原工教学平台</td>
							<td>教学平台</td>
							<td>国家级</td>
							<td>张三</td>
							<td>200</td>
							<td>已通过</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</form>
	</article>
	<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"></script>
<script>
    </script>


</body>
</html>