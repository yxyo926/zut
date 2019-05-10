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
		管理员管理 <span class="c-gray en">&gt;</span> 角色管理 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					删除记录</a> <a class="btn btn-primary radius" href="javascript:;"
				onclick="location.href='${pageContext.request.contextPath}/assess/getSort.do'"><i
					class="Hui-iconfont">&#xe600;</i> 添加记录</a>
			</span> <span class="r">共有数据：<strong><c:out
						value="${fn:length(assessList)}"></c:out></strong> 条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered radius table-striped table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="auto"><input type="checkbox" value="" name=""></th>
						<th>#</th>>
						<th width="auto">评价名称</th>
						<th width="auto">项目名称</th>
						<th width="auto">申报人</th>
						<th with="auto">总业绩点</th>
						<th with="auto">我的业绩点</th>
						<th with="auto">审核状态</th>
						<th with="auto">操作</th>
					</tr>
				</thead>
				<tbody>
						<c:forEach items="${assessList}" var="assess" varStatus="rr">
							<tr>
								<td><input name="ids" type="checkbox"></td>
								<td>${rr.index+1}</td>
								<td>${assess.sys_Dict.name}</td>
								<td>${assess.project.projectinfo_Name}</td>
								<td>${assess.user.user_name}</td>	
								<td>${assess.assessinfo_getGpa}</td>
								<td class="text-center">${assess.gpaDistr}</td>
								<td class="text-center">${assess.statuString}</td>
								<td class="text-center">
									<c:if test="${assess.record_status==3}">
									<%--  <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"  data-target="#customerEditDialog" onclick="editINfo(${project.projectinfo_Id});">修改</a> --%>
									 <button type="button" href="javascript:;" onclick="info_edit('编辑','updateInfo.do?id=${assess.assessinfo_id}','4','','510')"
									 class="btn bg-olive btn-xs">修改</button>
									 </c:if>
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
			$(function(){
				$('.table-sort').dataTable({
					"aaSorting": [[ 1, "desc" ]],//默认第几个排序
					"bStateSave": true,//状态保存
					"aoColumnDefs": [
					  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
					  {"orderable":false,"aTargets":[0,7]}// 制定列不参与排序
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