package cn.gpa.zut.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.gpa.zut.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.service.IGpaDistrService;
import cn.gpa.zut.service.IRecordService;
import cn.gpa.zut.service.IUserService;
import cn.gpa.zut.service.IUserteamService;
import cn.gpa.zut.service.Jy_ProjectService;
import cn.gpa.zut.utils.UUIDUtils;

@Controller
@RequestMapping("/jy_project")
@SessionAttributes(value = { "infoId", "Userteam", "totalGpa", "recordId", "sort" })
public class Jy_ProjectController {
	
	@Autowired 
	private Jy_ProjectService jy_projectservice;
	//这是业绩点分配
	@Autowired
	private IGpaDistrService gpaDistrService;
	@Autowired 
	private IUserService userService;
	@Autowired
	private IRecordService recordService;
	@Autowired
	private IUserteamService userteamService;
	private  String fileUrl;
	private  String rfilename;
	
	// 分配业绩点
			@RequestMapping("/gpadistribute.do")
			public ModelAndView gpaDistribute(Userteam userteam) throws Exception {
				ModelAndView mv = new ModelAndView();
				// mv.addObject("userteam", userteam);
				/*
				 * request.setAttribute("userteam_name", "zfy");
				 * request.setAttribute("userteam_num", "10");
				 */
				List<User> users = userService.findAll();
				mv.addObject("users", users);
				System.out.println("分配页面出现了");
				mv.setViewName("gpadistribute");
				return mv;
			}

			// 保存业绩点分配记录
			@RequestMapping("/gpasave.do")
			public String saveUsers(ModelMap model, @ModelAttribute("Userteam") Userteam uerUserteam,
					SessionStatus sessionStatus, @ModelAttribute("form") Paper paper) {
				UUIDUtils uuidUtils = new UUIDUtils();
				String recordString = uuidUtils.getUUID();
				List<GpaDistr> gpaDistrs = paper.getGpaDistrs();
				for (int i = 0; i < gpaDistrs.size(); i++) {
					String uuidString = uuidUtils.getUUID();
					GpaDistr gpaDistr = paper.getGpaDistrs().get(i);
					gpaDistr.setGpadistr_id(uuidString);
					gpaDistr.setRecord_id(recordString);
					gpaDistr.setUserteam_id(uerUserteam.getUserteam_id());
					System.out.println(gpaDistr.getUser_Id());
					gpaDistrService.save(gpaDistr);
				}
				model.addAttribute("recordId", recordString);
				System.out.println("业绩点保存了");

				return "record";

			}

			@RequestMapping("/record.do")
			public ModelAndView record() throws Exception {
				ModelAndView mv = new ModelAndView();
				mv.setViewName("record");
				return mv;
			}

			// 凭证提交
			@RequestMapping("/recordsave.do")
			//@ResponseBody
			public String saveRecord(MultipartFile file, Record record, HttpServletRequest request) throws IllegalStateException, IOException {
				HttpSession session = request.getSession(true);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
				String res = sdf.format(new Date());
				// uploads文件夹位置
				String rootPath = request.getSession().getServletContext().getRealPath("resource/uploads/");
				// 原始名称
				String originalFileName = file.getOriginalFilename();
				// 新文件名
				String newFileName = "sliver" + res + originalFileName.substring(originalFileName.lastIndexOf("."));
				// 创建年月文件夹
				Calendar date = Calendar.getInstance();
				File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));
				// 新文件
				File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
				// 判断目标文件所在目录是否存在
				if (!newFile.getParentFile().exists()) {
					// 如果目标文件所在的目录不存在，则创建父目录
					newFile.getParentFile().mkdirs();
				}
				System.out.println(newFile);
				// 将内存中的数据写入磁盘
				file.transferTo(newFile);
				//文件名
				rfilename=newFileName;
				// 完整的url
				fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;
				System.out.println(fileUrl);
				session.removeAttribute("Userteam");
				session.removeAttribute("infoId");
				session.removeAttribute("totalGpa");
				session.removeAttribute("recordId");
				System.out.println(fileUrl);
				record.setRecord_proof(fileUrl);
				recordService.save(record);
				//return "redirect:findAll.do";
				return "redirect:downshow.do";

			}
			
			@RequestMapping("/downshow.do")
			public ModelAndView down() throws Exception {
				ModelAndView mv = new ModelAndView();
				mv.setViewName("fail");
				return mv;
			}
			
			 @RequestMapping("/down.do")  
		     public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
		        
		         String fileName = request.getSession().getServletContext().getRealPath("resource/uploads/")+fileUrl;  
		         
		         InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
		           
		         String filename = rfilename ;  
		         
		         filename = URLEncoder.encode(rfilename,"UTF-8");  
		         
		         response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
		             
		         response.setContentType("multipart/form-data");   
		         
		         BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
		         int len = 0;  
		         while((len = bis.read()) != -1){  
		             out.write(len);  
		             out.flush();  
		         }  
		         out.close();  
		     }  
	//我加的东西到此结束
	
		
	//教改
	
	@RequestMapping("/Jiaogai_Lev.do")
	public ModelAndView Jiaogai_Lev() throws Exception{
		ModelAndView mv = new ModelAndView();
		String ID="1";
		List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(ID);
		mv.addObject("list_lev", list_lev);
		mv.setViewName("jy_project_jiaogai");
		return mv;
	}
	
	//提交教改项目
	@RequestMapping("/Add_Jiaogai.do")
	public String Add_Jiaogai(ModelMap model,
			@RequestParam("project_name") String reforminfo_name,
			@RequestParam("project_type") String reforminfo_sort,
			@RequestParam("lev_select") String projectlev_id,
			@RequestParam("logmin") String reforminfo_starttime,
			@RequestParam("logmax") String reforminfo_finishtime,
			@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num
	)throws Exception {
		JY_jiaogai jiaogai=new JY_jiaogai();		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date starttime = formatter.parse(reforminfo_starttime);
		Date finishtime = formatter.parse(reforminfo_finishtime);
		
		String reforminfo_id=UUID.randomUUID().toString(); 
		jiaogai.setReforminfo_id(reforminfo_id);
		jiaogai.setReforminfo_name(reforminfo_name);
		jiaogai.setProjectlev_id(projectlev_id);
		jiaogai.setReforminfo_sort(reforminfo_sort);
		jiaogai.setReforminfo_starttime(starttime);
		jiaogai.setReforminfo_finishtime(finishtime);
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
		jy_projectservice.Add_Record(jy_record);
		
		//tianji
	     String	sumGpa="0";
		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", sumGpa);
		String sort = "jy_project";
		model.addAttribute("sort", sort);


		return "redirect:gpadistribute.do";
	}
	
	//专业
	@RequestMapping("/Zhuanye_Lev.do")
	public ModelAndView Zhuanye_Lev() throws Exception{
		ModelAndView mv = new ModelAndView();
		String ID="2";
		List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(ID);
		mv.addObject("list_lev", list_lev);
		mv.setViewName("jy_project_zhuanye");
		return mv;
	}
	
	//提交专业项目
		@RequestMapping("/Add_ZhuanYe.do")
		public String Add_ZhuanYe(ModelMap model,
				@RequestParam("project_name") String project_name,
				@RequestParam("project_type") String project_type,
				@RequestParam("project_college") String project_college,
				@RequestParam("lev_select") String lev_select,
				@RequestParam("logmin") String logmin,
				@RequestParam("logmax") String logmax,
				@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num
				)throws Exception {
			JY_ZhuanYe zhuanye=new JY_ZhuanYe();		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date starttime = formatter.parse(logmin);
			Date finishtime = formatter.parse(logmax);
			
			String reforminfo_id=UUID.randomUUID().toString(); 
			zhuanye.setMajorinfo_id(reforminfo_id);
			zhuanye.setMajorinfo_name(project_name);
			zhuanye.setMajorinfo_mname(project_type);
			zhuanye.setMajorinfo_college(project_college);
			zhuanye.setProjectlev_id(lev_select);
			zhuanye.setMajorinfo_starttime(starttime);
			zhuanye.setMajorinfo_endtime(finishtime);
			jy_projectservice.Add_ZhuanYe(zhuanye);
			
			String project_record=UUID.randomUUID().toString();
			Date sbtime=new Date();
			String tablename="tch_majorinfo";
			JY_Record jy_record=new JY_Record();
			jy_record.setRecord_id(project_record);
			jy_record.setRecord_project_id(reforminfo_id);
			jy_record.setRecord_sort(tablename);
			jy_record.setRecord_sbtime(sbtime);
			jy_record.setState(0);
			jy_projectservice.Add_Record(jy_record);
			//tianji
		     String	sumGpa="0";
			Userteam userteam = isExist(userteam_name, userteam_num);
			model.addAttribute("Userteam", userteam);
			model.addAttribute("totalGpa", sumGpa);
			String sort = "jy_project";
			model.addAttribute("sort", sort);


			return "redirect:gpadistribute.do";
		}
		
	
	//课程
	
	@RequestMapping("/Kecheng_Lev.do")
	public ModelAndView Kecheng_Lev() throws Exception{
		ModelAndView mv = new ModelAndView();
		String ID="3";
		List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(ID);
	
		mv.addObject("list_lev", list_lev);
		mv.setViewName("jy_project_kecheng");
		return mv;
	}
	
	//提交课程项目
			@RequestMapping("/Add_KeCheng.do")
			public String Add_KeCheng(ModelMap model,
					@RequestParam("project_name") String reforminfo_name,
					@RequestParam("project_type") String reforminfo_sort,
					@RequestParam("college") String college,
					@RequestParam("belongs") String belongs,
					@RequestParam("lev_select") String projectlev_id,
					@RequestParam("logmin") String reforminfo_starttime,
					@RequestParam("logmax") String reforminfo_finishtime,
					@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num
					)throws Exception {
				JY_KeCheng kecheng=new JY_KeCheng();		
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date starttime = formatter.parse(reforminfo_starttime);
				Date finishtime = formatter.parse(reforminfo_finishtime);
				
				String reforminfo_id=UUID.randomUUID().toString(); 
				
				kecheng.setClassinfo_id(reforminfo_id);
				kecheng.setClassinfo_name(reforminfo_name);
				kecheng.setClassinfo_majorname(reforminfo_sort);
				kecheng.setClassinfo_lev(projectlev_id);
				kecheng.setClassinfo_belongs(belongs);
				kecheng.setClassinfo_starttime(starttime);
				kecheng.setClassinfo_endtime(finishtime);
				jy_projectservice.Add_KeCheng(kecheng);
				
				
				String project_record=UUID.randomUUID().toString();
				Date sbtime=new Date();
				String tablename="tch_classinfo";
				JY_Record jy_record=new JY_Record();
				jy_record.setRecord_id(project_record);
				jy_record.setRecord_project_id(reforminfo_id);
				jy_record.setRecord_sort(tablename);
				jy_record.setRecord_sbtime(sbtime);
				jy_record.setState(0);
				
				jy_projectservice.Add_Record(jy_record);
				//tianji
			     String	sumGpa="0";
				Userteam userteam = isExist(userteam_name, userteam_num);
				model.addAttribute("Userteam", userteam);
				model.addAttribute("totalGpa", sumGpa);
				String sort = "jy_project";
				model.addAttribute("sort", sort);

				return "redirect:gpadistribute.do";
			}
			
	
	//获奖
	
	@RequestMapping("/Huojiang_Lev.do")
	public ModelAndView Huojiang_Lev() throws Exception{
		ModelAndView mv = new ModelAndView();
		String ID="5";
		List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(ID);
		mv.addObject("list_lev", list_lev);
		mv.setViewName("jy_project_huojiang");
		return mv;
	}
	
	//提交获奖项目
			@RequestMapping("/Add_HuoJiang.do")
			public String Add_HuoJiang(ModelMap model,
					@RequestParam("project_name") String project_name,
					@RequestParam("organize_name") String organize_name,
					@RequestParam("lev_select") String lev_select,
					@RequestParam("logmin") String logmin,
					@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num
					)throws Exception {
				JY_HuoJiang huojiang=new JY_HuoJiang();		
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date starttime = formatter.parse(logmin);
				
				String id=UUID.randomUUID().toString(); 
				huojiang.setRewardinfo_id(id);
				huojiang.setProjectlev_id(lev_select);
				huojiang.setRewardinfo_gettime(starttime);
				huojiang.setRewardinfo_name(project_name);
				huojiang.setRewardinfo_organizename(organize_name);
				jy_projectservice.Add_HuoJiang(huojiang);
				
				String project_record=UUID.randomUUID().toString();
				Date sbtime=new Date();
				String tablename="tch_rewardinfo";
				JY_Record jy_record=new JY_Record();
				jy_record.setRecord_id(project_record);
				jy_record.setRecord_project_id(id);
				jy_record.setRecord_sort(tablename);
				jy_record.setRecord_sbtime(sbtime);
				jy_record.setState(0);
				
				jy_projectservice.Add_Record(jy_record);

                //sumGpa
				String sumGpa=null;
				Userteam userteam = isExist(userteam_name, userteam_num);
				model.addAttribute("Userteam", userteam);
				model.addAttribute("totalGpa", sumGpa);
				String sort = "jy_project";
				model.addAttribute("sort", sort);
				return "redirect:gpadistribute.do";


			}
	//
	//需要复制
	public Double sumGPA(Assess assess) throws Exception {

		int gpa = 0;
		Double ratio = 0.0;
		Double sumgpa = 0.0;
		sumgpa = (double) gpa;
		return sumgpa;
	}

	// 判断回传的用户组数据是否存在
	public Userteam isExist(String userteam_name, Integer userteam_num) throws Exception {
		List<Userteam> userteams = userteamService.findAll();
		for (Iterator iterators = userteams.iterator(); iterators.hasNext(); ) {
			Userteam userteam = (Userteam) iterators.next();// 获取当前遍历的元素，指定为Example对象
			if (userteam.getUserteam_name().equals(userteam_name)) {
				return userteam;
			}
		}
		Userteam userteam = new Userteam();
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		userteam.setUserteam_id(uuidString);
		userteam.setUserteam_name(userteam_name);
		userteam.setUserteam_num(userteam_num);
		userteamService.save(userteam);
		return userteam;
	}
	
	
	//论文
	
		@RequestMapping("/lunwen_Lev.do")
		public ModelAndView lunwen_Lev() throws Exception{
			ModelAndView mv = new ModelAndView();
			String ID="4";
			List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(ID);
			mv.addObject("list_lev", list_lev);
			mv.setViewName("jy_project_lunwen");
			return mv;
		}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/findLevByCategoryId.do")
	List<JY_ProjectLev> findLevByCategoryId(@RequestParam("category_id") String category_id) throws Exception{
		List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(category_id);
		System.out.println("信息填报："+list_lev.get(1).getProjectlev_name());
		return list_lev;
	}
	
	
	
	
	
	@RequestMapping("/fileupload.do")
	public @ResponseBody  String upload(MultipartFile file, HttpServletRequest request) throws IOException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
	String res = sdf.format(new Date());

	// uploads文件夹位置
	String rootPath = request.getSession().getServletContext().getRealPath("resource/uploads/");
	 // 原始名称
	String originalFileName = file.getOriginalFilename();
	// 新文件名
	String newFileName = "sliver" + res + originalFileName.substring(originalFileName.lastIndexOf("."));
	// 创建年月文件夹
	Calendar date = Calendar.getInstance();
	File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH)+1));

	// 新文件
	File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
	// 判断目标文件所在目录是否存在
	if( !newFile.getParentFile().exists()) {
	      // 如果目标文件所在的目录不存在，则创建父目录
	      newFile.getParentFile().mkdirs();
	  }
	System.out.println(newFile);
	// 将内存中的数据写入磁盘
	file.transferTo(newFile);
	// 完整的url
	String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + newFileName;
	    return  fileUrl;
	}
	
	
	
}
