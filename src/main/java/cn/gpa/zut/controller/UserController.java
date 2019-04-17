package cn.gpa.zut.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.gpa.zut.domain.User;
import cn.gpa.zut.service.IUserService;

@Controller
@RequestMapping("/login")
//这里用了@SessionAttributes，可以直接把model中的user(也就key)放入其中
//这样保证了session中存在user这个对象
@SessionAttributes("user")
public class UserController {
    
    @Autowired
    private IUserService userServivce;
    
    //正常访问login页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
    //表单提交过来的路径
    @RequestMapping("/checkLogin.do")
    public String checkLogin(User user,Model model){
        //调用service方法
        user = userServivce.checkLogin(user.getUser_Id(), user.getUser_password());
        System.out.println(user.getUser_Id());
        System.out.println(user.getUser_password());
        //若有user则添加到model里并且跳转到成功页面
        if(user != null){
            model.addAttribute("user",user);
            return "/frame/main";
        }
        return "fail";
    }
    
    //测试超链接跳转到另一个页面是否可以取到session值
    @RequestMapping("/anotherpage")
    public String hrefpage(){
        
        return "anotherpage";
    }
    
    //注销方法
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session){
        //通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "login";
    }

    @RequestMapping("/regist")
    public String regist(){
        return "regist";
    }

    @RequestMapping("/doRegist")
    public String doRegist(User user,Model model){
        System.out.println(user.getUser_Id());
        
        return "success";
    }
}
