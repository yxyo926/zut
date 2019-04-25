<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@page import="cn.gpa.zut.domain.*"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="frame/meta.jsp"></jsp:include>
<title>项目录入 - 科研项目管理</title>
<meta name="keywords" content="中原工学院业绩管理系统V1.0">
<meta name="description" content="中原工学院业绩管理系统V1.0">
</head>

<body>
	<div class="four steps">
		<span class="step">第一步 填写信息</span> <span class="step">第二步 业绩点分配</span>
		<span class="active step">第三步 提交凭证</span>
	</div>
	<article class="page-container">
		<form class="form form-horizontal" id="form-paper-add"
			action="${pageContext.request.contextPath}/${sessionScope.sort}/recordsave.do"
			method="post" enctype="multipart/form-data">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>记录编号：</label>
				<div class="formControls col-xs-4 col-sm-2">
					<input type="text" class="input-text" name="record_Id"
						id="record_id" readonly="true" value="${sessionScope.recordId}">
				</div>
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>信息编号：</label>
				<div class="formControls col-xs-4 col-sm-2">
					<input type="text" class="input-text"
						value="${sessionScope.infoId}" placeholder="" id="info_id"
						readonly="true" name="recordinfo_id">
				</div>
			</div>
			<div class="row cl " id="input_conatiner"></div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户名：</label>
				<div class="formControls col-xs-4 col-sm-2">
					<input type="text" class="input-text radius size-S"
						value="${sessionScope.user.user_name}" placeholder="" id=""
						name="paperinfo_N"> <input type="hidden"
						value="${sessionScope.user.user_Id}" name="ni">
					<%-- <span class="select-box"> <select name="paperinfo_Lev"
						class="select">
								<option value="${sessionScope.user.user_Id}">${sessionScope.user.user_name}</option>
					</select>
					</span> --%>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>类别：</label>
				<div class="formControls col-xs-4 col-sm-2">
					<input type="text" class="input-text radius size-S"
						value="sci_${sessionScope.sort}info" placeholder="" id=""
						name="category_Id">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>上传凭证：</label>
				<!-- <div class="formControls col-xs-4 col-sm-2">
					<input type="text" class="input-text radius size-S" value=""
						placeholder="" id="proof" name="record_proof">
				</div> -->

				<span class="btn-upload form-group"> <input
					class="input-text upload-url radius" type="text"
					name="uploadfile-1" id="uploadfile-1" readonly><a
					href="javascript:void();" class="btn btn-primary radius"><i
						class="iconfont"></i> 浏览文件</a> <input type="file" multiple name="file"
					class="input-file">
				</span>
			</div>
			
			<div id="div"></div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onClick="deleteText();" class="btn btn-primary radius"
						type="submit">
						<i class="Hui-iconfont">&#xe632;</i>保存
					</button>
					<button onClick="deleteText()" class="btn btn-secondary radius"
						type="button">
						<i class="Hui-iconfont">&#xe632;</i> 保存草稿
					</button>
					<button onClick="removeIframe();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
		
	</article>

	<jsp:include page="frame/footer.jsp"></jsp:include>


	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/webuploader/0.1.5/webuploader.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/ueditor/1.4.3/ueditor.config.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/ueditor/1.4.3/ueditor.all.min.js">
		
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
		function deleteText() {
			var record_id = document.getElementById("record_id").value;
			var info_id = document.getElementById("info_id").value;
			var proof = document.getElementById("proof").value;
			userList.push({
				record_Id : record_id,
				recordinfo_id : info_id,
				record_proof : proof
			});
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/paper/recordsave.do",
				data : JSON.stringify(userList),//将对象序列化成JSON字符串  
				dataType : "json",
				contentType : "application/json;charset=UTF-8", //设置请求头信息  
				success : function(data) {
					alter("chenggong");
				},
				error : function(res) {
					alter("chucuole");
				}
			});
		}
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>