package cn.gpa.zut.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.Record;
import cn.gpa.zut.service.IGpaDistrService;
import cn.gpa.zut.service.IRecordService;

@Controller
@RequestMapping("/gpadistr")
@SessionAttributes(value = { "subject", "Userteam", "totalGpa", "record", "sort" })
public class GpaDistrController {
	@Autowired
	private IGpaDistrService gpaDistrService;
	@Autowired
	private IRecordService recordService;
	@RequestMapping("/findAllGpa.do")
	public ModelAndView findByPaper(ModelMap model, @RequestParam(name = "id", required = true) String Id)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		List<GpaDistr> gpaDistrs = gpaDistrService.findAllById(Id);
		Record record =recordService.findByInfoId(Id);
		mv.addObject("gpaDistrsList", gpaDistrs);
		mv.addObject("record", record);
		mv.setViewName("gpadistrdetails");
		return mv;
	}
	@RequestMapping("/findAllRecord.do")
	public ModelAndView findAllRecordUserId(ModelMap model, @RequestParam(name = "id", required = true) String Id)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Record> recordlist = recordService.findAllByUserId(Id);
		mv.addObject("recordlist", recordlist);
		mv.setViewName("record-list");
		return mv;
	}
     
		
//		///保留无用代码
//		@RequestMapping("/downshow.do")
//		public ModelAndView down() throws Exception {
//			ModelAndView mv = new ModelAndView();
//			mv.setViewName("fail");
//			return mv;
//		}
//
//		@RequestMapping("/down.do")
//		public void down(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//			String fileName = request.getSession().getServletContext().getRealPath("resource/uploads/") + fileUrl;
//
//			InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
//
//			String filename = rfilename;
//
//			filename = URLEncoder.encode(rfilename, "UTF-8");
//
//			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
//
//			response.setContentType("multipart/form-data");
//
//			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//			int len = 0;
//			while ((len = bis.read()) != -1) {
//				out.write(len);
//				out.flush();
//			}
//			out.close();
//		}
}
