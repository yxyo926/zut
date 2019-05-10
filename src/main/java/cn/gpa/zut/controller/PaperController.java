package cn.gpa.zut.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.alibaba.fastjson.JSONObject;

import cn.gpa.zut.domain.Sys_Ratio;
import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Project;
import cn.gpa.zut.domain.Record;
import cn.gpa.zut.domain.Sys_Dict;
import cn.gpa.zut.domain.User;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.service.ISys_RatioService;
import cn.gpa.zut.service.IGpaDistrService;
import cn.gpa.zut.service.IPaperService;
import cn.gpa.zut.service.IPerformance_typeService;
import cn.gpa.zut.service.IRecordService;
import cn.gpa.zut.service.ISys_DictService;
import cn.gpa.zut.service.IUserService;
import cn.gpa.zut.service.IUserteamService;
import cn.gpa.zut.utils.Test;
import cn.gpa.zut.utils.UUIDUtils;

@Controller
@RequestMapping("/paper")
@SessionAttributes(value = { "dictRatios", "object", "gpadistrlist", "infoID", "Userteam", "totalGpa", "record",
		"sort" })
// ①将ModelMap中属性名为的属性
//放到Session属性列表中，以便这个属性可以跨请求访问
public class PaperController {
	@Autowired
	private IPaperService paperService;
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

	private Paper paper = new Paper();
	List<GpaDistr> gpaDistrs;
	Record record = new Record();
	String infoID;
	private String fileUrl = null;
	private String rfilename = null;

	// 信息详情页面
	@RequestMapping("/findByPaper.do")
	public ModelAndView findByPaper(ModelMap model, @RequestParam(name = "id", required = true) String paperId)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		List<GpaDistr> gpaDistrs = gpaDistrService.findAllById(paperId);
		mv.addObject("gpaDistrsList", gpaDistrs);
		mv.setViewName("paper-details");
		return mv;
	}

	// 查找用户名下所有业绩记录并将信息编号存入session
	@RequestMapping("/findAllById.do")
	public ModelAndView findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<Paper> papers = paperService.findAllById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		//
		infoID=uuidString;
		paper = new Paper();
		paper.setPaperinfo_Id(uuidString);
		model.addAttribute("object", paper);
		model.addAttribute("infoID", infoID);
		String recordString = uuidUtils.getUUID();
		record.setRecord_Id(recordString);
		model.addAttribute("record", record);
		System.out.println("这是添加编号" + uuidString);
		//
		mv.addObject("paperList", papers);
		mv.setViewName("paper-list");
		return mv;
	}

	// 获取参数与系数信息并跳转信息添加页面
	@RequestMapping("/getSort.do")
	public ModelAndView getSort() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Userteam> userteams = userteamService.findAll();
		mv.addObject("userteams", userteams);
		mv.setViewName("paper-add");
		return mv;
	}
	// 动态返回级联菜单
	@RequestMapping(value = "/getgpasort.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findObject(String id,ModelMap model) throws Exception {
		System.out.println(id);
		String name = "论文";
		int parentid = performanceService.getIdByName(id);
		System.out.println(parentid);
		int pid = performanceService.getIdByParent(parentid, name);
		System.out.println(pid);
		List<Sys_Dict> list = sys_dictService.getDicts(pid);
		String raname="署名系数";
		int ratioparentid=performanceService.getIdByParent(pid,raname);
		System.out.println("这是系数父类"+ratioparentid);
		List<Sys_Ratio> dictRatios = sys_RatioService.findAllLevbyId(ratioparentid);
		model.addAttribute("dictRatios", dictRatios);
		JSONObject obj = new JSONObject();
		obj.put("list", list);
		String a = JSON.toJSONString(obj);// 数据转为JSON格式的
		System.out.println(a);
		return a;
	}

	// 修改业绩点页面
	@RequestMapping("/updateInfo.do")
	@ResponseBody
	public ModelAndView updateInfo(ModelMap model, @RequestParam(name = "id", required = true) String id,
			HttpServletRequest request) throws Exception {
		paper = paperService.findByPaperId(id);
		record = recordService.findByInfoId(id);
		ModelAndView mv = new ModelAndView();
		List<Userteam> userteams = userteamService.findAll();
		model.addAttribute("infoID", id);
		model.addAttribute("object", paper);
		model.addAttribute("record", record);
		mv.addObject("userteams", userteams);
		// 改动
		mv.setViewName("project-add");
		return mv;
	}

	// 论文信息添加并将数据存入数据库
	@RequestMapping("/save.do")
	public String save(ModelMap model, @ModelAttribute("form") Paper rpaper,
			@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num)
			throws Exception {
		paper=rpaper;
		Double sumGpa = sumGPA(paper);
		paper.setPaperinfo_getGpa(sumGpa);
		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", sumGpa);
		String sort = "paper";
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
		session.removeAttribute("infoID");
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
		System.out.println(paper.toString());
		if (hasExist(paper)) {
			System.out.println("更新信息");
			paperService.update(paper);
			System.out.println(record1.getRecord_Id());
			List<GpaDistr> gpaDistrlist = gpaDistrService.findAllByRecordId(record1.getRecord_Id());
			if (gpaDistrlist.size() > 0) {
				gpacheck = gpaDistrlist.get(0);
			}
			String hasUserString = gpacheck.getUserteam_id();
			if (hasUserString.equals(userteam.getUserteam_id())) {
				// 添加考核业绩点
					System.out.println("更新业绩点");
					for (int i = 0; i < gpaDistrs.size(); i++) {
						GpaDistr gpalistDistr = gpaDistrlist.get(i);
						GpaDistr gpaDistr = gpaDistrs.get(i);
						gpalistDistr.setUser_Id(gpaDistr.getUser_Id());
						gpalistDistr.setUserteam_profession(gpaDistr.getUserteam_profession());
						gpalistDistr.setUserteam_getGpa(gpaDistr.getUserteam_getGpa());
						gpaDistrService.update(gpalistDistr);
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
			paperService.save(paper);
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
		fileUrl = "http://" + ftpHost + ":21/zut/" + filePath + newFileName;
		fileUrl = "/zut/" + filePath + newFileName;
		System.out.println("这是保存的路径" + fileUrl);
		return fileUrl;
	}

	// 判断是否已存在object
	private boolean hasExist(Paper object) throws Exception {
		// TODO Auto-generated method stub
		List<Paper> subjects = paperService.findAll();
		for (Iterator iterators = subjects.iterator(); iterators.hasNext();) {
			Paper testobject = (Paper) iterators.next();// 获取当前遍历的元素，指定为Example对象
			if (testobject.getPaperinfo_Id().equals(object.getPaperinfo_Id())) {
				return true;
			}
		}
		return false;
	}

	// 计算业绩点
	public Double sumGPA(Paper paper) throws Exception {

		Double gpa = 0.0;
		Double ratio = 0.0;
		Double sumgpa = 0.0;
		paper.getPaperinfo_Lev();
		paper.getPaperinfo_orglev();
		Sys_Dict sys_Dict=sys_dictService.findByid(paper.getPaperinfo_Lev());
		gpa = sys_Dict.getValue();
		Sys_Ratio sys_Ratio=sys_RatioService.findByid(paper.getPaperinfo_orglev());	
		ratio=sys_Ratio.getRatio_ratio();

		sumgpa = gpa * ratio;
		return gpa * ratio;
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
