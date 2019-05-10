<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
 <div id="main" style="height:500px"></div>
 
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery.js"></script>
  <script src="${pageContext.request.contextPath}/build/dist/echarts.js"></script>
  <script src="${pageContext.request.contextPath}/build/dist/echarts-all.js"></script>
 <script type="text/javascript">
 // 路径配置
 require.config({
     paths: {
         echarts: '${pageContext.request.contextPath}/build/dist'
     }
 });
 
 // 使用
 require(
     [
         'echarts',
         'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
     ],
     function (ec) {
         // 基于准备好的dom，初始化echarts图表
         
         var myChart = echarts.init(document.getElementById('main')); 
  

  $.ajax({
      type: "POST",
      url:"${pageContext.request.contextPath}/tongji/tongji.do",
      success: function(summap){ 
    	  var data1=[];
    	  
    	  for(var n in summap.tch_map.grade){
              data1.push(summap.tch_map.grade[n]);
          }
    	  
  		  var data2=[];
    	  
    	  for(var n in summap.sci_map.grade){
              data2.push(summap.sci_map.grade[n]);
          }
    	  
    	  
 		 var data3=[];
    	  
    	  for(var n in summap.wy_map.grade){
              data3.push(summap.wy_map.grade[n]);
          }
     
	     var option = {
	 			tooltip : {
	   		  	trigger: 'axis'
	 				},
				 legend: {
	     		data:['教研','科研','文艺']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : ['2013学年','2014学年','2015学年','2016学年','2017学年','2018学年','2019学年']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			       
			        {
			            name:'教研',
			            type:'line',
			            stack: '总量',
			            data: []
			        },
			        {
			            name:'科研',
			            type:'line',
			            stack: '总量',
			            data:[]
			        },
			        {
			            name:'文艺',
			            type:'line',
			            stack: '总量',
			            data:[]
			        }
			    ]
			};
	    /*  option.series[0].data=[150, 150, 150, 150, 150, 150, 150]; */
	     option.series[0].data=data1;
	     option.series[1].data=data2;
	     option.series[2].data=data3;
 
         // 为echarts对象加载数据 
         myChart.setOption(option); 
      }
    
    });
  });
  


  </script>
</body>
</html>