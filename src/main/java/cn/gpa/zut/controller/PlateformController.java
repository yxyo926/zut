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

import org.apache.commons.net.ftp.FTPClient;
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

import com.alibaba.fastjson.JSON;

import cn.gpa.zut.domain.Sys_Ratio;
import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Plateform;
import cn.gpa.zut.domain.Record;
import cn.gpa.zut.domain.Sys_Dict;
import cn.gpa.zut.domain.User;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.service.ISys_RatioService;
import cn.gpa.zut.service.IGpaDistrService;
import cn.gpa.zut.service.IPerformance_typeService;
import cn.gpa.zut.service.IPlateformService;
import cn.gpa.zut.service.IRecordService;
import cn.gpa.zut.service.ISys_DictService;
import cn.gpa.zut.service.IUserService;
import cn.gpa.zut.service.IUserteamService;
import cn.gpa.zut.utils.Test;
import cn.gpa.zut.utils.UUIDUtils;

@Controller
@RequestMapping("/plateform")
@SessionAttributes(value = { "dictRatios","object","gpadistrlist","infoID", "Userteam", "totalGpa", "record", "sort" })
public class PlateformController {
	@Autowired
	public IPlateformService plateformService;
	@Autowired
	private IUserteamService userteamService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IGpaDistrService gpaDistrService;
	@Autowired
	private IRecordService recordService;
	@Autowired
	private IPerformance_typeService performanceService;
	@Autowired
	private ISys_DictService sys_dictService;
	@Autowired
	private ISys_RatioService sys_RatioService;

	// 
	private Plateform plateform = new Plateform();
	List<GpaDistr> gpaDistrs;
	Record record = new Record();
	String infoID;
	private String fileUrl = null;
	private String rfilename = null;

	//
	@RequestMapping("/findAllById.do")
	public ModelAndView findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<Plateform> projects = plateformService.findAllById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		infoID = uuidString;
		System.out.println(uuidString);
		plateform = new Plateform();
		plateform.setPlateforminfo_id(uuidString);
		model.addAttribute("object", plateform);
		model.addAttribute("infoID", infoID);
		String recordString = uuidUtils.getUUID();
		record.setRecord_Id(recordString);
		model.addAttribute("record", record);
		System.out.println("这是添加编号" + uuidString);
		mv.addObject("projectList", projects);
		mv.setViewName("plateform-list");
		return mv;
	}

	// 获取参数与系数信息并跳转信息添加页面
	@RequestMapping("/getSort.do")
	public ModelAndView getSort() throws Exception {
		ModelAndView mv = new ModelAndView();
		Integer thirdId = performanceService.getIdByName("科研、学科平台");
		List<Sys_Dict> typesort=sys_dictService.getDicts(thirdId);
		System.out.println(typesort.toString());
		List<Userteam> userteams = userteamService.findAll();
		mv.addObject("typesort", typesort);
		mv.addObject("userteams", userteams);
		mv.setViewName("plateform-add");
		return mv;
	}
	// 修改业绩点页面
		@RequestMapping("/updateInfo.do")
		@ResponseBody
		public ModelAndView updateInfo(ModelMap model, @RequestParam(name = "id", required = true) String id,
				HttpServletRequest request) throws Exception {
			plateform = plateformService.findById(id);
			record = recordService.findByInfoId(id);
			//添加级别选项
			ModelAndView mv = new ModelAndView();
			Integer thirdId = performanceService.getIdByName("科研、学科平台");
			List<Sys_Dict> typesort=sys_dictService.getDicts(thirdId);
			System.out.println(typesort.toString());
			mv.addObject("typesort", typesort);
			//
			List<Userteam> userteams = userteamService.findAll();
			model.addAttribute("infoID", id);
			model.addAttribute("object", plateform);
			model.addAttribute("record", record);
			mv.addObject("userteams", userteams);
			mv.setViewName("plateform-add");
			return mv;
		}
		// 添加考核业绩点页面
		@RequestMapping(value = "/addchecklev.do", produces = "application/json;charset=utf-8")
		@ResponseBody
		public String addCheckGpa(String infoid,ModelMap model) throws Exception {
			System.out.println("这是要考核的编号" + infoid);
			Plateform object = plateformService.findById(infoid);
			Integer id=sys_dictService.getParentId(object.getPlateforminfo_lev());
			System.out.println(id);
			List<Sys_Ratio> dictRatios = sys_RatioService.findAllLevbyId(id);
			System.out.println(dictRatios.toString());
			model.addAttribute("dictRatios", dictRatios);
			object.toString();
			String aString = JSON.toJSONString(object);
			return aString;
		}
		// 添加考核业绩点
		@RequestMapping(value = "/savechecklev.do", produces = "application/json;charset=utf-8")
		@ResponseBody
		public String saveChecklev(Plateform levObject, ModelMap model) throws Exception {
			String userteamID = null;
			Userteam userteam;
			System.out.println("这是添加考核的编号" + levObject.getPlateforminfo_id());
			Plateform object = plateformService.findById(levObject.getPlateforminfo_id());
			Record checklevrecord = recordService.findByInfoId(levObject.getPlateforminfo_id());
			List<GpaDistr> gpaDistrlist = gpaDistrService.findAllByRecordId(checklevrecord.getRecord_Id());
			if (gpaDistrlist.size() > 0) {
				GpaDistr gpaDistr = gpaDistrlist.get(0);
				userteamID = gpaDistr.getUserteam_id();
				userteam = userteamService.findById(userteamID);
			} else {
				return null;
			}
			object.toString();
			object.setPlateforminfo_checklev(levObject.getPlateforminfo_checklev());
			System.out.println(levObject.getPlateforminfo_checklev());
			Double checkGpa = sumcheckGPA(object);
			object.toString();
			object.setPlateforminfo_getGpa(object.getPlateforminfo_getGpa() + checkGpa);
			model.addAttribute("object", object);
			model.addAttribute("gpadistrlist", gpaDistrlist);
			model.addAttribute("infoID", levObject.getPlateforminfo_id());
			model.addAttribute("totalGpa", checkGpa);
			model.addAttribute("Userteam", userteam);
			model.addAttribute("record", checklevrecord);
			plateform = object;
			String sort = "plateform";
			model.addAttribute("sort", sort);
			String aString = JSON.toJSONString(object);
			return aString;
		}

	// 信息添加并将数据存入数据库
	@RequestMapping("/save.do")
	public String save(ModelMap model, @ModelAttribute("form") Plateform pla,
			@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num)
			throws Exception {
		plateform=pla;
		Double sumGpa = sumGPA(plateform);
		plateform.setPlateforminfo_getGpa(sumGpa);
//		plateformService.save(plateform);
		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", sumGpa);
		String sort = "plateform";
		model.addAttribute("sort", sort);
		return "redirect:gpadistribute.do";
	}
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
				SessionStatus sessionStatus, @ModelAttribute("form") Paper paper) {
			 gpaDistrs = paper.getGpaDistrs();
			System.out.println("业绩点保存了");
			return "record";

		}
		// 展示记录保存页面
		@RequestMapping("/record.do")
		public ModelAndView record() throws Exception {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("record");
			return mv;
		}
		// 凭证提交
		@RequestMapping("/recordsave.do")
		public String saveRecord(MultipartFile file, Record record1, HttpServletRequest request) throws Exception {
			HttpSession session = request.getSession(true);
			System.out.println(record1.toString());
			saveMessage(file, record1, request);
			session.removeAttribute("Userteam");
			session.removeAttribute("infoId");
			session.removeAttribute("totalGpa");
			session.removeAttribute("record");
			System.out.println(fileUrl);
			// return "redirect:findAll.do";
			User user = (User) session.getAttribute("user");
			return "redirect:findAllById.do?id=" + user.getUser_Id();

		}
		// 信息添加保存
		public String saveMessage(MultipartFile file, Record record1, HttpServletRequest request) throws Exception {
			// 准备属性
			HttpSession session = request.getSession(true);
			GpaDistr gpacheck = null;
			Userteam userteam = (Userteam) session.getAttribute("Userteam");
			// 保存文件返回完整的url
			fileUrl = saveFile(request, file);
			System.out.println("这是返回的保存路径" + fileUrl);
			// 保存info信息
			System.out.println(plateform.toString());
			if (hasExist(plateform)) {
				System.out.println("更新信息");
				plateformService.update(plateform);
				System.out.println(record1.getRecord_Id());
				List<GpaDistr> gpaDistrlist = gpaDistrService.findAllByRecordId(record1.getRecord_Id());
				if (gpaDistrlist.size() > 0) {
					gpacheck = gpaDistrlist.get(0);
				}
				String hasUserString = gpacheck.getUserteam_id();
				if (hasUserString.equals(userteam.getUserteam_id())) {
					// 添加考核业绩点
					if (plateform.getPlateforminfo_checklev() != null) {
						System.out.println("更新业绩点");
						for (int i = 0; i < gpaDistrs.size(); i++) {
							GpaDistr gpalistDistr = gpaDistrlist.get(i);
							GpaDistr gpaDistr = gpaDistrs.get(i);
							GpaDistr oldGpaDistr = gpaDistrlist.get(i);
							gpalistDistr.setUser_Id(gpaDistr.getUser_Id());
							gpalistDistr.setUserteam_profession(gpaDistr.getUserteam_profession());
							gpalistDistr
									.setUserteam_getGpa(gpaDistr.getUserteam_getGpa() + oldGpaDistr.getUserteam_getGpa());
							gpaDistrService.update(gpalistDistr);
						}
					} else {
						System.out.println("更新业绩点");
						for (int i = 0; i < gpaDistrs.size(); i++) {
							GpaDistr gpalistDistr = gpaDistrlist.get(i);
							GpaDistr gpaDistr = gpaDistrs.get(i);
							gpalistDistr.setUser_Id(gpaDistr.getUser_Id());
							gpalistDistr.setUserteam_profession(gpaDistr.getUserteam_profession());
							gpalistDistr.setUserteam_getGpa(gpaDistr.getUserteam_getGpa());
							gpaDistrService.update(gpalistDistr);
						}
					}
					// 更新业绩点分配

				} else {
					System.out.println("重新创建业绩点");
					for (int i = 0; i < gpaDistrlist.size(); i++) {
						gpaDistrService.deletebyID(gpaDistrlist.get(i));
					}
					// 保存业绩点分配
					UUIDUtils uuidUtils = new UUIDUtils();
					for (int i = 0; i < gpaDistrs.size(); i++) {
						String uuidString = uuidUtils.getUUID();
						GpaDistr gpaDistr = gpaDistrs.get(i);
						gpaDistr.setGpadistr_id(uuidString);
						gpaDistr.setRecord_id(record1.getRecord_Id());
						gpaDistr.setUserteam_id(userteam.getUserteam_id());
						gpaDistrService.save(gpaDistr);
						System.out.println(gpaDistr.toString());
					}

				}
				System.out.println("更新记录");
				record.setRecord_proof(fileUrl);
				recordService.update(record1);
			} else {
				System.out.println("添加信息");
				plateformService.save(plateform);
				// 保存业绩点分配
				UUIDUtils uuidUtils = new UUIDUtils();
				for (int i = 0; i < gpaDistrs.size(); i++) {
					System.out.println("添加分配");
					String uuidString = uuidUtils.getUUID();
					GpaDistr gpaDistr = gpaDistrs.get(i);
					gpaDistr.setGpadistr_id(uuidString);
					gpaDistr.setRecord_id(record.getRecord_Id());
					gpaDistr.setUserteam_id(userteam.getUserteam_id());
					gpaDistrService.save(gpaDistr);
				}
				System.out.println("添加记录");
				record.setRecord_proof(fileUrl);
				record.setRecord_status(2);
				recordService.save(record1);
			}
			return fileUrl;
		}

		// 文件保存
		public String saveFile(HttpServletRequest request, MultipartFile file) throws Exception {
			String filePath;
			String ftpHost = "119.23.12.86";
			String ftpPassword = "123456";
			String ftpUserName = "test";
			int ftpPort = 21;
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
			filePath = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/"
					+ (date.get(Calendar.DAY_OF_MONTH)) + "/";
			System.out.println(filePath);
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
			System.out.println(rfilename);
			// 完整的url
			// 存入ftp服务器
			Test t = new Test();
			t.connect("/zut/", ftpHost, 21, ftpUserName, ftpPassword);
			t.createDir(filePath);
			File file1 = newFile;
			t.upload(file1);
			System.out.println("上传ok");
			// 删除tomcat缓存的文件
			newFile.delete();
			// 完整的url
			/* fileUrl = "http://"+ftpHost+":21/zut/" + filePath + newFileName; */
			fileUrl = "/zut/" + filePath + newFileName;
			System.out.println("这是保存的路径" + fileUrl);
			return fileUrl;
		}

		// 判断是否已存在object
		private boolean hasExist(Plateform object) throws Exception {
			// TODO Auto-generated method stub
			List<Plateform> subjects = plateformService.findAll();
			for (Iterator iterators = subjects.iterator(); iterators.hasNext();) {
				Plateform testobject = (Plateform) iterators.next();// 获取当前遍历的元素，指定为Example对象
				if (testobject.getPlateforminfo_id().equals(object.getPlateforminfo_id())) {
					return true;
				}
			}
			return false;
		}
		

	public Double sumGPA(Plateform plateform) throws Exception {

		Double gpa = 0.0;
		Double ratio = 0.0;
		Double sumgpa = 0.0;
		Sys_Dict gpaSubject = sys_dictService.findByid(plateform.getPlateforminfo_lev());
		gpa = gpaSubject.getValue();
		sumgpa = (double) gpa;
		return sumgpa;
	}
	// 考核业绩点计算
		public Double sumcheckGPA(Plateform object) throws Exception {
			Double gpa = 0.0;
			Double ratio = 0.0;
			Double sumgpa = 0.0;
			String infolevString = object.getPlateforminfo_lev();
			Sys_Dict gpaSubject = sys_dictService.findByid(infolevString);
			Sys_Ratio dictRatio = sys_RatioService.findByid(object.getPlateforminfo_checklev());
			ratio = dictRatio.getRatio_ratio();
			gpa = Double.valueOf(gpaSubject.getRemark());
			sumgpa = (double) gpa * ratio;
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
