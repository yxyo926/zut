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
		<span class="disabled step">第一步 填写信息</span> <span class="active step">第二步
			业绩点分配</span> <span class=" step">第三步 提交凭证</span>
	</div>
	<article class="page-container">
		 <form class="form form-horizontal" id="form"
			action="${pageContext.request.contextPath}/${sessionScope.sort}/gpasave.do"
			onsubmit="checkgpa();" method="post"> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户组：</label> 
					<div class="formControls col-xs-4 col-sm-2">
					<input type="text" class="input-text" name="userteam_name"
					 id="useteam_name" readonly="true" value="${sessionScope.Userteam.userteam_name}">
					</div>
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>用户组人数：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input type="text" class="input-text"
					value="${sessionScope.Userteam.userteam_num}" placeholder=""
					id="userteam_num" readonly="true" name="userteam_num">
			</div>
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>总业绩点：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input type="text" class="input-text"
					value="${sessionScope.totalGpa}" placeholder=""
					id="totalgpa" readonly="true" name="userteam_num">
			</div>
			</div>
			<%-- <div class="row cl " id="input_conatiner"></div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>用户名：</label>
				<div class="formControls col-xs-4 col-sm-2">
				<input type="text" class="input-text radius size-S" value="${sessionScope.user.user_name}"
						placeholder="" id="" name="paperinfo_N">
						<input type="hidden" value="${sessionScope.user.user_Id}" name="paperinfo_Name">
					<span class="select-box"> <select name="paperinfo_Lev"
						class="select">
								<option value="${sessionScope.user.user_Id}">${sessionScope.user.user_name}</option>
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
			</div> --%>
			<div id="div">
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="paper_save_submit();"
						class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 保存并提交审核
					</button>
				<button onClick="deleteText()" class="btn btn-secondary radius"
						type="button">
						<i class="Hui-iconfont">&#xe632;</i> 保存
					</button>
					<button onClick="window.location.href='${pageContext.request.contextPath}/record/show.do'"
						class="btn btn-primary radius" >
						<i class="Hui-iconfont">&#xe632;</i> 下一步
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
	    function addText(){
	        var div = document.getElementById("div");
	        var obj=document.getElementById("userteam_num");
	        var count=obj.value;
	        for(var i=0;i<count;i++){
	        	div.innerHTML += "<div class='row cl'> <label class='form-label col-xs-3 col-sm-2'>"
	    	        +"<span class='c-red'>*</span>用户名：</label>"
	    	        +"<div class='formControls col-xs-4 col-sm-2'> <span class='select-box'> "
                    +"<select name='gpaDistrs["+i+"].user_Id' id='name"+i+"' class='select'> <c:forEach items='${users}' var='user'>"
	    	        +"<option value='${user.user_Id}'>${user.user_name}</option> </c:forEach>"
	    	        +"</select> </span> "
	    	        +"</div> <label class='form-label col-xs-3 col-sm-2'><span class='c-red'>*</span>身份：</label>"+
	    	        "<div class='formControls col-xs-3 col-sm-2'>"
	    	        +"<input type='text' class='input-text radius size-S' value='0' placeholder='' id='identity"+i+"' name='gpaDistrs["+i+"].userteam_profession'>"
	    	        +"</div> <label class='form-label col-xs-3 col-sm-2'><span class='c-red'>*</span>所获业绩点：</label>"
	    	        +"<div class='formControls col-xs-3 col-sm-2'>"
	    	        +"<input placeholder='请输入数字' onkeyup='value=value.replace(/[^\d.]/g,'')' class='input-text radius size-S' value='2' placeholder='' id='getGpa"+i+"' name='gpaDistrs["+i+"].userteam_getGpa'>";
	        }
	        
	    }
	    $("#div input[name='gpaDistrs["+i+"].userteam_getGpa'>']").keyup(function(){
    		 var c=$(this);  
            if(/[^\d]/.test(c.val())){//替换非数字字符  
             var temp_amount=c.val().replace(/[^\d]/g,'');  
             $(this).val(temp_amount);  
            }  
    	})
	    function checkgpa(){
	    	var obj2=document.getElementById("userteam_num");
	    	var totalgpa1=document.getElementById("totalgpa").value;
		     var count3=obj2.value;
		     var total=0.0;
		     for(var x=0;x<count3;x++){
	    	var user_getGpa=document.getElementById("getGpa"+x).value;
	    	total=total+user_getGpa;
		     }
		     if(total>totalgpa1){
		    	 alter("业绩分配超额请重新分配");
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
		         url: "${pageContext.request.contextPath}/paper/gpasave.do",  
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