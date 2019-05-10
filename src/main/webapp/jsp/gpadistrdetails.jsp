<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="frame/meta.jsp"></jsp:include>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		详情 <span class="c-gray en">&gt;</span> 分配详情 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="mt-20">
		<div class="content-wrapper" style="width: 98%">
			<!-- 内容头部 -->
			<section class="content-header">
			<h1>
				详情 <small>全部分配信息</small>
			</h1>
			</section>
			<!-- 内容头部 /-->
			<div class="section-body">
				<div class="mt-20">
						<div class="col-lg-12">
							<div class="card">
								<div class="card-header">
									<h4>业绩点分配详情</h4>
								</div>
								<table class="table table-striped table-hover mb-0 text-nowrap">
									<tr>
										<th class="">#</th>
										<th class="">用户组</th>
										<th class="">记录编号</th>
										<th class="">用户名</th>
										<th class="">用户身份</th>
										<th class="">所获业绩点</th>
									</tr>
									<c:forEach var="gpaDistr" items="${gpaDistrsList}"
										varStatus="xh">
										<tr>
											<td>${xh.index+1}</td>
											<td>${gpaDistr.userteam.userteam_name}</td>
											<td>${gpaDistr.record_id }</td>
											<td>${gpaDistr.user.user_name}</td>
											<td>${gpaDistr.userteam_profession}</td>
											<td>${gpaDistr.userteam_getGpa}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
					</div>
				</div>
				<div class="col-lg-12">
				<div class="mt-20">
					<div class="card">
						<div class="card-header">
							<h4>业绩记录详情</h4>
						</div>
								<table class="table table-striped mb-0 text-nowrap">
									<tr>
										<th>#</th>
										<th class="">类别</th>
										<th class="">申报时间</th>
										<th class="">凭证地址</th>
										<th class="">申报状态</th>
									</tr>
									<tr>
										<th>1</th>
										<th>${record.category_Id}</th>
										<th>${record.record_time}</th>
										<th>${record.record_proof}</th>
										<th>${record.statuString}</th>

									</tr>
			       		</table>
					</div>
				</div>
			</div>
			<div class="box-tools text-center" align="center">
				<button  type="button" class="btn btn-primary btn-xs"
					onclick="history.back(-1);">返回</button>
			</div>
			<!--工具栏/-->
			</section>
			<!-- 正文区域 /-->


		</div>
		<footer class="footer mt-20">
	      <div class="container"> 
		<!-- 内容区域 /-->
		<!-- <div align="center"> -->
		<strong>Copyright &copy;2019 <a
			href="https://soft.zut.edu.cn/">软件学院</a>.
		</strong> All rights reserved. </footer>
		<!-- 底部导航 /-->
		</div>
		</footer>
	</div>
	</div>
	
	<jsp:include page="frame/footer.jsp"></jsp:include>
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
    <script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
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