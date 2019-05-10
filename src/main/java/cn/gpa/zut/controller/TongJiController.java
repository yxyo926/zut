package cn.gpa.zut.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.domain.User;
import cn.gpa.zut.service.TongJiService;

@Controller
@RequestMapping("/tongji")
public class TongJiController {
	
	@Autowired
	private TongJiService tongjiservice;
	
	
	@RequestMapping("/tongji.do")
	@ResponseBody
	public Map tongji_list(HttpServletRequest request)throws Exception{
		HttpSession session = request.getSession(true);
		ModelAndView mv=new ModelAndView();
		User user=(User) session.getAttribute("user");
		String user_id=user.getUser_Id();
		System.out.println("user_id:"+user_id);
		
//		List<String>  sci_year=tongjiservice.findSciYear(user_id);
////		List<String>  tch_year=tongjiservice.findTchYear(user_id);
//		List<String>  wy_year=tongjiservice.findWyYear(user_id);
		
		List<String>  year_sum = new ArrayList<String>();
//		Date date=new Date();
//		SimpleDateFormat aaa=new SimpleDateFormat("yyyy");
//		int X_year=Integer.valueOf(aaa.format(date));
//		System.out.println(X_year);
//		for(int i=1;i<8;i++ ){
//			System.out.println(X_year);
//			tch_year.add(String.valueOf(X_year));
//			X_year=X_year-i;
//			
//		}
		year_sum.add("2013");
		year_sum.add("2014");
		year_sum.add("2015");
		year_sum.add("2016");
		year_sum.add("2017");
		year_sum.add("2018");
		year_sum.add("2019");
		
		
		 
	    List<Double> gradeList=new ArrayList<Double>();
	       for(String year:year_sum){
	          Double doubleList=tongjiservice.tch_findSumGpa(user_id, year);
	          if(doubleList==null){
	        	 gradeList.add((double) 0);
	          }else{
	           gradeList.add(doubleList);
	          }
	        }
	        Map<String,List<Double>> tch_map=new HashMap<String, List<Double>>();
	        
	        tch_map.put("grade",gradeList);
	        
	        List<Double> gradeList2=new ArrayList<Double>();
	        for(String year:year_sum){
	            Double doubleList=tongjiservice.sci_findSumGpa(user_id, year);
	            if(doubleList==null){
		        	 gradeList.add((double) 0);
		          }else{
		           gradeList2.add(doubleList);
		           System.out.println("sci:"+year+"总业绩点:"+doubleList);
		        
		          }
	        }
	        Map<String,List<Double>> sci_map=new HashMap<String, List<Double>>();
	        sci_map.put("grade",gradeList2);
	        
	        List<Double> gradeList3=new ArrayList<Double>();
	        for(String year:year_sum){
	            Double doubleList=tongjiservice.wy_findSumGpa(user_id, year);
	            if(doubleList==null){
		        	 gradeList.add((double) 0);
		          }else{
		           gradeList3.add(doubleList);
		          
		          }
	        }
	        Map <String,List<Double>> wy_map=new HashMap<String, List<Double>>();
	        wy_map.put("grade",gradeList3);
		
		Map<String,Map> summap=new HashMap<String, Map>();
		summap.put("tch_map", tch_map);
		summap.put("sci_map", sci_map);
		summap.put("wy_map", wy_map);
		
		return summap;
		
		
		
	}

}
