package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import cn.gpa.zut.domain.GpaDistr;
public interface IGpaDistrDao {
   @Insert("insert into sci_gpadistr(gpadistr_id,userteam_id,record_id,user_Id,userteam_profession,userteam_getGpa)"
			+ " values(#{gpadistr_id},#{userteam_id},#{record_id},#{user_Id},#{userteam_profession},#{userteam_getGpa})")
	void save(GpaDistr gpaDistr);
        //根据条件查询参数信息
        //缺少限定条件user_id保证唯一性
 		@Select("select * from sci_gpadistr where record_id in(SELECT record_id FROM sci_record WHERE recordinfo_id=#{id})")
  	public	GpaDistr findById(String id) throws Exception;
 		//根据信息编号查询所有分配详情
 		@Select("select * from sci_gpadistr where record_id in(SELECT record_id FROM sci_record WHERE recordinfo_id=#{id})")
 	  	List<GpaDistr> findAllById(String id) throws Exception;
}
