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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.service.IGpaDistrService;
import cn.gpa.zut.service.IRecordService;
import cn.gpa.zut.service.IUserService;
import cn.gpa.zut.service.IUserteamService;
import cn.gpa.zut.service.Jy_PingTaiService;
import cn.gpa.zut.service.Jy_ProjectService;
import cn.gpa.zut.utils.UUIDUtils;

@Controller
@RequestMapping("/jy_pingtai")
@SessionAttributes(value = { "infoId", "Userteam", "totalGpa", "recordId", "sort" })
public class Jy_PingTaiController {
	@Autowired
	private Jy_PingTaiService jy_pingtaiservice;
	@Autowired
	private Jy_ProjectService jy_projectservice;
	@Autowired
	private IUserteamService userteamService;
	//这是业绩点分配
		@Autowired
		private IGpaDistrService gpaDistrService;
		@Autowired 
		private IUserService userService;
		@Autowired
		private IRecordService recordService;
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
				     //wotai
	
	
	
	
	
	@RequestMapping("/FindAll_lev.do")
	public ModelAndView findLevBycategory() throws Exception{
		ModelAndView mv=new ModelAndView();
		List<JY_ProjectLev> list_lev=jy_pingtaiservice.findLevByCategoryID();
		mv.addObject("list_lev", list_lev);
		mv.setViewName("jy_pingtai");
		return mv;
		
	}
	
	//展示表格
	   @RequestMapping("/pingtai_findAllById.do")
	   public ModelAndView pingtaifindAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
			throws Exception {
		System.out.println(userId);
		ModelAndView mv = new ModelAndView();
		List<JY_pingtai> projects = jy_pingtaiservice.findpingtaiById(userId);
		UUIDUtils uuidUtils = new UUIDUtils();
		String uuidString = uuidUtils.getUUID();
		model.addAttribute("infoId", uuidString);
		System.out.println(uuidString);
		mv.addObject("projectList", projects);
		mv.setViewName("jy_pingtai-list");
		return mv;
	}
	   
	   
	   
	   
	
	//提交教学平台信息
		@RequestMapping("/Add_pingtai.do")
		public String Add_pingtai(ModelMap model,
				@RequestParam("pt_name") String pt_name,
				@RequestParam("pt_type") String pt_type,
				@RequestParam("pt_place") String pt_place,
				@RequestParam("pingtai_lev") String pingtai_lev,
				@RequestParam("kaohe_lev") String kaohe_lev,
				@RequestParam("logmin") String logmin,
				@RequestParam("logmax") String logmax,
				@RequestParam("userteam_name") String userteam_name, @RequestParam("userteam_num") Integer userteam_num
				)throws Exception {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date starttime = formatter.parse(logmin);
			Date finishtime = formatter.parse(logmax);
			JY_pingtai jy_pt=new JY_pingtai();	
			System.out.println("平台等级id"+pingtai_lev);
			System.out.println("平台kaohe_lev值"+kaohe_lev);
			
			String id=UUID.randomUUID().toString(); 
			jy_pt.setPlateforminfo_id(id);
			jy_pt.setPlateforminfo_name(pt_name);
			jy_pt.setPlateforminfo_type(pt_type);
			jy_pt.setPlateforminfo_organize(pt_place);
			jy_pt.setPlateforminfo_Tlev(pingtai_lev);
			jy_pt.setPlateforminfo_Tlev(kaohe_lev);
			jy_pt.setPlateforminfo_starttime(starttime);
			jy_pt.setPlateforminfo_finishtime(finishtime);
			
			jy_pingtaiservice.Add_pingtai(jy_pt);
			
			String project_record=UUID.randomUUID().toString();
			Date sbtime=new Date();
			String tablename="tch_reforminfo";
			JY_Record jy_record=new JY_Record();
			jy_record.setRecord_id(project_record);
			jy_record.setRecord_project_id(id);
			jy_record.setRecord_sort(tablename);
			jy_record.setRecord_sbtime(sbtime);
			jy_record.setState(0);
			
			jy_projectservice.Add_Record(jy_record);
			Double sumGpa = sumGPA(pingtai_lev,kaohe_lev);
//
			Userteam userteam = isExist(userteam_name, userteam_num);
			model.addAttribute("Userteam", userteam);
			model.addAttribute("totalGpa", sumGpa);
			String sort = "assess";
			model.addAttribute("sort", sort);
			return "redirect:gpadistribute.do";
		}

		//
		//需要复制
		public Double sumGPA(String projectlev_id,String kaohe_lev) throws Exception {

			Double gpa=jy_projectservice.findgpaByprojectId(projectlev_id);
			Double khgpa=jy_projectservice.findKhgpaByprojectId(projectlev_id);
			Integer kao=Integer.getInteger(kaohe_lev);
			Double xs=Double.parseDouble(kaohe_lev);
			System.out.println(gpa);
			System.out.println(khgpa);
			System.out.println(kaohe_lev);
			System.out.println(xs);
			Double sumgpa=gpa+khgpa*xs;
			return sumgpa ;
			
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
