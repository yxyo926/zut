package cn.gpa.zut.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.User;
import cn.gpa.zut.domain.Userteam;



public interface TeamDao {
	
	
	@Insert("insert into userteam(userteam_id,userteam_name,userteam_num) value(#{userteam_id},#{userteam_name},#{userteam_num})")
	Boolean AddTeam(Userteam userteam)throws Exception;
	

	
	@Select("select user_Id,user_name,user_college from user")
	List<User> findAll_uer() throws Exception;
	
	@Select("select userteam_name from userteam")
	List<Userteam> findAll() throws Exception;
	
	@Select("select user_Id,user_name,user_college from user where user_college='${user_college}' ")
	List<User> findAllUserByCollege(@Param("user_college") String user_college) throws Exception;
	
	@Select("select user_Id,user_name,user_college from user where user_name='${user_name}' ")
	List<User> findAllUserByName(@Param("user_name") String user_name) throws Exception;
	

}
