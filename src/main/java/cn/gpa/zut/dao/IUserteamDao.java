package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.Sys_Ratio;
import cn.gpa.zut.domain.Userteam;

public interface IUserteamDao {
	        //根据条件查询参数信息
			@Select("select * from userteam")
			List<Userteam> getLev(String id) throws Exception;
			//查询所有参数信息
			@Select("select * from userteam")
			List<Userteam> findAll() throws Exception;
			@Insert("insert into userteam(userteam_id,userteam_name,userteam_num)"
					+ " values(#{userteam_id},#{userteam_name},#{userteam_num})")
			void save(Userteam userteam);
			@Select("select * from userteam where userteam_id=#{record_Id}")
			Userteam findByRecordId(String record_Id);
			//根据id查用户组
			@Select("select * from userteam where userteam_id=#{userteamID}")
			Userteam findById(String userteamID);
}
