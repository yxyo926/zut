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

import cn.gpa.zut.dao.Wy_ProjectDao;
import cn.gpa.zut.domain.Allot;
import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.JY_jiaogai;
import cn.gpa.zut.domain.JY_pingtai;
import cn.gpa.zut.domain.User;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.domain.WY_BiaoYan;
import cn.gpa.zut.domain.WY_ChuBan;
import cn.gpa.zut.domain.WY_ProjectLev;
import cn.gpa.zut.domain.WY_Record;
import cn.gpa.zut.service.IGpaDistrService;
import cn.gpa.zut.service.IRecordService;
import cn.gpa.zut.service.IUserService;
import cn.gpa.zut.service.IUserteamService;
import cn.gpa.zut.service.Wy_ChuBanService;
import cn.gpa.zut.utils.UUIDUtils;

@Controller
@RequestMapping("/wenyi")
@SessionAttributes(value = { "infoId", "Userteam", "totalGpa", "record", "sort", "list_name"})
public class Wy_ProjectController {
	@Autowired
	private Wy_ChuBanService wy_projectservice;
	
	
	//这是业绩点分配
	@Autowired
	private IGpaDistrService gpaDistrService;
	@Autowired 
	private IUserService userService;
	@Autowired
	private IUserteamService userteamService;
	@Autowired
	private IRecordService recordService;
	private  String fileUrl;
	private  String rfilename;
	
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
						SessionStatus sessionStatus, @ModelAttribute("form") Allot allot, @ModelAttribute("record") WY_Record  record) {
					
					UUIDUtils uuidUtils = new UUIDUtils();//分配id
					List<GpaDistr> gpaDistrs = allot.getGpaDistrs();						
					for (int i = 0; i < gpaDistrs.size(); i++) {		
						String uuidString = uuidUtils.getUUID();							
						GpaDistr gpaDistr = allot.getGpaDistrs().get(i);
						gpaDistr.setGpadistr_id(uuidString);
						gpaDistr.setRecord_id(record.getRecord_id());							
						gpaDistr.setUserteam_id(uerUserteam.getUserteam_id());							
						gpaDistrService.wy_save(gpaDistr);
					}

					System.out.println("业绩点保存了");
					return "record";
				}

				@RequestMapping("/record.do")
				public ModelAndView record() throws Exception {
					ModelAndView mv = new ModelAndView();
					mv.setViewName("wy_record");
					return mv;
				}

				// 凭证提交
				@RequestMapping("/recordsave.do")
				//@ResponseBody
				public String saveRecord(MultipartFile file, @ModelAttribute("record") WY_Record  record, 
						@ModelAttribute("list_name")  String list_name,
						HttpServletRequest request) throws Exception {
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
					record.setRecord_local(fileUrl);
//					jy_projectservice.Add_Record(record);
					wy_projectservice.Add_Record(record);
					session.removeAttribute("Userteam");
					session.removeAttribute("infoId");
					session.removeAttribute("totalGpa");
					session.removeAttribute("record");
					System.out.println(fileUrl);
					User user=(User) session.getAttribute("user");
					return "redirect:"+list_name+"?id="+user.getUser_Id();
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

//展示表格
   @RequestMapping("/chuban_findAllById.do")
   public ModelAndView WY_ChuBanfindAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
		throws Exception {
	System.out.println(userId);
	ModelAndView mv = new ModelAndView();
	
	List<WY_ChuBan> wy_chuban=wy_projectservice.findChuBanById(userId);
	UUIDUtils uuidUtils = new UUIDUtils();
	String uuidString = uuidUtils.getUUID();
	model.addAttribute("infoId", uuidString);
	System.out.println(uuidString);
	
	mv.addObject("projectList", wy_chuban);

	mv.setViewName("wy_chuban-list");
	return mv;
}
   
   //显示添加
   @RequestMapping("/FindAll_lev.do")
   public ModelAndView FindAll_lev() throws Exception{
	   ModelAndView mv=new ModelAndView();
	   String id1="非学术类等级";
	   String id2="出版社等级";
	   String id3="页面大小";
	   String id4="署名";
	   String id5="期刊名称";
	   String id6="立项系数";
		List<WY_ProjectLev> Fxueshu_lev=wy_projectservice.findLevByCategoryID(id1);
		//System.out.println(Fxueshu_lev.toString());
		List<WY_ProjectLev> press_lev=wy_projectservice.findLevByCategoryID(id2);
		List<WY_ProjectLev> zp_size=wy_projectservice.findLevByCategoryID(id3);
		List<WY_ProjectLev> wy_zutsign=wy_projectservice.findLevByCategoryID(id4);
		List<WY_ProjectLev> qk_name=wy_projectservice.findLevByCategoryID(id5);
		List<WY_ProjectLev> builed_lev=wy_projectservice.findLevByCategoryID(id6);
		
		mv.addObject("Fxueshu_lev", Fxueshu_lev);
		mv.addObject("press_lev", press_lev);
		mv.addObject("zp_size", zp_size);
		mv.addObject("wy_zutsign", wy_zutsign);
		mv.addObject("qk_name", qk_name);
		mv.addObject("builed_lev", builed_lev);
	
		mv.setViewName("wy_chuban");
		return mv;
	   
   }
   
   
	
	//提交文艺出版项目
		@RequestMapping("/Add_chuban.do")
		public String Add_chuban(ModelMap model,@ModelAttribute("form") WY_ChuBan wy_chuban,
				@RequestParam("CB_time") String cb_time,
				@RequestParam("userteam_name") String userteam_name,
				@RequestParam("userteam_num") Integer userteam_num
				)throws Exception {		
			System.out.println("表单提交文艺项目："+wy_chuban.toString());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date starttime = formatter.parse(cb_time);
			String project_id=UUID.randomUUID().toString(); 
		
			wy_chuban.setProject_id(project_id);			
			wy_chuban.setCb_time(starttime);
			wy_chuban.setProject_type(wy_projectservice.findNameByprojectId(wy_chuban.getProject_type()));
			wy_chuban.setLev_select(wy_projectservice.findNameByprojectId(wy_chuban.getLev_select()));
			wy_chuban.setWy_zutsign(wy_projectservice.findNameByprojectId(wy_chuban.getWy_zutsign()));
			wy_chuban.setQk_name(wy_projectservice.findNameByprojectId(wy_chuban.getQk_name()));
			
			
			wy_projectservice.Add_WyChuBan(wy_chuban);
			String project_record=UUID.randomUUID().toString();
				
//			Double gpa=sumGPA(wy_chuban);
			double gpa=10;
			Date sbtime=new Date();
			String tablename="wy_chuban";
			WY_Record wy_record=new WY_Record();
			wy_record.setRecord_id(project_record);
			wy_record.setRecord_project_id(project_id);
			wy_record.setRecord_sort(tablename);
			wy_record.setRecord_sbtime(sbtime);
			wy_record.setRecord_piont(gpa);
			wy_record.setState(0);
			
			Userteam userteam = isExist(userteam_name, userteam_num);
			model.addAttribute("Userteam", userteam);
			model.addAttribute("totalGpa", gpa);
			model.addAttribute("record", wy_record);
			String sort = "wenyi";
			model.addAttribute("list_name", "chuban_findAllById.do");
			model.addAttribute("sort", sort);

			return "redirect:gpadistribute.do";
		}
		
		////////////////////////////////////////////
		//文艺表演
		
		//展示表格
		   @RequestMapping("/biaoyan_findAllById.do")
		   public ModelAndView WY_BiaoYanfindAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
				throws Exception {
			System.out.println(userId);
			ModelAndView mv = new ModelAndView();
			
			List<WY_BiaoYan> wy_biaoyan=wy_projectservice.findBiaoYanById(userId);
			UUIDUtils uuidUtils = new UUIDUtils();
			String uuidString = uuidUtils.getUUID();
			model.addAttribute("infoId", uuidString);
			System.out.println(uuidString);
			
			mv.addObject("projectList", wy_biaoyan);

			mv.setViewName("wy_biaoyan-list");
			return mv;
		}
		
		   //显示添加
		   @RequestMapping("/by_FindAll_lev.do")
		   public ModelAndView BY_FindAll_lev() throws Exception{
			   ModelAndView mv=new ModelAndView();
			   String id="场所";
				List<WY_ProjectLev> project_lev=wy_projectservice.findLevByCategoryID(id);
				mv.addObject("project_lev", project_lev);
				mv.setViewName("wy_biaoyan");
				return mv;
			   
		   }
		   
		 //提交文艺出版项目
			@RequestMapping("/Add_biaoyan.do")
			public String Add_biaoyan(ModelMap model,@ModelAttribute("form") WY_BiaoYan wy_biaoyan,
					@RequestParam("project_Time") String project_Time,
					@RequestParam("userteam_name") String userteam_name,
					@RequestParam("userteam_num") Integer userteam_num
					)throws Exception {		
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date starttime = formatter.parse(project_Time);
				String project_id=UUID.randomUUID().toString(); 
				System.out.println("文艺表演提交："+wy_biaoyan.toString());
				Double gpa=sumGPA2(wy_biaoyan.getProject_lev());
				wy_biaoyan.setProject_id(project_id);			
				wy_biaoyan.setProject_time(starttime);
				wy_biaoyan.setProject_lev(wy_projectservice.findNameByprojectId(wy_biaoyan.getProject_lev()));
				
				wy_projectservice.Add_WyBiaoYan(wy_biaoyan);
				
				String project_record=UUID.randomUUID().toString();
				System.out.println("文艺表演提交："+wy_biaoyan.toString());
				System.out.println("gpa:"+gpa);
				Date sbtime=new Date();
				String tablename="wy_biaoyan";
				WY_Record wy_record=new WY_Record();
				wy_record.setRecord_id(project_record);
				wy_record.setRecord_project_id(project_id);
				wy_record.setRecord_sort(tablename);
				wy_record.setRecord_sbtime(sbtime);
				wy_record.setRecord_piont(gpa);
				wy_record.setState(0);
				
				Userteam userteam = isExist(userteam_name, userteam_num);
				model.addAttribute("Userteam", userteam);
				model.addAttribute("totalGpa", gpa);
				model.addAttribute("record", wy_record);
				String sort = "wenyi";
				model.addAttribute("sort", sort);
				model.addAttribute("list_name", "biaoyan_findAllById.do");
				
				return "redirect:gpadistribute.do";
			}
			
		
		
		
		
		
		
		
		/////////////////////////////////////////////////////////////////////////
		// 计算业绩点
		public double sumGPA(WY_ChuBan chuban) throws Exception {
//			Double gpa=wy_projectservice.findgpaByprojectId(projectlev_id);
//			著作业绩点=每万字业绩点×万字数×出版社级别系数×立项系数
//			公开发表：署名*期刊名称*
//			公开出版学术：页面大小*万字*出版社*立项
			
//			公开出版非学术：页面大小*万字*出版社*立项*30%
			String xueshu=chuban.getProject_type();
			double qk_gpa=wy_projectservice.findgpaByprojectId(chuban.getQk_name());
			double sm_gpa=wy_projectservice.findKhgpaByprojectId(chuban.getWy_zutsign());
			
			double Flev_gpa=wy_projectservice.findgpaByprojectId(chuban.getLev_select());
			double press_gpa=wy_projectservice.findgpaByprojectId(chuban.getPress_lev());
			Double pages=Double.valueOf(chuban.getZp_num())  ;
			double size_gpa=press_gpa*(chuban.getZp_size()/10000);
			double builed_gpa=wy_projectservice.findKhgpaByprojectId(chuban.getBuiled_lev());
			if(xueshu.equals("1")){
				
				
				return qk_gpa*sm_gpa ;

			}else{
				
				return size_gpa*builed_gpa*0.3 ;
			}
			
		}
		
		public Double sumGPA2(String  Project_lev) throws Exception {
			Double gpa=wy_projectservice.findgpaByprojectId(Project_lev);
			
			return gpa ;
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
				
				
				
			
}
