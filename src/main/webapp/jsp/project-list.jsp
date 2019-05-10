
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<div class="page-container">
		<div class="text-c">
			日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
				id="datemin" class="input-text Wdate" style="width: 120px;">
			- <input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})"
				id="datemax" class="input-text Wdate" style="width: 120px;">
			<input type="text" class="input-text" style="width: 250px"
				placeholder="输入会员名称、电话、邮箱" id="" name="">
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="icon-search"></i> 搜用户
			</button>

		</div>

		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					删除记录</a> <a class="btn btn-primary radius" href="javascript:;"
				onclick="location.href='${pageContext.request.contextPath}/project/getSort.do'"><i
					class="Hui-iconfont">&#xe600;</i> 添加记录</a>
			</span> <span class="r">共有数据：<strong><c:out
						value="${fn:length(projectList)}"></c:out></strong> 条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered radius table-striped table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" value="" name=""></th>
						<th width="auto">#</th>
						<th width="auto">项目来源</th>
						<th width="auto">项目名称</th>
						<th width="auto">项目负责人</th>
						<th width="auto">开始时间</th>
						<th width="auto">完成时间</th>
						<th width="auto">立项经费</th>
						<th width="auto">总业绩点</th>
						<th width="auto">我的业绩点</th>
						<th width="auto">审核状态</th>
						<th width="auto">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${projectList}" var="project">
						<tr>
							<td><input name="ids" type="checkbox"varStatus="rr">
						    <td>${rr.index+1}</td>
							<td>${project.sys_Dict.name}</td>
							<td>${project.projectinfo_Name}</td>
							<td>${project.user.user_name}</td>
							<td><fmt:formatDate value="${project.projectinfo_StartTime}" pattern="yyyy-MM-dd"/></td>
							<td class="text-center"><fmt:formatDate value="${project.projectinfo_FinishTime}" pattern="yyyy-MM-dd"/></td>
							<td class="text-center">${project.projectinfo_StartMoney}</td>
							<td class="text-center">${project.projectinfo_getGpa}</td>
							<td class="text-center">${project.gpaDistr}</td>
							<td class="text-center">${project.statuString}</td>
							<td class="text-center">
								<%-- <button type="button" href="javascript:;" onclick="info_edit('编辑','updateInfo.do?id=${project.projectinfo_Id}','4','','510')" class="btn bg-olive btn-xs">修改</button> --%>
								<%-- <button type="button" class="btn bg-olive btn-xs" href="javascript:;" onclick="editINfo(${project.projectinfo_Id});"
									data-toggle="modal" data-target="#customerEditDialog"
									>修改</button> --%>
									<c:if test="${project.record_status==3}">
									<%--  <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"  data-target="#customerEditDialog" onclick="editINfo(${project.projectinfo_Id});">修改</a> --%>
									 <button type="button" href="javascript:;" onclick="info_edit('编辑','updateInfo.do?id=${project.projectinfo_Id}','4','','510')"
									 class="btn bg-olive btn-xs">修改</button>
									 </c:if>
								<button type="button"
									onclick="location.href='${pageContext.request.contextPath}/gpadistr/findAllGpa.do?id=${project.projectinfo_Id}'"
									class="btn bg-olive btn-xs">详情</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- 客户编辑对话框 -->
		<%-- <div class="modal fade" id="customerEditDialog" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="edit_info_form">
							<input type="hidden" class="input-text"
								value="${sessionScope.infoId}" placeholder="" readonly="true"
								id="projectinfo_Id" name="projectinfo_Id"> <input
								type="hidden" class="input-text radius size-S"
								value="${sessionScope.user.user_Id}" readonly="true"
								placeholder="" id="projectinfo_Leader" name="projectinfo_Leader">
							<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span
									class="c-red">*</span>项目名称：</label>
								<div class="formControls col-xs-3 col-sm-4">
									<input type="text" class="input-text radius size-S" value=""
										placeholder="" id="projectinfo_Name" required="true"
										name="projectinfo_Name">
								</div>
								<label class="form-label col-xs-4 col-sm-2"><span
									class="c-red">*</span>类别：</label>
								<div class="formControls col-xs-3 col-sm-4">
									<span class="select-box"> <select id="paperinfo_sort"
										name="paperinfo_sort" class="select">
											<option value="0">自然科学</option>
											<option value="1">人文科学</option>
									</select>
									</span>
								</div>
							</div>
							<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span
									class="c-red">*</span>项目来源：</label>
								<div class="formControls col-xs-3 col-sm-4">
									<span class="select-box"> <select
										id="projectinfo_origin" name="projectinfo_origin"
										class="select">
											<c:forEach items="${projectParas}" var="para">
												<option value="${para.researchlevel_Id}">${para.researchlevel_sort}</option>
											</c:forEach>
									</select>
									</span>
								</div>
								<label class="form-label col-xs-4 col-sm-2"><span
									class="c-red">*</span>开始日期：</label>
								<div class="formControls col-xs-8 col-sm-4">
									<input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
										id="projectinfo_StartTime" class="input-text Wdate"
										name="projectinfo_StartTime">
								</div>
							</div>
							<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span
									class="c-red">*</span>结束日期：</label>
								<div class="formControls col-xs-3 col-sm-4">
									<input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
										id="projectinfo_FinishTime" class="input-text Wdate"
										name="projectinfo_FinishTime">
								</div>
								<label class="form-label col-xs-4 col-sm-2"><span
									class="c-red">*</span>立项经费：</label>
								<div class="formControls col-xs-8 col-sm-4">
									<input type="text" class="input-text" value=""
										placeholder="上限为200万" required="true"
										id="projectinfo_StartMoney" name="projectinfo_StartMoney">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary"
							onclick="updateCustomer()">保存修改</button>
					</div>
				</div>
			</div>
		</div> --%>
		<jsp:include page="frame/footer.jsp"></jsp:include>

		<!--请在下方写此页面业务相关的脚本-->
		<script type="text/javascript">
		function info_edit(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}
		function checklev_edit(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}
			$(function(){
				$('.table-sort').dataTable({
					"aaSorting": [[ 1, "desc" ]],//默认第几个排序
					"bStateSave": true,//状态保存
					"aoColumnDefs": [
					  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
					  {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
					]
				});
				
			});
			
			//使用对话框修改方式
			function editINfo(id){
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/project/updateInfo.do",
				data:{"id":id},
				success:function(data) {
					$("#projectinfo_Id").val(data.projectinfo_Id);
					$("#projectinfo_Leader").val(data.projectinfo_Leader);
					$("#projectinfo_Name").val(data.projectinfo_Name)
					$("#paperinfo_sort").val(data.paperinfo_sort)
					$("#projectinfo_origin").val(data.projectinfo_origin)
					$("#projectinfo_StartTime").val(data.projectinfo_StartTime);
					$("#projectinfo_FinishTime").val(data.projectinfo_FinishTime);
					$("#projectinfo_StartMoney").val(data.projectinfo_StartMoney);
				}
			});
		}
			//直接使用添加流程解决方式
			/* function info_edit(title,url,id,w,h){
				layer_show(title,url,w,h);
			} */
			
			</script>
</body>
</html>