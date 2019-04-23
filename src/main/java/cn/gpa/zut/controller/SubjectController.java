package cn.gpa.zut.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.domain.DictPara;
import cn.gpa.zut.domain.DictRatio;
import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Plateform;
import cn.gpa.zut.domain.Record;
import cn.gpa.zut.domain.Subject;
import cn.gpa.zut.domain.User;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.service.IAssessService;
import cn.gpa.zut.service.IDictParaService;
import cn.gpa.zut.service.IDictRatioService;
import cn.gpa.zut.service.IGpaDistrService;
import cn.gpa.zut.service.IRecordService;
import cn.gpa.zut.service.ISubjectService;
import cn.gpa.zut.service.IUserService;
import cn.gpa.zut.service.IUserteamService;
import cn.gpa.zut.utils.UUIDUtils;

@Controller
@RequestMapping("/subject")
@SessionAttributes(value = { "infoId", "Userteam", "totalGpa", "recordId", "sort" })
public class SubjectController {
	@Autowired
	public ISubjectService subjectService;
	@Autowired
	private IDictParaService dictParaService;
	@Autowired
	private IDictRatioService dictRatioService;
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
		List<Subject> papers = subjectService.findAll();
		mv.addObject("paperList", papers);
		mv.setViewName("subject-list");
		return mv;
	}

	//
	@RequestMapping("/findAllById.do")
	public ModelAndView findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<Subject> projects = subjectService.findAllById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		model.addAttribute("infoId", uuidString);
		System.out.println(uuidString);
		mv.addObject("projectList", projects);
		mv.setViewName("subject-list");
		return mv;
	}

	// 获取参数与系数信息并跳转信息添加页面
	@RequestMapping("/getSort.do")
	public ModelAndView getSort() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<DictPara> dictParas = dictParaService.getSort("10");
		List<DictRatio> dictRatios = dictRatioService.getLev("");
		List<Userteam> userteams = userteamService.findAll();
		mv.addObject("dictRatios", dictRatios);
		mv.addObject("dictParas", dictParas);
		mv.addObject("userteams", userteams);
		mv.setViewName("subject-add");
		return mv;
	}

	// 信息添加并将数据存入数据库
	@RequestMapping("/save.do")
	public String save(ModelMap model, @ModelAttribute("form") Subject subject,
			@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num)
			throws Exception {
		Double sumGpa = sumGPA(subject);
		subject.setSubjectinfo_getGpa(sumGpa);
		subjectService.save(subject);
		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", sumGpa);
		String sort = "subject";
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
	@RequestMapping("/record.do")
	public ModelAndView record() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("record");
		return mv;
	}
	//凭证提交
	@RequestMapping("/recordsave.do")
	public String saveRecord( Record record, HttpServletRequest request) {
		HttpSession session = request.getSession(true); 
        recordService.save(record);
		System.out.println("nihao");
		session.removeAttribute("Userteam");
		session.removeAttribute("infoId");
		session.removeAttribute("totalGpa");
		session.removeAttribute("recordId");
		return "redirect:findAll.do";

	}

	public Double sumGPA(Subject subject) throws Exception {

		int gpa = 0;
		Double ratio = 0.0;
		Double sumgpa = 0.0;
		List<DictPara> dictParas = dictParaService.getSort("05");
		List<DictRatio> dictRatios = dictRatioService.getLev("01");
		subject.getSubjectinfo_lev();
		for (Iterator iterators = dictParas.iterator(); iterators.hasNext();) {
			DictPara dictPara = (DictPara) iterators.next();// 获取当前遍历的元素，指定为Example对象
			String name = dictPara.getDictpara_id();
			if (subject.getSubjectinfo_lev().equals(name)) {
				gpa = dictPara.getDictpara_gpa();
			}
		}
		sumgpa = (double) gpa;
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
