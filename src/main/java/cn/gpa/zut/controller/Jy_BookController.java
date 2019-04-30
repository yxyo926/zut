package cn.gpa.zut.controller;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.gpa.zut.domain.*;
import cn.gpa.zut.service.*;
import cn.gpa.zut.utils.UUIDUtils;

import org.omg.CORBA.PRIVATE_MEMBER;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/jy_book")
@SessionAttributes(value = { "infoId", "Userteam", "totalGpa", "recordId", "sort" })
public class Jy_BookController {
	@Autowired 
	private Jy_BookService jy_bookservice;

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
	public void down(HttpServletRequest request, HttpServletResponse response) throws Exception{

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
	
	
	//展示表格
	   @RequestMapping("/book_findAllById.do")
	   public ModelAndView bookfindAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<JY_Book> projects = jy_bookservice.findbookById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		model.addAttribute("infoId", uuidString);
		System.out.println(uuidString);
		mv.addObject("bookList", projects);
		mv.setViewName("jy_book-list");
		return mv;
	}

	@RequestMapping("/FindBookLev.do")
	public ModelAndView FindBookLev() throws Exception{
		ModelAndView mv = new ModelAndView();
		List<JY_ProjectLev> book_lev =jy_bookservice.FindBookLev() ;
		List<JY_ProjectLev> language=jy_bookservice.FindBookLanguage();
		List<JY_ProjectLev> builed=jy_bookservice.FindBookBuiled();
		List<JY_ProjectLev> zhubian=jy_bookservice.FindBookZhuBian();
		mv.addObject("book_lev", book_lev);
		mv.addObject("language", language);
		mv.addObject("builed", builed);
		mv.addObject("zhubian", zhubian);
		mv.setViewName("jy_book");
		return mv;
	}
	//提交教材申报
	@RequestMapping("/Add_book.do")
	public String Add_HuoJiang(ModelMap model,
			@RequestParam("book_name") String book_name,
			@RequestParam("book_object") String book_object,
			@RequestParam("book_lev") String book_lev,
			@RequestParam("T_name") String zhubian,
			@RequestParam("book_Enum") String book_Enum,
			@RequestParam("book_Pname") String book_Pname,
			@RequestParam("language") String language,
			@RequestParam("builed") String builed,
			@RequestParam("repiblic") String repiblic,
			@RequestParam("book_press") String book_press,
			@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num)throws Exception {
		JY_Book book=new JY_Book();
		String id=UUID.randomUUID().toString(); 
		book.setBook_id(id);
		book.setBook_name(book_name);
		book.setBook_object(book_object);
		book.setBooklev(book_lev);
		book.setBook_Tname(zhubian);
		book.setBook_Enum(Integer.valueOf(book_Enum).intValue());
		book.setLanguage(language);
		book.setBuild_id(builed);
		book.setRepiblic(1);
		book.setBook_press(book_press);
		
		jy_bookservice.Add_Book(book);
		
		String project_record=UUID.randomUUID().toString();
		Date sbtime=new Date();
		String tablename="tch_bookinfo";
		JY_Record jy_record=new JY_Record();
		jy_record.setRecord_id(project_record);
		jy_record.setRecord_project_id(id);
		jy_record.setRecord_sort(tablename);
		jy_record.setRecord_sbtime(sbtime);
		jy_record.setState(0);
		jy_projectservice.Add_Record(jy_record);
		System.out.println("教材等级"+book_lev);
		System.out.println("语言类型"+language);
		System.out.println("立项"+builed);			
		double sumGpa =sumGPA( book_lev, language, builed,book_Enum,zhubian);

		//wojia
		Userteam userteam = isExist(userteam_name, userteam_num);
		model.addAttribute("Userteam", userteam);
		model.addAttribute("totalGpa", sumGpa);
		String sort = "assess";
		model.addAttribute("sort", sort);
		
		return "redirect:gpadistribute.do";
	}

	//需要复制
	

		public Double sumGPA(String booklev,String booklanguage,String bookbuiled,String book_Enum,String zhubian) throws Exception {
			//教材级别
			Double lev=jy_projectservice.findKhgpaByprojectId(booklev);
			//语言类型
			Double language=jy_projectservice.findKhgpaByprojectId(booklanguage);
			//立项
			Double builed=jy_projectservice.findKhgpaByprojectId(bookbuiled);
			//总字数
			Double num=Double.parseDouble(book_Enum)/10000;
			//主编身份
			Double zb=jy_projectservice.findKhgpaByprojectId(zhubian);
			System.out.println(booklev+"：："+booklev);
			System.out.println(language+"：："+booklanguage);
			System.out.println(builed+"：："+bookbuiled);			
			
			
			
			return lev*language*builed*num*zb ;
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
	@RequestMapping("/fileupload.do")
	public @ResponseBody
	String upload(MultipartFile file, HttpServletRequest request) throws IOException {
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
		// 完整的url
		String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;
		return fileUrl;
	}
	
}
