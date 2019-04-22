<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="frame/meta.jsp"></jsp:include>
</head>
<body>

		<!-- 内容区域 -->
			<div class="panel panel-success">
				<div class="panel-heading">分配信息</div>
				<!--数据列表-->
				<table id="dataList"
					class="table table-bordered table-striped table-hover dataTable">
					<thead>
						<tr>
							<th class="">分配编号</th>
							<th class="">用户组</th>
							<th class="">记录编号</th>
							<th class="">用户名</th>
							<th class="">用户身份</th>
							<th class="">所获业绩点</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="gpaDistr" items="${gpaDistrsList}">
							<tr>
								<td>${gpaDistr.gpadistr_id}</td>
								<td><input type="text" size="10" value="${gpaDistr.userteam_id }"
									readonly="readonly"></td>
								<td><input type="text" size="10" value="${gpaDistr.record_id }"
									readonly="readonly"></td>
								<td><input type="text" size="20"
									value="${gpaDistr.user_Id}" readonly="readonly"></td>
								<td><input type="text" size="15"
									value="${gpaDistr.userteam_profession}" readonly="readonly"></td>
								<td><input type="text" size="28"
									value="${gpaDistr.userteam_getGpa }" readonly="readonly"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!--数据列表/-->
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">记录信息</div>
				<!--数据列表-->
				<table id="dataList"
					class="table table-bordered table-striped table-hover dataTable">
					<thead>
						<tr>
							<th class="">人群</th>
							<th class="">姓名</th>
							<th class="">性别</th>
							<th class="">手机号码</th>
							<th class="">证件类型</th>
							<th class="">证件号码</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="gpaDistr" items="${gpaDistrsList}">
							<tr>
								<td>${gpaDistr.gpadistr_id}</td>
								<td><input type="text" size="10" value="${gpaDistr.userteam_id }"
									readonly="readonly"></td>
								<td><input type="text" size="10" value="${gpaDistr.record_id }"
									readonly="readonly"></td>
								<td><input type="text" size="20"
									value="${gpaDistr.user_Id}" readonly="readonly"></td>
								<td><input type="text" size="15"
									value="${gpaDistr.userteam_profession}" readonly="readonly"></td>
								<td><input type="text" size="28"
									value="${gpaDistr.userteam_getGpa }" readonly="readonly"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!--数据列表/-->
			</div>
<jsp:include page="frame/footer.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			// 选择框
			$(".select2").select2();

			// WYSIHTML5编辑器
			$(".textarea").wysihtml5({
				locale : 'zh-CN'
			});
		});

		// 设置激活菜单
		function setSidebarActive(tagUri) {
			var liObj = $("#" + tagUri);
			if (liObj.length > 0) {
				liObj.parent().parent().addClass("active");
				liObj.addClass("active");
			}
		}

		$(document).ready(function() {

			// 激活导航位置
			setSidebarActive("order-manage");

			// 列表按钮 
			$("#dataList td input[type='checkbox']").iCheck({
				checkboxClass : 'icheckbox_square-blue',
				increaseArea : '20%'
			});
			// 全选操作 
			$("#selall").click(function() {
				var clicks = $(this).is(':checked');
				if (!clicks) {
					$("#dataList td input[type='checkbox']").iCheck("uncheck");
				} else {
					$("#dataList td input[type='checkbox']").iCheck("check");
				}
				$(this).data("clicks", !clicks);
			});
		});
	</script>
</body>


</html>