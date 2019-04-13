package cn.gpa.zut.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.domain.DictPara;
import cn.gpa.zut.domain.DictRatio;
import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Product;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.service.IDictParaService;
import cn.gpa.zut.service.IDictRatioService;
import cn.gpa.zut.service.IPaperService;
import cn.gpa.zut.service.IUserteamService;


@Controller
@RequestMapping("/paper")
public class PaperController {
	@Autowired
	private IPaperService paperService;
	@Autowired
	private IDictParaService dictParaService;
	@Autowired
	private IDictRatioService dictRatioService;
	@Autowired
	private IUserteamService userteamService;
	
	@RequestMapping("/getSort.do")
	public ModelAndView getSort()throws Exception {
		ModelAndView mv = new ModelAndView();
		List<DictPara> dictParas = dictParaService.getSort("01");
		List<DictRatio> dictRatios=dictRatioService.getLev("01");
		List<Userteam>  userteams=userteamService.findAll();
		mv.addObject("dictRatios", dictRatios);
		mv.addObject("dictParas",dictParas );
		mv.addObject("userteams", userteams);
		mv.setViewName("paper-add");
		return mv;
	} 

	@RequestMapping("/findAll.do")
	public ModelAndView findAll() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Paper> papers = paperService.findAll();
		mv.addObject("paperList", papers);
		mv.setViewName("paper-list");
		return mv;
	}
	
	//论文添加
	@RequestMapping("/save.do")
	public String save(Paper paper) throws Exception {	
		Double sumGpa=sumGPA(paper);
		paper.setPaperinfo_getGpa(sumGpa);
        paperService.save(paper);
        return "redirect:findAll.do";
    } 
	
	
	//计算业绩点并baocun
	public Double sumGPA(Paper paper) throws Exception {
		
		int gpa=0;
		Double ratio=0.0;
		Double sumgpa=0.0;
		List<DictPara> dictParas = dictParaService.getSort("01");
		List<DictRatio> dictRatios=dictRatioService.getLev("01");
		paper.getPaperinfo_Lev();
		paper.getPaperinfo_orglev();
	   for(Iterator iterators = dictParas.iterator();iterators.hasNext();){
			 DictPara dictPara = (DictPara) iterators.next();//获取当前遍历的元素，指定为Example对象
			          String name = dictPara.getDictpara_id();
			          if(paper.getPaperinfo_Lev().equals(name)){
			        	  gpa=dictPara.getDictpara_gpa();			        	  
			          }
			      }
	   for(Iterator iterators = dictRatios.iterator();iterators.hasNext();){
		   DictRatio dictRatio = (DictRatio) iterators.next();//获取当前遍历的元素，指定为Example对象
			          String name = dictRatio.getDictratio_id();
			          if(paper.getPaperinfo_orglev().equals(name)){
			        	  ratio=dictRatio.getDictratio_ratio();			        	  
			          }
			      }
	    sumgpa=gpa*ratio;
		return gpa*ratio;
		
		
	}
}
