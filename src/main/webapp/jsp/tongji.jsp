<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link class="include" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jqplot/jquery.jqplot.min.css" />
 <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/jqplot/examples/syntaxhighlighter/styles/shCoreDefault.min.css" />
 <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/jqplot/examples/syntaxhighlighter/styles/shThemejqPlot.min.css" />
    
    <title>ECharts</title>
</head>
<body>

  <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
  <!--   <div id="main" style="height:500px"></div> -->
    
    <div id="chart1"></div>
	<div id="chart2"></div>
	<div id="chart3"></div>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery.js"></script>
   
	<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/m_jqplot.js"></script>
<%-- 	<script src="${pageContext.request.contextPath}/js/jqplot/yejichaxun.js"></script> --%>
    <script type="text/javascript">
     $(document).ready(function() {

         var x=[];
         var data=[];
         var line_title =[];
         $.ajax({
             type: "POST",
          		 url:"${pageContext.request.contextPath}/tongji/tongji.do",
           /*   url:baseURL+"zut/tongji/tongji.do", */
             success: function(summap){
                 
                   for(var i in summap.tch_map.years){
                       x.push(summap.tch_map.years[i]);
                   }
                   var data1=[];
                   var max1=null;
                   for(var n in summap.tch_map.grade){
                       data1.push(summap.tch_map.grade[n]);
                       if(max1==null){
                           max1= summap.tch_map.grade[n];
                       }else{
                           if(max1< summap.tch_map.grade[n]){
                               max1= data.tch_map.grade[n];
                           }
                       }
                   }
                     line_title.push("教研");
                   var data2=[];
                   var max2=null;
                   for(var o in summap.sci_map.grade){
                       data2.push(summap.sci_map.grade[o]);
                       if(max2==null){
                           max2=summap.sci_map.grade[o];
                       }else{
                           if(max2<summap.sci_map.grade[o]){
                               max2=summap.sci_map.grade[o];
                           }
                       }
                   }
                     line_title.push("科研");
                   
                   var data3=[];
                   var max3=null;
                   for(var n in summap.wy_map.grade){
                       data3.push(summap.wy_map.grade[n]);
                       if(max3==null){
                           max3= summap.wy_map.grade[n];
                       }else{
                           if(max3< summap.wy_map.grade[n]){
                               max3= data.wy_map.grade[n];
                           }
                       }
                   }
                     line_title.push("文艺");
                   
                   
                   var data_max;
                   if(max1>max2){
                        if(max1>max3){
                        data_max=max1;                        	
                        }else{
                        	data_max=max3;         
                        }
                   }else{
                	   if(max2>max3){
                       data_max=max2;  
                	   }else{
                		   data_max=max3;
                	   }
                   }
                   data.push(data1);
                   data.push(data2);
                   data.push(data3);

                    // var data_max = 1000; //Y轴最大刻度
                    // var line_title = ["教研","科研","文艺"]; //曲线名称
                     var y_label = "总业绩点"; //Y轴标题
                     var x_label = "年份"; //X轴标题
                     //x = [1,2,3,4,5,6,7,8,9]; //定义X轴刻度值
                    // var title = "这是标题"; //统计图标标题
                     j.jqplot.diagram.base("chart1", data, line_title, "教研", x, x_label, y_label, data_max, 1);
                     j.jqplot.diagram.base("chart2", data, line_title, "科研", x, x_label, y_label, data_max, 2);
                     j.jqplot.diagram.base("chart3", data, line_title, "文艺", x, x_label, y_label, data_max, 3); 

             }
         });

     });

     
     
     
     
    
 </script>
</body>
</html>