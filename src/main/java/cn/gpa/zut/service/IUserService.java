package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.User;

public interface IUserService {

	List<User> findAll();
	
	User checkLogin(String username,String password);

}
