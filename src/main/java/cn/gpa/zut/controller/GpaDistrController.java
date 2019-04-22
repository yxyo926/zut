package cn.gpa.zut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.service.IGpaDistrService;

@Controller
@RequestMapping("/gpadistr")
public class GpaDistrController {
	@Autowired
	private IGpaDistrService gpaDistrService;
	//信息详情页面
		@RequestMapping("/findAllGpa.do")
		public ModelAndView findByPaper(ModelMap model, @RequestParam(name = "id", required = true) String Id)
				throws Exception {
			ModelAndView mv = new ModelAndView();
			List<GpaDistr> gpaDistrs = gpaDistrService.findAllById(Id);
			mv.addObject("gpaDistrsList", gpaDistrs);
			mv.setViewName("gpadistrdetails");
			return mv;
		}

}
