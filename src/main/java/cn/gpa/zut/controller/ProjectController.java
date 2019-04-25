package cn.gpa.zut.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.domain.DictPara;
import cn.gpa.zut.domain.DictRatio;
import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Project;
import cn.gpa.zut.domain.ProjectPara;
import cn.gpa.zut.domain.Record;
import cn.gpa.zut.domain.User;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.service.IDictParaService;
import cn.gpa.zut.service.IDictRatioService;
import cn.gpa.zut.service.IGpaDistrService;
import cn.gpa.zut.service.IProjectParaService;
import cn.gpa.zut.service.IProjectService;
import cn.gpa.zut.service.IRecordService;
import cn.gpa.zut.service.IUserService;
import cn.gpa.zut.service.IUserteamService;
import cn.gpa.zut.utils.UUIDUtils;

@Controller

@RequestMapping("/project")
@SessionAttributes(value = { "infoId", "Userteam", "totalGpa", "recordId", "sort" })
public class ProjectController {
	@Autowired
	public IProjectService projectService;
	@Autowired
	private IProjectParaService projectParaService;
	@Autowired
	private IUserteamService userteamService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IGpaDistrService gpaDistrService;
	@Autowired
	private IRecordService recordService;

	// 显示所有论文信息
	@RequestMapping("/findAll.do")
	public ModelAndView findAll() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Project> projects = projectService.findAll();
		mv.addObject("projectList", projects);
		mv.setViewName("project-list");
		return mv;
	}

	// 显示项目信息
	@RequestMapping("/findAllById.do")
	public ModelAndView findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<Project> projects = projectService.findAllById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		model.addAttribute("infoId", uuidString);
		System.out.println(uuidString);
		mv.addObject("projectList", projects);
		mv.setViewName("project-list");
		return mv;
	}

	// 获取参数与系数信息并跳转信息添加页面
	@RequestMapping("/getSort.do")
	public ModelAndView getSort() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<ProjectPara> projectParas = projectParaService.findAll();
		List<Userteam> userteams = userteamService.findAll();
		mv.addObject("projectParas", projectParas);
		mv.addObject("userteams", userteams);
		mv.setViewName("project-add");
		return mv;
	}

	// 信息添加并将数据存入数据库
	@RequestMapping("/save.do")
	public String save(ModelMap model, @ModelAttribute("form") Project project,
			@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num)
			throws Exception {
		Double sumGpa = sumGPA(project);
		project.setProjectinfo_getGpa(sumGpa);
		projectService.save(project);
		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", sumGpa);
		String sort = "project";
		model.addAttribute("sort", sort);
		return "redirect:gpadistribute.do";
	}

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

	private  String fileUrl=null;
	private  String rfilename=null;

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

	public Double sumGPA(Project project) throws Exception {
		int gpa = 0;
		Double ratio = 0.0;
		Double sumgpa = 0.0;
		Double M=0.0;
		Double startMoneyDouble=0.0;
		if(project.getProjectinfo_StartMoney()!=null) {
		 startMoneyDouble = project.getProjectinfo_StartMoney();
		}
		
		//获取数据库中参数集合
		List<ProjectPara> projectParas = projectParaService.findAll();
		//遍历
		for (Iterator iterators = projectParas.iterator(); iterators.hasNext();) {
			ProjectPara projectPara = (ProjectPara) iterators.next();// 获取当前遍历的元素，指定为Example对象
			String name = projectPara.getResearchlevel_Id();
			if (project.getProjectinfo_origin().equals(name)) {
				//取业绩点
				gpa = projectPara.getResearchlevel_Gpa();
				//取系数
				ratio=projectPara.getResearchlevel_Ratio();
				//取M值
				M=projectPara.getResearchlevel_M();
			}
		}
		Double mgpa= startMoneyDouble/M;
		System.out.println(mgpa);
		sumgpa = gpa+(mgpa*ratio);
		return sumgpa;
	}

	// 判断回传的用户组数据是否存在
	public Userteam isExist(String userteam_name, Integer userteam_num) throws Exception {
		List<Userteam> userteams = userteamService.findAll();
		for (Iterator iterators = userteams.iterator(); iterators.hasNext();) {
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

}
