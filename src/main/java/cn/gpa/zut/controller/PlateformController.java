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
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.domain.DictPara;
import cn.gpa.zut.domain.DictRatio;
import cn.gpa.zut.domain.Plateform;
import cn.gpa.zut.domain.Project;
import cn.gpa.zut.domain.Record;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.service.IDictParaService;
import cn.gpa.zut.service.IDictRatioService;
import cn.gpa.zut.service.IGpaDistrService;
import cn.gpa.zut.service.IPlateformService;
import cn.gpa.zut.service.IRecordService;
import cn.gpa.zut.service.IUserService;
import cn.gpa.zut.service.IUserteamService;
import cn.gpa.zut.utils.UUIDUtils;

@Controller
@RequestMapping("/plateform")
@SessionAttributes(value = { "infoId", "Userteam", "totalGpa", "recordId", "sort" })
public class PlateformController {
	@Autowired
	public IPlateformService plateformService;
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
		List<Plateform> papers = plateformService.findAll();
		mv.addObject("paperList", papers);
		mv.setViewName("plateform-list");
		return mv;
	}

	//
	@RequestMapping("/findAllById.do")
	public ModelAndView findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<Plateform> projects = plateformService.findAllById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		model.addAttribute("infoId", uuidString);
		System.out.println(uuidString);
		mv.addObject("projectList", projects);
		mv.setViewName("plateform-list");
		return mv;
	}

	// 获取参数与系数信息并跳转信息添加页面
	@RequestMapping("/getSort.do")
	public ModelAndView getSort() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<DictPara> dictParas = dictParaService.getSort("08");
		List<DictRatio> dictRatios = dictRatioService.getLev("02");
		List<Userteam> userteams = userteamService.findAll();
		mv.addObject("dictRatios", dictRatios);
		mv.addObject("dictParas", dictParas);
		mv.addObject("userteams", userteams);
		mv.setViewName("plateform-add");
		return mv;
	}

	// 信息添加并将数据存入数据库
	@RequestMapping("/save.do")
	public String save(ModelMap model, @ModelAttribute("form") Plateform plateform,
			@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num)
			throws Exception {
		Double sumGpa = sumGPA(plateform);
		plateform.setPlateforminfo_getGpa(sumGpa);
		plateformService.save(plateform);
		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", sumGpa);
		String sort = "plateform";
		model.addAttribute("sort", sort);
		return "redirect:gpadistribute.do";
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

	public Double sumGPA(Plateform plateform) throws Exception {

		int gpa = 0;
		Double ratio = 0.0;
		Double sumgpa = 0.0;
		List<DictPara> dictParas = dictParaService.getSort("05");
		List<DictRatio> dictRatios = dictRatioService.getLev("01");
		plateform.getPlateforminfo_lev();
		for (Iterator iterators = dictParas.iterator(); iterators.hasNext();) {
			DictPara dictPara = (DictPara) iterators.next();// 获取当前遍历的元素，指定为Example对象
			String name = dictPara.getDictpara_id();
			if (plateform.getPlateforminfo_lev().equals(name)) {
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
