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
<body onload="bangnum()">
	<div class="four steps">
		<span class="active step">第一步 填写信息</span> <span class="step">第二步 业绩点分配</span> <span
			class="disabled step">第三步 提交凭证</span> 
	</div>
	<article class="page-container">
		<form class="form form-horizontal" id="paperaddform"
			action="${pageContext.request.contextPath}/paper/save.do"
			method="post">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>信息编号：</label>
				<div class="formControls col-xs-8 col-sm-4">
					<input type="text" class="input-text" value="${sessionScope.infoID}" placeholder=""
						readonly="true" id="" name="paperinfo_Id">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>申报人：</label>
				<div class="formControls col-xs-3 col-sm-4">
					<input type="text" class="input-text radius size-S" value="${sessionScope.user.user_name}"
						readonly="true"  placeholder="" id="" name="paperinfo_A">
						<input type="hidden" class="input-text radius size-S" value="${sessionScope.user.user_Id}"
						readonly="true"  placeholder="" id="" name="paperinfo_Author">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>论文名称：</label>
				<div class="formControls col-xs-3 col-sm-4">
					<input type="text" class="input-text radius size-S" value="${sessionScope.object.paperinfo_Name}"
						placeholder="" id="paperinfo_Name" name="paperinfo_Name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>论文类别：</label>
				<div class="formControls col-xs-8 col-sm-3">
					<span class="select-box"> <select id="paperinfo_sort" name="paperinfo_sort" class="select">
							<option value="0">---请选择---</option>
							<option value="自然科学">自然科学</option>
							<option value="人文社科">人文社科</option>
					</select>
					</span>
				</div> 
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>论文级别：</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span class="select-box"> <select id="paperinfo_Lev" name="paperinfo_Lev"
						class="select">
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>署名级别：</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span class="select-box"> <select id="paperinfo_orglev" name="paperinfo_orglev"
						class="select">
						<option value="${sessionScope.object.paperinfo_orglev}">---请选择---</option>
							<c:forEach items="${sessionScope.dictRatios}" var="ratio">
								<option value="${ratio.ratio_id}">${ratio.ratio_name}</option>
							</c:forEach>
					</select>
					</span>
				</div>
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>期刊名称：</label>
				<div class="formControls col-xs-3 col-sm-4">
					<input type="text" class="input-text radius size-S" value="${sessionScope.object.paperinfo_MName}"
						placeholder="" id="paperinfo_MName" name="paperinfo_MName">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>CN号：</label>
				<div class="formControls col-xs-8 col-sm-4">
					<input type="text" class="input-text" value="${sessionScope.object.paperinfo_CN}" placeholder="" id="paperinfo_CN"
							 required="true" name="paperinfo_CN">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>ISSN号：</label>
				<div class="formControls col-xs-8 col-sm-4">
					<input type="text" class="input-text" value="${sessionScope.object.paperinfo_ISSN}" placeholder="" id="paperinfo_ISSN"
						required="true" name="paperinfo_ISSN">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>发表日期：</label>
				<div class="formControls col-xs-8 col-sm-4">
					<input type="text"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						id="datemin" value="${sessionScope.object.paperinfo_Time}" class="input-text Wdate" name="paperinfo_Time">
				</div>
			</div>
			<%-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户组：</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span class="select-box"> <select name="userteam_name" class="select"
						id="useteam_name" onchange="bangnum(this)">
							<c:forEach items="${userteams}" var="userteam">
								<option parentcode="${userteam.userteam_num}" value="${userteam.userteam_name}">${userteam.userteam_name}</option>
							</c:forEach>
					</select>
					</span>
				</div>
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户组人数：</label>
				<div class="formControls col-xs-8 col-sm-4">
					<input type="text" class="input-text" value="" placeholder=""
						id="userteam_num" required="true" name="userteam_num">

				</div>
			</div> --%>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户组：</label>
					<!-- datalist解决方案，大部分浏览器不支持 -->
				 <div class="formControls col-xs-6 col-sm-4">
					<input type="text"   required="true" list="userteam_name_list" class="input-text"  name="userteam_name" onchange="bangnum(this)" />
					<datalist id="userteam_name_list"> 
					<option parentcode=""
								value="单人">人数：1</option>
					<c:forEach items="${userteams}" var="userteam">
							<option parentcode="${userteam.userteam_num}"
								value="${userteam.userteam_name}">人数：${userteam.userteam_num}</option>
					</c:forEach>

					</datalist>
					</div> 
					<!--  多选框解决方案-->
					<%-- <span class="select-box"> <select name="userteam_name"
						class="select" id="useteam_name" onchange="bangnum(this)">
							<c:forEach items="${userteams}" var="userteam">
								<option parentcode="${userteam.userteam_num}"
									value="${userteam.userteam_name}">${userteam.userteam_name}</option>
							</c:forEach>
					</select>
					</span> --%>
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户组人数：</label>
				<div class="formControls col-xs-8 col-sm-4">
					<input type="text" class="input-text" value="" placeholder="创建新小组时必填"
						id="userteam_num" name="userteam_num">

				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onClick="paper_save_submit();"
						class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 保存并提交审核
					</button>
					<button onClick="paper_save();" class="btn btn-secondary radius"
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
		src="${pageContext.request.contextPath}/lib/ueditor/1.4.3/ueditor.all.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
	
		function bangnum() {
			document.getElementById('userteam_num').value=$(id).find("option:selected").attr("parentcode");
		}	
		
		$('#paperinfo_sort').change(function(){
			 $.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/paper/getgpasort.do",
					data:{"id": $('#paperinfo_sort').val()},
					scriptCharset: 'utf-8',
					success:function(data) {
						console.log(data);                  
	                       if(data!=null){
	                    	   $("#paperinfo_Lev").empty();
	                    	 /*   $("#paperinfo_Lev").empty(); */
	                           $("#paperinfo_Lev").prepend("<option value='0'>请选择</option>");
	                          /*  var jsarr = JSON.parse(data); */
	                          var jsarr = data;
	                           //遍历
	                            $.each(jsarr,function(i,item){
	                                  $.each(item,function(j,val){
	                                      $("#paperinfo_Lev").append("<option value="+val.id+">"+val.name+"</option>");                                     
	                                  })

	                              }); 
	                       }                  

						
					}
				});
	        });
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>