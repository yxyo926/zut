package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.User;
import cn.gpa.zut.domain.Userteam;




public interface TeamService {
	

	
	List<User> findAll_uer() throws Exception;
	List<Userteam> findAll() throws Exception;
	
	List<User> findAllUserByCollege(String user_college) throws Exception;
	List<User> findAllUserByUserName(String user_name) throws Exception;
	
	Boolean AddTeam(Userteam userteam) throws Exception;

}
