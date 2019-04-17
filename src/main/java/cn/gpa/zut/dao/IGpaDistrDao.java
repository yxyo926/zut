package cn.gpa.zut.dao;

import org.apache.ibatis.annotations.Insert;

import cn.gpa.zut.domain.GpaDistr;

public interface IGpaDistrDao {
   @Insert("insert into sci_gpadistr(gpadistr_id,userteam_id,record_id,user_Id,userteam_profession,userteam_getGpa)"
			+ " values(#{gpadistr_id},#{userteam_id},#{record_id},#{user_Id},#{userteam_profession},#{userteam_getGpa})")
	void save(GpaDistr gpaDistr);

}
