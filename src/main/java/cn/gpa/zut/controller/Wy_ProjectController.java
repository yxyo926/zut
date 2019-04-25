package cn.gpa.zut.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.JY_jiaogai;

@Controller
@RequestMapping("/wenyi")
public class Wy_ProjectController {
	
	
	/*//提交教改项目
		@RequestMapping("/Add_chuban.do")
		public String Add_Jiaogai(ModelMap model,
				@RequestParam("project_name") String reforminfo_name,
				@RequestParam("project_type") String reforminfo_sort,
				@RequestParam("lev_select") String projectlev_id,
				@RequestParam("logmin") String reforminfo_starttime,
				@RequestParam("logmax") String reforminfo_finishtime,
				@RequestParam("File") String local_pingzheng 
				)throws Exception {
			JY_jiaogai jiaogai=new JY_jiaogai();		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date starttime = formatter.parse(reforminfo_starttime);
			Date finishtime = formatter.parse(reforminfo_finishtime);
			System.out.println("教改等级id"+projectlev_id);
			String reforminfo_id=UUID.randomUUID().toString(); 
			jiaogai.setReforminfo_id(reforminfo_id);
			jiaogai.setReforminfo_name(reforminfo_name);
			jiaogai.setProjectlev_id(projectlev_id);
			jiaogai.setReforminfo_sort(reforminfo_sort);
			jiaogai.setReforminfo_starttime(starttime);
			jiaogai.setReforminfo_finishtime(finishtime);
			jiaogai.setReforminfo_place(local_pingzheng);
			System.out.println(jiaogai.toString());
			jy_projectservice.Add_Jiaogai(jiaogai);
			
			String project_record=UUID.randomUUID().toString();
			Date sbtime=new Date();
			String tablename="tch_reforminfo";
			JY_Record jy_record=new JY_Record();
			jy_record.setRecord_id(project_record);
			jy_record.setRecord_project_id(reforminfo_id);
			jy_record.setRecord_sort(tablename);
			jy_record.setRecord_sbtime(sbtime);
			jy_record.setState(0);
			jy_record.setRecord_local(local_pingzheng);
			
			jy_projectservice.Add_Record(jy_record);


			return "redirect:gpadistribute.do";
		}
	*/
}
