<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="frame/meta.jsp"></jsp:include>
<title>角色管理</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		申报记录<span class="c-gray en">&gt;</span> 记录查看 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<div class="mt-20">
			<table
				class="table table-border table-bordered radius table-striped table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th>#</th>
						<th width="auto">记录编号</th>
						<th width="auto">类别</th>
						<th width="auto">申报人</th>
						<th width="auto">申报时间</th>
						<th width="auto">凭证路径</th>
						<th width="auto">审核状态</th>
						<th width="auto">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${recordlist}" var="record" varStatus="rr">
						<tr>
						    <th>${rr.index+1}</th>
						    <th>${record.record_Id}</th>
							<th>${record.category_Id}</th>
							<th>${record.user.user_name}</th>
							<th>${record.record_time}</th>
							<th>${record.record_proof}</th>
							<th>${record.statuString}</th>
							<td>
								<button type="button"
									onclick="location.href='${pageContext.request.contextPath}/gpadistr/findAllGpa.do?id=${assess.assessinfo_id}'"
									class="btn bg-olive btn-xs">详情</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<jsp:include page="frame/footer.jsp"></jsp:include>

		<!--请在下方写此页面业务相关的脚本-->
		<script type="text/javascript">
			function info_edit(title, url, id, w, h) {
				layer_show(title, url, w, h);
			}
			$(function() {
				$('.table-sort').dataTable({
					"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
					"bStateSave" : true,//状态保存
					"aoColumnDefs" : [
					//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
					{
						"orderable" : false,
						"aTargets" : [ 0, 7 ]
					} // 制定列不参与排序
					]
				});

			});
			/*管理员-角色-添加*/
			function admin_role_add(title, url, w, h) {
				layer_show(title, url, w, h);
			}
			/*管理员-角色-编辑*/
			function admin_role_edit(title, url, id, w, h) {
				layer_show(title, url, w, h);
			}
			/*管理员-角色-删除*/
			function admin_role_del(obj, id) {
				layer.confirm('角色删除须谨慎，确认要删除吗？', function(index) {
					$.ajax({
						type : 'POST',
						url : '',
						dataType : 'json',
						success : function(data) {
							$(obj).parents("tr").remove();
							layer.msg('已删除!', {
								icon : 1,
								time : 1000
							});
						},
						error : function(data) {
							console.log(data.msg);
						},
					});
				});
			}
		</script>
</body>
</html>