package cn.gpa.zut.controller;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.User;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.service.IUserService;
import cn.gpa.zut.service.TeamService;
import cn.gpa.zut.utils.UUIDUtils;

@Controller
@RequestMapping("/team")
@SessionAttributes(value = { "Userteam" })
public class TeamController {
	
	@Autowired
	private TeamService teamservice;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/findAll_user.do")
	public ModelAndView findAll_user()throws Exception{
		ModelAndView mv=new ModelAndView();
		List<User> list_user=teamservice.findAll_uer();
		mv.addObject("list_user",list_user);
		mv.setViewName("team");
		return mv;
		
	}
	
	@RequestMapping("/findUserByCollege.do")
	@ResponseBody
	public List<User> findAllUserByCollege(@RequestParam("user_college") String user_college)throws Exception{
		
		System.out.println("user_college:"+user_college);
		List<User> list_user=teamservice.findAllUserByCollege( user_college);
		for(int i = 0 ; i < list_user.size() ; i++) {
			  System.out.println(list_user.get(i).toString());
			}
		
		return list_user;
		
	}
	
	@RequestMapping("/findUserByUserName.do")
	@ResponseBody
	public List<User> findAllUserByUserName(@RequestParam("user_name") String user_name)throws Exception{
		System.out.println("user_name:"+user_name);
		List<User> list_user=teamservice.findAllUserByUserName(user_name);
		for(int i = 0 ; i < list_user.size() ; i++) {
			  System.out.println(list_user.get(i).toString());
			}
		return list_user;
		
	}
	
	
	@RequestMapping("/AddTeam.do")	
	public String AddTeam(ModelMap model, @ModelAttribute("form") Paper paper,
			@RequestParam("teamname") String userteam_name, @RequestParam("userteam_num")int userteam_num)
			throws Exception {

		Userteam userteam = isExist(userteam_name, userteam_num);
		System.out.println(userteam.toString());
		model.addAttribute("Userteam", userteam);
		return "redirect:gpadistribute.do";
	}
	
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
	// 判断回传的用户组数据是否存在
		public Userteam isExist(String userteam_name, Integer userteam_num) throws Exception {
			List<Userteam> userteams = teamservice.findAll();
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
			System.out.println("isExist:"+userteam_name);
			teamservice.AddTeam(userteam);
			return userteam;
		}

	

}
