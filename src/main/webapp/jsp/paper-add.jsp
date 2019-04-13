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
		<span class="active step">第一步 填写信息</span> <span class="step">第二步 业绩点分配</span> <span
			class="disabled step">第三步 提交凭证</span> 
	</div>
	<article class="page-container">
		<form class="form form-horizontal" id="form-paper-add"
			action="${pageContext.request.contextPath}/paper/save.do"
			method="post">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>信息编号：</label>
				<div class="formControls col-xs-8 col-sm-3">
					<input type="text" class="input-text" value="1" placeholder=""
						id="" name="paperinfo_Id">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>申报人：</label>
				<div class="formControls col-xs-3 col-sm-3">
					<input type="text" class="input-text radius size-S" value=""
						placeholder="" id="" name="paperinfo_Author">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>论文名称：</label>
				<div class="formControls col-xs-3 col-sm-3">
					<input type="text" class="input-text radius size-S" value=""
						placeholder="" id="" name="paperinfo_Name">
				</div>
			</div>
			<div class="row cl">
				<!-- <label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>论文类别：</label>
				<div class="formControls col-xs-8 col-sm-3">
					<span class="select-box"> <select name="paperinfo_sort" class="select">
							<option value="1">自然科学</option>
							<option value="2">人文科学</option>
					</select>
					</span>
				</div> -->

				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>论文级别：</label>
				<div class="formControls col-xs-6 col-sm-3">
					<span class="select-box"> <select name="paperinfo_Lev"
						class="select">
							<c:forEach items="${dictParas}" var="para">
								<option value="${para.dictpara_id}">${para.dictpara_lev}</option>
							</c:forEach>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>组织级别：</label>
				<div class="formControls col-xs-6 col-sm-3">
					<span class="select-box"> <select name="paperinfo_orglev"
						class="select">
							<c:forEach items="${dictRatios}" var="ratio">
								<option value="${ratio.dictratio_id}">${ratio.dictratio_lev}</option>
							</c:forEach>
					</select>
					</span>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>CN号：</label>
				<div class="formControls col-xs-8 col-sm-3">
					<input type="text" class="input-text" value="" placeholder="" id=""
						name="paperinfo_CN">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>ISSN号：</label>
				<div class="formControls col-xs-8 col-sm-3">
					<input type="text" class="input-text" value="" placeholder="" id=""
						name="paperinfo_ISSN">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>发表日期：</label>
				<div class="formControls col-xs-8 col-sm-3">
					<input type="text"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
						id="datemin" class="input-text Wdate" name="paperinfo_Time">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户组：</label>
				<div class="formControls col-xs-6 col-sm-3">
					<span class="select-box"> <select name="userteam" class="select"
						id="useteam_name" onchange="bangnum()">
							<c:forEach items="${userteams}" var="userteam">
								<option value="${userteam.userteam_num}">${userteam.userteam_name}</option>
							</c:forEach>
					</select>
					</span>
				</div>
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户组人数：</label>
				<div class="formControls col-xs-8 col-sm-3">
					<input type="text" class="input-text" value="" placeholder=""
						id="userteamnum" name="">

				</div>
			</div>


			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">凭证：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor" type="text/plain"
						style="width: 100%; height: 200px;"></script>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onClick="article_save_submit();"
						class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 保存并提交审核
					</button>
					<button onClick="article_save();" class="btn btn-secondary radius"
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
		function bangnum() {
			document.getElementById('userteamnum').value = document
					.getElementById('useteam_name').value;
		}

		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});

			$list = $("#fileList"), $btn = $("#btn-star"), state = "pending",
					uploader;

			var uploader = WebUploader.create({
				auto : true,
				swf : 'lib/webuploader/0.1.5/Uploader.swf',

				// 文件接收服务端。
				server : 'fileupload.php',

				// 选择文件的按钮。可选。
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.
				pick : '#filePicker',

				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
				resize : false,
				// 只允许选择图片文件。
				accept : {
					title : 'Images',
					extensions : 'gif,jpg,jpeg,bmp,png',
					mimeTypes : 'image/*'
				}
			});
			uploader
					.on(
							'fileQueued',
							function(file) {
								var $li = $('<div id="' + file.id + '" class="item">'
										+ '<div class="pic-box"><img></div>'
										+ '<div class="info">'
										+ file.name
										+ '</div>'
										+ '<p class="state">等待上传...</p>'
										+ '</div>'), $img = $li.find('img');
								$list.append($li);

								// 创建缩略图
								// 如果为非图片文件，可以不用调用此方法。
								// thumbnailWidth x thumbnailHeight 为 100 x 100
								uploader.makeThumb(file, function(error, src) {
									if (error) {
										$img.replaceWith('<span>不能预览</span>');
										return;
									}

									$img.attr('src', src);
								}, thumbnailWidth, thumbnailHeight);
							});
			// 文件上传过程中创建进度条实时显示。
			uploader
					.on(
							'uploadProgress',
							function(file, percentage) {
								var $li = $('#' + file.id), $percent = $li
										.find('.progress-box .sr-only');

								// 避免重复创建
								if (!$percent.length) {
									$percent = $(
											'<div class="progress-box"><span class="progress-bar radius"><span class="sr-only" style="width:0%"></span></span></div>')
											.appendTo($li).find('.sr-only');
								}
								$li.find(".state").text("上传中");
								$percent.css('width', percentage * 100 + '%');
							});

			// 文件上传成功，给item添加成功class, 用样式标记上传成功。
			uploader.on('uploadSuccess', function(file) {
				$('#' + file.id).addClass('upload-state-success')
						.find(".state").text("已上传");
			});

			// 文件上传失败，显示上传出错。
			uploader.on('uploadError', function(file) {
				$('#' + file.id).addClass('upload-state-error').find(".state")
						.text("上传出错");
			});

			// 完成上传完了，成功或者失败，先删除进度条。
			uploader.on('uploadComplete', function(file) {
				$('#' + file.id).find('.progress-box').fadeOut();
			});
			uploader.on('all', function(type) {
				if (type === 'startUpload') {
					state = 'uploading';
				} else if (type === 'stopUpload') {
					state = 'paused';
				} else if (type === 'uploadFinished') {
					state = 'done';
				}

				if (state === 'uploading') {
					$btn.text('暂停上传');
				} else {
					$btn.text('开始上传');
				}
			});

			$btn.on('click', function() {
				if (state === 'uploading') {
					uploader.stop();
				} else {
					uploader.upload();
				}
			});

			var ue = UE.getEditor('editor');

		});
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>