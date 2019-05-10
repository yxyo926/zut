  package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import cn.gpa.zut.domain.User;

public interface IUserDao {
	
	@Select("select * from user")
	public List<User> findAll();
	//根据ID找对象
    @Select("select * from sys_user where user_Id=#{username}")
	public User findByUsername(String username);
     //根据id查对象
  	@Select("select * from sys_user where user_id=#{id}")
  	@Results({ @Result(id = true, property = "user_Id", column = "user_id"),
		@Result(property = "user_name", column = "username"),
		@Result(property = "user_password", column = "password"),
		@Result(property = "user_phone", column = "mobile"),
		@Result(property = "user_email", column = "email"),
		@Result(property = "user_active", column = "status"),
		@Result(property = "salt", column = "salt"),
		@Result(property = "department_Id", column = "dept_id"),
		@Result(property = "create_time", column = "create_time"),
		@Result(property = "is_admin", column = "is_admin"),
		})
  	public User findById(String id);
}
