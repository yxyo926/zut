package cn.gpa.zut.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.gpa.zut.domain.Record;
import cn.gpa.zut.service.IRecordService;

@Controller
@RequestMapping("/record")
public class RecordController {
	@Autowired
	public IRecordService recordService;
	@RequestMapping("show.do")
	public ModelAndView record() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("record");
		return mv;
	}
	//凭证提交
	@RequestMapping("/recordsave.do")
	@ResponseBody
	public String saveRecord(@RequestBody Record  userList) {

		
			Record record = userList;
			recordService.save(record);
		
		System.out.println("nihao");
		return null;

	}
	

}
