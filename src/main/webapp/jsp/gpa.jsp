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

<body onload="addText()">
	<div class="four steps">
		<span onclick="javascript:window.history.back(-1);" class="disabled step">第一步 填写信息</span>
		<span onclick="javascript:window.history.back(-1);" class="active step">第二步 业绩点分配</span> 
		<span onclick="javascript:window.history.back(-1);" class=" step">第三步 提交凭证</span>
	</div>
	<article class="page-container">
		<form class="form form-horizontal" id="form-paper-add"
			action="${pageContext.request.contextPath}/paper/gpasave.do"
			method="post">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户组：</label> 
					<div class="formControls col-xs-4 col-sm-2">
					<input type="text" class="input-text" name="userteam_name"
					 id="useteam_name" disabled="disabled" value="${requestScope.userteam_name}">
					</div>
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>用户组人数：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input type="text" class="input-text"
					value="${requestScope.userteam_num}" placeholder=""
					id="userteam_num" disabled="disabled" name="userteam_num">
			</div>
			</div>
			<div class="row cl " id="input_conatiner"></div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户名：</label>
				<div class="formControls col-xs-4 col-sm-2">
					<span class="select-box"> <select name="paperinfo_Lev"
						class="select">
							<c:forEach items="${users}" var="user">
								<option value="${user.user_Id}">${user.user_name}</option>
							</c:forEach>
					</select>
					</span>
				</div>
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>身份：</label>
				<div class="formControls col-xs-4 col-sm-2">
					<input type="text" class="input-text radius size-S" value=""
						placeholder="" id="" name="paperinfo_Author">
				</div>
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>所获业绩点：</label>
				<div class="formControls col-xs-4 col-sm-2">
					<input type="text" class="input-text radius size-S" value=""
						placeholder="" id="" name="paperinfo_Name">
				</div>
			</div>
			<div id="div">
				<input type="button" value="增加" onclick="addText()" />
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onClick="deleteText();"
						class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 下一步
					</button>
					<button onClick="deleteText();" class="btn btn-secondary radius"
						type="button">
						<i class="Hui-iconfont">&#xe632;</i> 保存草稿
					</button>
					<button onClick="removeIframe();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
			<div id="dic">
				<input type="button" value="shanchu" onclick="deleteText()" />
			</div>

		</form>
	</article>

	<script type="text/javascript" src="../../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="../../static/h-ui.admin/js/H-ui.admin.page.js"></script> 


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
	    function addText(){
	        var div = document.getElementById("div");
	        var obj=document.getElementById("userteam_num");
	        var count=obj.value;
	        for(var i=0;i<count;i++){
	        	div.innerHTML += "<div class='row cl'> <label class='form-label col-xs-3 col-sm-2'>"
	    	        +"<span class='c-red'>*</span>用户名：</label>"
	    	        +"<div class='formControls col-xs-4 col-sm-2'> <span class='select-box'> "
                    +"<select name='user_name' id='name"+i+"' class='select'> <c:forEach items='${users}' var='user'>"
	    	        +"<option value='${user.user_Id}'>${user.user_name}</option> </c:forEach>"
	    	        +"</select> </span> "
	    	        +"</div> <label class='form-label col-xs-3 col-sm-2'><span class='c-red'>*</span>身份：</label>"+
	    	        "<div class='formControls col-xs-3 col-sm-2'>"
	    	        +"<input type='text' class='input-text radius size-S' value='0' placeholder='' id='identity"+i+"' name='paperinfo_Author'>"
	    	        +"</div> <label class='form-label col-xs-3 col-sm-2'><span class='c-red'>*</span>所获业绩点：</label>"
	    	        +"<div class='formControls col-xs-3 col-sm-2'>"
	    	        +"<input type='text' class='input-text radius size-S' value='2' placeholder='' id='getGpa"+i+"' name='paperinfo_Name'> </div>"
	    	        +"<input type='button' value='删除' onclick='deleteText(this)'></div>";
	        }
	        
	    }
	     function deleteText(){
	    	 var obj1=document.getElementById("userteam_num");
		     var count1=obj1.value;
		     var name="q";
		     var idarray=new Array();
		     var idenarray=new Array();
		     var gpaarray=new Array();
		     var userList = new Array();  
		     for(var j=0;j<count1;j++){
	    	 var user_id=document.getElementById("name"+j).value;
	    	 var user_identity=document.getElementById("identity"+j).value;
	    	 var user_getGpa=document.getElementById("getGpa"+j).value;
	    	 idarray.push(user_id);
	    	 idenarray.push(user_identity);
	    	 name+=gpaarray.push(gpaarray);
	    	 userList.push({user_Id:user_id,userteam_profession: user_identity,userteam_getGpa:user_getGpa});
	    	
		     }   
		     $.ajax({  
		         type: "POST",  
		         url: "${pageContext.request.contextPath}/paper/senddd.do",  
		         data: JSON.stringify(userList),//将对象序列化成JSON字符串  
		         dataType:"json",  
		         contentType : "application/json;charset=UTF-8", //设置请求头信息  
		         success: function(data){ 
		        	 alter("chenggong");
		         },  
		         error: function(res){   
		        	 alter("chucuole");
		         }  
		     });  
	     }


	    
	     
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>