package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.DictRatio;
import cn.gpa.zut.domain.Userteam;

public interface IUserteamDao {
	        //根据条件查询参数信息
			@Select("select * from userteam")
			List<Userteam> getLev(String id) throws Exception;
			//查询所有参数信息
			@Select("select * from userteam")
			List<Userteam> findAll() throws Exception;
}
