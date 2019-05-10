package cn.gpa.zut.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.JY_HuoJiang;
import cn.gpa.zut.domain.JY_KeCheng;
import cn.gpa.zut.domain.JY_Lunwen;
import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.JY_ZhuanYe;
import cn.gpa.zut.domain.JY_jiaogai;
import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.User;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.service.IGpaDistrService;
import cn.gpa.zut.service.IRecordService;
import cn.gpa.zut.service.IUserService;
import cn.gpa.zut.service.IUserteamService;
import cn.gpa.zut.service.Jy_ProjectService;
import cn.gpa.zut.utils.UUIDUtils;

@Controller
@RequestMapping("/jy_project")
@SessionAttributes(value = { "infoId", "Userteam", "totalGpa", "record", "sort", "list_name" })
public class Jy_ProjectController {

	@Autowired
	private Jy_ProjectService jy_projectservice;
	// 这是业绩点分配
	@Autowired
	private IGpaDistrService gpaDistrService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserteamService userteamService;
	@Autowired
	private IRecordService recordService;

	private String fileUrl;
	private String rfilename;

	// 分配业绩点
	@RequestMapping("/gpadistribute.do")
	public ModelAndView gpaDistribute(Userteam userteam) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<User> users = userService.findAll();
		mv.addObject("users", users);
		System.out.println("分配页面出现了");
		mv.setViewName("gpadistribute");
		return mv;
	}

	// 保存业绩点分配记录
	@RequestMapping("/gpasave.do")
	public String saveUsers(ModelMap model, @ModelAttribute("Userteam") Userteam uerUserteam,
			SessionStatus sessionStatus, @ModelAttribute("form") Paper paper,
			@ModelAttribute("record") JY_Record record) {
		UUIDUtils uuidUtils = new UUIDUtils();
		String recordString = uuidUtils.getUUID();
		List<GpaDistr> gpaDistrs = paper.getGpaDistrs();
		for (int i = 0; i < gpaDistrs.size(); i++) {
			String uuidString = uuidUtils.getUUID();// 业绩分配id
			GpaDistr gpaDistr = paper.getGpaDistrs().get(i);
			gpaDistr.setGpadistr_id(uuidString);
			gpaDistr.setRecord_id(record.getRecord_id());
			gpaDistr.setUserteam_id(uerUserteam.getUserteam_id());
			System.out.println(gpaDistr.getUser_Id());
			gpaDistrService.tch_save(gpaDistr);
		}
		model.addAttribute("recordId", recordString);
		System.out.println("业绩点保存了");

		return "record";

	}

	@RequestMapping("/record.do")
	public ModelAndView record() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jy_record");
		return mv;
	}

	// 凭证提交
	@RequestMapping("/recordsave.do")
	// @ResponseBody
	public String saveRecord(MultipartFile file, @ModelAttribute("record") JY_Record record,
			@ModelAttribute("list_name") String list_name, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
		// 文件名
		rfilename = newFileName;
		// 完整的url

		fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;
		System.out.println(fileUrl);
		System.out.println(fileUrl);
		record.setRecord_local(fileUrl);
		recordService.tch_save(record);

		session.removeAttribute("Userteam");
		session.removeAttribute("infoId");
		session.removeAttribute("totalGpa");
		session.removeAttribute("record");
		session.removeAttribute("list_name");
		User user = (User) session.getAttribute("user");

		return "redirect:" + list_name + "?id=" + user.getUser_Id();

	}

	@RequestMapping("/downshow.do")
	public ModelAndView down() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fail");
		return mv;
	}

	@RequestMapping("/down.do")
	public void down(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = request.getSession().getServletContext().getRealPath("resource/uploads/") + fileUrl;
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
		String filename = rfilename;
		filename = URLEncoder.encode(rfilename, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		while ((len = bis.read()) != -1) {
			out.write(len);
			out.flush();
		}
		out.close();
	}
	// 我加的东西到此结束

	// 教改

	@RequestMapping("/Jiaogai_findAllById.do")
	public ModelAndView findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<JY_jiaogai> projects = jy_projectservice.findJiaogaiById(userId);
		/*
		 * UUIDUtils uuidUtils = new UUIDUtils(); String uuidString =
		 * uuidUtils.getUUID(); model.addAttribute("infoId", uuidString);
		 * System.out.println(uuidString);
		 */
		mv.addObject("projectList", projects);
		mv.setViewName("jy_jiaogai-list");
		return mv;
	}

	@RequestMapping("/Jiaogai_Lev.do")
	public ModelAndView Jiaogai_Lev() throws Exception {
		ModelAndView mv = new ModelAndView();
		String ID = "1";
		List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(ID);
		mv.addObject("list_lev", list_lev);
		mv.setViewName("jy_project_jiaogai");
		return mv;
	}

	// 提交教改项目
	@RequestMapping("/Add_Jiaogai.do")
	public String Add_Jiaogai(ModelMap model, @RequestParam("project_name") String reforminfo_name,
			@RequestParam("project_type") String reforminfo_sort, @RequestParam("lev_select") String project_id,
			@RequestParam("logmin") String reforminfo_starttime, @RequestParam("logmax") String reforminfo_finishtime,
			@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num)
			throws Exception {
		JY_jiaogai jiaogai = new JY_jiaogai();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date starttime = formatter.parse(reforminfo_starttime);
		Date finishtime = formatter.parse(reforminfo_finishtime);
		System.out.println("教改等级id" + project_id);
		Double gpa = sumGPA(project_id);
		System.out.println(gpa);
		model.addAttribute("totalGpa", gpa);
		String reforminfo_id = UUID.randomUUID().toString(); // 项目id
		jiaogai.setReforminfo_id(reforminfo_id);
		jiaogai.setReforminfo_name(reforminfo_name);
		jiaogai.setProjectlev_id(project_id);

		jiaogai.setReforminfo_sort(reforminfo_sort);
		jiaogai.setReforminfo_starttime(starttime);
		jiaogai.setReforminfo_finishtime(finishtime);
		System.out.println(jiaogai.toString());
		jy_projectservice.Add_Jiaogai(jiaogai);

		String project_record = UUID.randomUUID().toString();// 记录id
		Date sbtime = new Date();
		String tablename = "tch_reforminfo";
		JY_Record jy_record = new JY_Record();
		jy_record.setRecord_piont(gpa);

		jy_record.setRecord_id(project_record);
		jy_record.setRecord_project_id(reforminfo_id);
		jy_record.setRecord_sort(tablename);
		jy_record.setRecord_sbtime(sbtime);
		jy_record.setState(0);

		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", gpa);
		model.addAttribute("record", jy_record);
		String sort = "jy_project";
		model.addAttribute("list_name", "Jiaogai_findAllById.do");
		model.addAttribute("sort", sort);
		return "redirect:gpadistribute.do";

	}

	// 专业

	@RequestMapping("/Zhuanye_findAllById.do")
	public ModelAndView Zhuanye_findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<JY_ZhuanYe> projects = jy_projectservice.findZhuanyeById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();

		System.out.println(uuidString);
		mv.addObject("zhuanyeList", projects);
		mv.setViewName("jy_zhuanye-list");
		return mv;
	}

	@RequestMapping("/Zhuanye_Lev.do")
	public ModelAndView Zhuanye_Lev() throws Exception {
		ModelAndView mv = new ModelAndView();
		String ID = "2";
		List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(ID);
		mv.addObject("list_lev", list_lev);
		mv.setViewName("jy_project_zhuanye");
		return mv;
	}

	// 提交专业项目
	@RequestMapping("/Add_ZhuanYe.do")
	public String Add_ZhuanYe(ModelMap model, @RequestParam("project_name") String project_name,
			@RequestParam("project_type") String project_type, @RequestParam("project_college") String project_college,
			@RequestParam("lev_select") String project_id, @RequestParam("logmin") String logmin,
			@RequestParam("logmax") String logmax, @RequestParam("userteam_name") String userteam_name,
			@RequestParam("userteam_num") Integer userteam_num) throws Exception {
		JY_ZhuanYe zhuanye = new JY_ZhuanYe();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date starttime = formatter.parse(logmin);
		Date finishtime = formatter.parse(logmax);
		Double gpa = sumGPA(project_id);
		System.out.println(gpa);

		String reforminfo_id = UUID.randomUUID().toString();
		zhuanye.setProject_id(reforminfo_id);
		zhuanye.setMajorinfo_name(project_name);
		zhuanye.setMajorinfo_mname(project_type);
		zhuanye.setMajorinfo_college(project_college);
		zhuanye.setProjectlev_id(project_id);
		zhuanye.setMajorinfo_starttime(starttime);
		zhuanye.setMajorinfo_endtime(finishtime);

		jy_projectservice.Add_ZhuanYe(zhuanye);

		String project_record = UUID.randomUUID().toString();
		Date sbtime = new Date();
		String tablename = "tch_majorinfo";

		JY_Record jy_record = new JY_Record();

		jy_record.setRecord_piont(gpa);
		jy_record.setRecord_id(project_record);
		jy_record.setRecord_project_id(reforminfo_id);
		jy_record.setRecord_sort(tablename);
		jy_record.setRecord_sbtime(sbtime);
		jy_record.setState(0);

		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", gpa);
		model.addAttribute("record", jy_record);
		String sort = "jy_project";
		model.addAttribute("sort", sort);
		model.addAttribute("list_name", "Zhuanye_findAllById.do");
		return "redirect:gpadistribute.do";

	}

	// 课程

	@RequestMapping("/Kecheng_findAllById.do")
	public ModelAndView Kecheng_findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<JY_KeCheng> projects = jy_projectservice.findKechengById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		model.addAttribute("infoId", uuidString);
		System.out.println(uuidString);
		mv.addObject("classinfoList", projects);
		mv.setViewName("jy_kecheng-list");
		return mv;
	}

	@RequestMapping("/Kecheng_Lev.do")
	public ModelAndView Kecheng_Lev() throws Exception {
		ModelAndView mv = new ModelAndView();
		String ID = "3";
		List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(ID);

		mv.addObject("list_lev", list_lev);
		mv.setViewName("jy_project_kecheng");
		return mv;
	}

	// 提交课程项目
	@RequestMapping("/Add_KeCheng.do")
	public String Add_KeCheng(ModelMap model, @RequestParam("project_name") String reforminfo_name,
			@RequestParam("project_type") String reforminfo_sort, @RequestParam("college") String college,
			@RequestParam("belongs") String belongs, @RequestParam("lev_select") String project_id,
			@RequestParam("logmin") String reforminfo_starttime, @RequestParam("logmax") String reforminfo_finishtime,
			@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num)
			throws Exception {
		JY_KeCheng kecheng = new JY_KeCheng();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date starttime = formatter.parse(reforminfo_starttime);
		Date finishtime = formatter.parse(reforminfo_finishtime);
		Double gpa = sumGPA(project_id);
		System.out.println(gpa);
		model.addAttribute("totalGpa", gpa);
		String reforminfo_id = UUID.randomUUID().toString();

		kecheng.setClassinfo_id(reforminfo_id);
		kecheng.setClassinfo_name(reforminfo_name);
		kecheng.setClassinfo_majorname(reforminfo_sort);
		kecheng.setClassinfo_lev(project_id);
		kecheng.setClassinfo_belongs(belongs);
		kecheng.setClassinfo_starttime(starttime);
		kecheng.setClassinfo_endtime(finishtime);
		jy_projectservice.Add_KeCheng(kecheng);

		String project_record = UUID.randomUUID().toString();
		Date sbtime = new Date();
		String tablename = "tch_classinfo";
		JY_Record jy_record = new JY_Record();
		jy_record.setRecord_id(project_record);

		jy_record.setRecord_piont(gpa);
		jy_record.setRecord_project_id(reforminfo_id);
		jy_record.setRecord_sort(tablename);
		jy_record.setRecord_sbtime(sbtime);
		jy_record.setState(0);

		// jy_projectservice.Add_Record(jy_record);

		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", gpa);
		model.addAttribute("record", jy_record);
		String sort = "jy_project";
		model.addAttribute("sort", sort);
		model.addAttribute("list_name", "Kecheng_findAllById.do");
		return "redirect:gpadistribute.do";

	}

	// 获奖
	// 展示表格
	@RequestMapping("/Huojiang_findAllById.do")
	public ModelAndView Huojiang_findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<JY_HuoJiang> projects = jy_projectservice.findHuojiangById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		model.addAttribute("infoId", uuidString);
		System.out.println(uuidString);
		mv.addObject("rewadrinfoList", projects);
		mv.setViewName("jy_huojiang-list");
		return mv;
	}

	@RequestMapping("/Huojiang_Lev.do")
	public ModelAndView Huojiang_Lev() throws Exception {
		ModelAndView mv = new ModelAndView();
		String ID = "5";
		List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(ID);
		mv.addObject("list_lev", list_lev);
		mv.setViewName("jy_project_huojiang");
		return mv;
	}

	// 提交获奖项目
	@RequestMapping("/Add_HuoJiang.do")
	public String Add_HuoJiang(ModelMap model, @RequestParam("project_name") String project_name,
			@RequestParam("organize_name") String organize_name, @RequestParam("lev_select") String project_id,

			@RequestParam("logmin") String logmin, @RequestParam("userteam_name") String userteam_name,
			@RequestParam("userteam_num") Integer userteam_num) throws Exception {
		JY_HuoJiang huojiang = new JY_HuoJiang();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date starttime = formatter.parse(logmin);

		Double gpa = sumGPA(project_id);
		System.out.println(gpa);
		model.addAttribute("totalGpa", gpa);
		String id = UUID.randomUUID().toString();
		huojiang.setRewardinfo_id(id);
		huojiang.setProjectlev_id(project_id);
		huojiang.setRewardinfo_gettime(starttime);
		huojiang.setRewardinfo_name(project_name);
		huojiang.setRewardinfo_organizename(organize_name);
		jy_projectservice.Add_HuoJiang(huojiang);

		String project_record = UUID.randomUUID().toString();
		Date sbtime = new Date();
		String tablename = "tch_rewardinfo";
		JY_Record jy_record = new JY_Record();
		jy_record.setRecord_id(project_record);
		jy_record.setRecord_project_id(id);
		jy_record.setRecord_piont(gpa);
		jy_record.setRecord_sort(tablename);
		jy_record.setRecord_sbtime(sbtime);
		jy_record.setState(0);

		// jy_projectservice.Add_Record(jy_record);
		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", gpa);
		model.addAttribute("record", jy_record);
		String sort = "jy_project";
		model.addAttribute("sort", sort);
		model.addAttribute("list_name", "Huojiang_findAllById.do");
		return "redirect:gpadistribute.do";

	}
	// 论文

	// 展示表格
	@RequestMapping("/lunwen_findAllById.do")
	public ModelAndView lunwen_findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<JY_Lunwen> projects = jy_projectservice.findLunwenById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		model.addAttribute("infoId", uuidString);
		System.out.println(uuidString);
		mv.addObject("paperList", projects);
		mv.setViewName("jy_lunwen-list");
		return mv;
	}

	@RequestMapping("/lunwen_Lev.do")
	public ModelAndView lunwen_Lev() throws Exception {
		ModelAndView mv = new ModelAndView();
		String ID = "4";
		List<JY_ProjectLev> list_lev = jy_projectservice.findLevByCategoryID(ID);
		mv.addObject("list_lev", list_lev);
		mv.setViewName("jy_project_lunwen");
		return mv;
	}

	// 论文添加
	@RequestMapping("/save.do")
	public String save(ModelMap model, @ModelAttribute("form") JY_Lunwen lunwen,
			@RequestParam("paperinfo_Time") String paperinfo_time, @RequestParam("userteam_name") String userteam_name,
			@RequestParam("userteam_num") Integer userteam_num) throws Exception {
		String id = UUID.randomUUID().toString();

		lunwen.setPaperinfo_id(id);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date starttime = formatter.parse(paperinfo_time);
		lunwen.setPaperinfo_time(starttime);

		Double sumGpa = sumGPA(lunwen.getProject_id());

		jy_projectservice.Add_LunWen(lunwen);
		JY_Record record = new JY_Record();
		String record_id = UUID.randomUUID().toString();
		record.setRecord_id(record_id);
		record.setRecord_project_id(lunwen.getPaperinfo_id());
		record.setRecord_piont(sumGpa);
		record.setRecord_sbtime(new Date());
		String tablename = "tch_paperinfo";
		record.setRecord_sort(tablename);

		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("record", record);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", sumGpa);
		model.addAttribute("record", record);
		String sort = "jy_project";
		model.addAttribute("sort", sort);
		model.addAttribute("list_name", "lunwen_findAllById.do");
		return "redirect:gpadistribute.do";

	}

	// 计算业绩点
	public Double sumGPA(String projectlev_id) throws Exception {
		Double gpa = jy_projectservice.findgpaByprojectId(projectlev_id);
		return gpa;
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
