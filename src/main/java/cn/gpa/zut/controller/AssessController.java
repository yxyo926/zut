package cn.gpa.zut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.domain.Project;
import cn.gpa.zut.service.IAssessService;
import cn.gpa.zut.utils.UUIDUtils;

@Controller
@RequestMapping("/assess")
public class AssessController {
	@Autowired
	public IAssessService assessService;
	// 显示所有论文信息
		@RequestMapping("/findAll.do")
		public ModelAndView findAll() throws Exception {
			ModelAndView mv = new ModelAndView();
			List<Assess> papers = assessService.findAll();
			mv.addObject("paperList", papers);
			mv.setViewName("assess-list");
			return mv;
		}
		
		//显示项目信息
				@RequestMapping("/findAllById.do")
				public ModelAndView findAllById(ModelMap model, @RequestParam(name = "id", required = true) String userId)
						throws Exception {
					System.out.println(userId);
					ModelAndView mv = new ModelAndView();
					List<Assess>projects = assessService.findAllById(userId);
					UUIDUtils uuidUtils = new UUIDUtils();
					String uuidString = uuidUtils.getUUID();
					model.addAttribute("infoId", uuidString);
					System.out.println(uuidString);
					mv.addObject("projectList", projects);
					mv.setViewName("assess-list");
					return mv;
				}

}
