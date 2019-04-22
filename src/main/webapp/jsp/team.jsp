<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/static/h-ui.admin/skin/default/skin.css"id="skin" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/static/h-ui.admin/css/style.css" />

<title>创建小组</title>

</head>

<body>
	<article class="page-container">
	<form class="form form-horizontal" >
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>小组名称：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input id="team_name" type="text" class="input-text radius size-S" value="">
			</div>
		</div>
	
		
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">院系：</label>
				<div class="formControls col-xs-6 col-sm-3" style="width: 200px;">
					<span class="select-box"> 
					<select id="team_category" class="select" onchange="ByCollege(this.value)">
					        <option >-----请选择学院-----</option>
							<option value="软件学院">软件学院</option>
							<option value="理学院">理学院</option>
							<option value="计算机学院">计算机学院</option>
					</select>
					</span>
				</div >
				
				<div class="formControls col-xs-8 col-sm-3" style="width: 200px;">
					<input type="text" class="input-text radius size-S" id="sousuo_text" style="text-decoration: hint" value="按姓名搜索">
				</div>
				<button id="ss" class="btn btn-primary radius" type="button" onclick="ByName()">搜索</button>
				
							
		</div>

		<table id="UserTable"
				class="table table-border table-bordered table-hover table-bg table-sort" style="width: 1025px;margin:20px auto">
				<thead>
					<tr class="text-c">
						<th width="25">序号</th>
						<th width="200">教师工号</th>
						<th width="200">教师名称</th>
						<th width="200">所属院系</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody  id="UserTableBody">
					<c:forEach items="${list_user}" var="list_user" varStatus="x" begin="0">
						<tr>
							<td class="td">${x.index+1}</td>
							<td>${list_user.user_Id }</td>
							<td>${list_user.user_name }</td>
							<td>${list_user.user_college }</td>
							<td class="text-center">
								<button  type="button" class="btn bg-olive btn-xs" onclick="Add_Zudui(this)" id="${x.index+1}">组队</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<table id="Team"
				class="table table-border table-bordered table-hover table-bg table-sort" style="width: 1025px;margin:20px auto">
				<thead>
					<tr class="text-c" >
						<th width="20">序号</th>
						<th width="300">组编号</th>
						<th width="300">教师工号</th>
						<th width="300">教师名称</th>
						<th width="300">身份</th>
						<th width="300">所属院系</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody id="Teambody" varStatus="x" begin="0">
						<tr>
							<td class="td">${x.index+1}</td>
							<td>123</td>
							<td>111111</td>
							<td>李四</td>
							<td>组长</td>
							<td>软件学院</td>
							<td class="text-center">
								<button type="button" class="btn bg-olive btn-xs" onclick="delete_user(this)" >删除</button>
							</td>
						</tr>
				</tbody>
			</table>
			
			


		<div class="row cl" style="margin:0;auto">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button id="commit"
					class="btn btn-primary radius" onclick="Add_Team()" type="button">创建小组</button>
				
				<button onClick="removeIframe();" class="btn btn-default radius"
					type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
		
	</form>
</article>
	<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script>

<script type="text/javascript"src="../lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript">
$(function (){
	$("#buzou2").click(function (){
		window.location.href="${pageContext.request.contextPath}/jy_project/findAll_category.do"
	});
	
})
//创建小组
function Add_Team(){
	
	var team_name=document.getElementById("team_name").value;
	var team_member=document.getElementById("team_name").value;
	var data=getTableContent();
	/* alert(team_name+data); */
	alert(data[1]);
	
	$.ajax({
		type:'POST',
		url:"${pageContext.request.contextPath}/team/AddTeam.do",
		data:data,
		
		success:function(result){
			
		
		}
	})
}
/** 
 * 遍历表格内容返回数组
 * @param  Int   id 表格id
 * @return Array
 */
function getTableContent(){
    var mytable = document.getElementById("Team");
    var data = [];
    var row=mytable.rows.length;
    for(var i=1; i<row; i++){
    	var cells=mytable.rows[i].cells.length;
        for(var j=0; j<cells-1; j++){
            if(!data[i]){
                data[i] = new Array();
            }
            data[i][j] = mytable.rows[i].cells[j].innerText;
        }
    }
    return data;
}




//删除组员
function delete_user(obj){
	 var tr=obj.parentNode.parentNode;
     var tbody=tr.parentNode;
     tbody.removeChild(tr);
   /*   //只剩行首时删除表格
     if(tbody.rows.length==1) {
         tbody.parentNode.removeChild(tbody);
     } */
	
}

function Add_Zudui(abj){
	
	var id = abj.id;  //获取当前行的对象  id属性
/* 	var idd=document.getElementById("Team").rows[0].cells[0].innerText; */
	var ids = document.getElementById("UserTable").rows[id].cells[0].innerText; //获取当前行的第一列值
	var user_id = document.getElementById("UserTable").rows[id].cells[1].innerText;//获取当前行的第二列值
	var user_name = document.getElementById("UserTable").rows[id].cells[2].innerText;//获取当前行的第四列值
	var user_college = document.getElementById("UserTable").rows[id].cells[3].innerText;//获取当前行的第五列值
/* 	
	<th width="20">序号</th>
	<th width="300">组编号</th>
	<th width="300">教师工号</th>
	<th width="300">教师名称</th>
	<th width="300">身份</th>
	<th width="300">所属院系</th>
	<th width="100">操作</th> */
	
		 var row = document.createElement('tr'); //创建行  
	       
	     var idCell = document.createElement('td'); //创建第1列id  
		 /*  idCell.innerHtml=n++; */
	     row.appendChild(idCell);
	       
	     var teamidCell = document.createElement('td');//创建第2列组编号  
	     teamidCell.innerHTML = 222;  
	     row.appendChild(teamidCell);  
	       
	 	var useridCell = document.createElement('td'); //创建第4列教师工号 
		useridCell.innerHTML = user_id;  
	     row.appendChild(useridCell); //加入行  ，下面类似  
	       
	     var usernameCell = document.createElement('td');//创建第5列教师名称  
	     usernameCell.innerHTML = user_name;  
	     row.appendChild(usernameCell);  
	       
	     var statuCell = document.createElement('td');//创建第6列身份 
	     statuCell.innerHTML ='组员';  
	     row.appendChild(statuCell); 
	 
		 var collegeCell = document.createElement('td'); //创建第7列所属院系 
		 collegeCell.innerHTML = user_college; 
	     row.appendChild(collegeCell); //加入行  ，下面类似  
	       
	     var DoCell = document.createElement('td');//创建第8列操作 
	     DoCell.innerHTML = '<button type="button" class="btn bg-olive btn-xs" onclick="delete_user(this)">删除</button>';  
	     row.appendChild(DoCell);
	 	 
		 var tbody = document.getElementById("Teambody");
     	 tbody.appendChild(row);
	  
}


    //根据选择的学院查询老师
    function ByCollege(user_college){
    	
    	var data={
    		user_college:user_college,
    		};
    	
    	$.ajax({
    		type:'POST',
    		url:"${pageContext.request.contextPath}/team/findUserByCollege.do",
    		data:data,
    		
    		success:function(result){
    	            alert(user_college);
    	            /*   alert(user_name+":::"+result); */
    	            alert(user_name+":::"+data);
    	           var str1 = "";
    	            //清空table中的html
    	            $("#UserTableBody").html("");
    	            for(var i = 0;i<data.data.length;i++){
    	                str1 = '<tr>'+
        				'<td>'+result[i].user_Id+'</td>'+
        				'<td>'+result[i].user_name+'</td>'+
        				'<td>'+result[i].user_college+'</td>'+
        				'<td>'+'<button type="button" class="btn bg-olive btn-xs" onclick="ZuDui('+result[i].user_college+','+result[i].user_name+','+result[i].user_college+')">组队</button>'
    					+'</td>'+
    					'</tr>';
    	                $("#UserTableBody").append(str1);
    	            }
    	

    			}
    	});
    }
    //根据姓名查询老师
    function ByName(){
    	
    	var user_name=document.getElementById("sousuo_text").value;
    	var data={
    		user_name:user_name,
    		};
    	$.ajax({
    		type:'POST',
    		url:"${pageContext.request.contextPath}/team/findUserByUserName.do",
    		data:data,
    		
    		success:function(result){
    	          /*   alert(user_name+":::"+result); */
    	            alert(user_name+":::"+data);
    	           var str1 = "";
    	            //清空table中的html
    	            $("#UserTableBody").html("");
    	            for(var i = 0;i<data.data.length;i++){
    	                str1 = '<tr>'+
        				'<td>'+result[i].user_Id+'</td>'+
        				'<td>'+result[i].user_name+'</td>'+
        				'<td>'+result[i].user_college+'</td>'+
        				'<td>'+'<button type="button" class="btn bg-olive btn-xs" onclick="ZuDui('+result[i].user_college+','+result[i].user_name+','+result[i].user_college+')">组队</button>'
    					+'</td>'+
    					'</tr>';
    	                $("#UserTableBody").append(str1);
    	            }
    	           
    			}
    	});
    }
    </script>


</body>
</html>