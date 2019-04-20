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
					onclick="location.href='${pageContext.request.contextPath}/paper/getSort.do'"><i
						class="Hui-iconfont">&#xe600;</i> 添加记录</a>
				</span> <span class="r">共有数据：<strong><c:out
							value="${fn:length(paperList)}"></c:out></strong> 条
				</span>
			</div>
	<div class="mt-20">
				<table
					class="table table-border table-bordered table-hover table-bg table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" value="" name=""></th>
							<th width="40">信息编号</th>
							<th width="40">论文作者</th>
							<th width="40">论文名称</th>
							<th width="40">期刊名称</th>
							<th with="70">期刊级别</th>
							<th width="40">CN号</th>
							<th width="70">ISSN号</th>
							<th width="70">发表时间</th>
							<th with="70">组织级别</th>
							<th with="70">总业绩点</th>
							<th with="70">我的业绩点</th>
							<th with="70">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${paperList}" var="paper">
							<tr>
								<td><input name="ids" type="checkbox"></td>
								<td>${paper.paperinfo_Id }</td>
								<td>${paper.user.user_name}</td>
								<td>${paper.paperinfo_Name}</td>
								<td>${paper.paperinfo_MName}</td>
								<td>${paper.dictPara.dictpara_lev}</td>
								<td>${paper.paperinfo_CN }</td>
								<td>${paper.paperinfo_ISSN }</td>
								<td class="text-center">${paper.paperinfo_Time }</td>
								<td class="text-center">${paper.dictRatio.dictratio_lev }</td>
								<td class="text-center">${paper.paperinfo_getGpa }</td>
								<td class="text-center">${paper.gpaDistr}</td>
								<td class="text-center">
									<button type="button" class="btn bg-olive btn-xs">修改</button>
									<button type="button" onclick="location.href='${pageContext.request.contextPath}/paper/findByPaper.do?id=${paper.paperinfo_Id}'" class="btn bg-olive btn-xs">详情</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<jsp:include page="frame/footer.jsp"></jsp:include>

			<!--请在下方写此页面业务相关的脚本-->
			<script type="text/javascript">
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