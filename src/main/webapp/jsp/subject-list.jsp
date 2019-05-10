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
				onclick="location.href='${pageContext.request.contextPath}/subject/getSort.do'"><i
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
						<th width="auto">信息编号</th>
						<th width="auto">学科建设名称</th>
						<th width="auto">主持人</th>
						<th with="auto">建设类别</th>
						<th with="auto">建设级别</th>
						<th with="auto">开始时间</th>
						<th width="auto">结束时间</th>
						<th width="auto">验收等级</th>
						<th with="auto">总业绩点</th>
						<th with="auto">我的业绩点</th>
						<th with="auto">审核状态</th>
						<th with="auto">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${projectList}" var="subject">
						<tr>
							<td><input name="ids" type="checkbox"></td>
							<td>${subject.subjectinfo_id }</td>
							<td>${subject.subjectinfo_name}</td>
							<td>${subject.user.user_name}</td>
							<td>${subject.performance_Type.tname}</td>
							<td>${subject.sys_Dict.name}</td>
							<td><fmt:formatDate value="${subject.subjectinfo_starttime}" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${subject.subjectinfo_finishtime}" pattern="yyyy-MM-dd"/></td>
							<td>${subject.sys_Ratio.ratio_name }</td>
							<td>${subject.subjectinfo_getGpa}</td>
							<td class="text-center">${subject.gpaDistr}</td>
							<td class="text-center">${subject.statuString},${subject.record_status}</td>
							<td class="text-center">
								<c:if test="${subject.record_status==3}">
								<button type="button" href="javascript:;"
									onclick="info_edit('编辑','updateInfo.do?id=${subject.subjectinfo_id}','4','','510')"
									 class="btn bg-olive btn-xs">修改</button>
								</c:if >
								<%-- <a href="javascript:void(0);" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#customerEditDialog" onclick="alter()" onclick="addchecklev1(${subject.subjectinfo_id});">考核</a> --%>
								<c:if test="${subject.record_status!=2}">
								<button type="button" href="javascript:;" class="btn btn-primary btn-xs"  onclick="addchecklev1('${subject.subjectinfo_id}');" data-toggle="modal" data-target="#customerEditDialog">考核</button>
								</c:if >
								<button type="button" 
									onclick="location.href='${pageContext.request.contextPath}/gpadistr/findAllGpa.do?id=${subject.subjectinfo_id }'"
									class="btn bg-olive btn-xs">详情</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!--考核对话框 -->
		<div class="modal fade" id="customerEditDialog" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">添加考核信息</h4>
					</div>
					<div class="modal-body">
					<form class="form-horizontal" id="form" action="${pageContext.request.contextPath}/subject/checksave.do">
							<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span
									class="c-red">*</span>信息编号：</label>
								<div class="formControls col-xs-8 col-sm-4">
									<input type="text" class="input-text"
										value="" placeholder=""
										readonly="true" id="subjectinfo_id" name="subjectinfo_id">
								</div>
							</div>
							<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span
									class="c-red">*</span>申报人：</label>
								<div class="formControls col-xs-3 col-sm-4">
									<input type="text" class="input-text radius size-S"
										value="${sessionScope.user.user_name}" readonly="true"
										placeholder="" id="" name="paperinfo_Author"> <input
										type="hidden" class="input-text radius size-S"
										value="${sessionScope.user.user_Id}" readonly="true"
										placeholder="" id="" name="subjectinfo_uname">
								</div>
							</div>
							<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span
									class="c-red">*</span>名称：</label>
								<div class="formControls col-xs-3 col-sm-4">
									<input type="text" class="input-text radius size-S" value=""
										placeholder="" readonly="true" id="subjectinfo_name" name="subjectinfo_name">
								</div>
							</div>
							<div class="row cl">
								<label class="form-label col-xs-4 col-sm-2"><span
									class="c-red">*</span>考核/验收级别：</label>
								<div class="formControls col-xs-6 col-sm-4">
									<span class="select-box"> <select name="subjectinfo_checklev" id="subjectinfo_checklev" class="select">
									       <option value="---请选择---"></option>
											<c:forEach items="${sessionScope.dictRatios}" var="para">
												<option value="${para.ratio_id}">${para.ratio_name}</option>
											</c:forEach>
									</select>
									</span>
								</div>
							</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button onClick="savechecklev();"
						class="btn btn-primary radius"  data-dismiss="modal">
						<i class="Hui-iconfont">&#xe632;</i> 保存并提交审核
					</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="frame/footer.jsp"></jsp:include>

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
		function info_edit(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}
		function checklev_edit(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}
		function addchecklev1(uinfoid){
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/subject/addchecklev.do",
				data:{"infoid":uinfoid},
				success:function(data){
					$("#subjectinfo_id").val(data.subjectinfo_id);
					$("#subjectinfo_name").val(data.subjectinfo_name);
				}
			});
		}
		function savechecklev(){
			var id=$("#subjectinfo_id").val();
			var lev=$("#subjectinfo_checklev").val();
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/subject/savechecklev.do",
				data:{"subjectinfo_id":id,"subjectinfo_checklev":lev},
				success:function(data){
					confirm(data);
					if (data) {
						/* window.location.href = ''; */
						location.href='${pageContext.request.contextPath}/subject/gpadistribute.do';
				}
				}
			});	
		}
		$(function() {
			$('.table-sort').dataTable({
				"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
				"bStateSave" : true,//状态保存
				"aoColumnDefs" : [
				//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
				{
					"orderable" : false,
					"aTargets" : [ 0, 8, 9 ]
				} // 制定列不参与排序
				]
			});

		});
		
	</script>
</body>
</html>