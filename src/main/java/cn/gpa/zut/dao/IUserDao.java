package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.User;

public interface IUserDao {
	
	@Select("select * from user")
	public List<User> findAll();
	//根据ID找对象
    @Select("select * from user where user_Id=#{username}")
	public User findByUsername(String username);
     //根据id查对象
  	@Select("select * from user where user_Id=#{id}")
  	public User findById(String id);
}
