package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.WY_BiaoYan;
import cn.gpa.zut.domain.WY_ChuBan;
import cn.gpa.zut.domain.WY_ProjectLev;
import cn.gpa.zut.domain.WY_Record;

public interface Wy_ProjectDao {
	
	@Insert("insert into wy_chubaninfo(wy_project_id,wy_project_name, wy_project_type,wy_lev_select,wy_zutsign,wy_press_name,wy_press_lev,wy_qk_name,wy_qk_num,wy_zp_num,wy_zp_size,wy_cb_time) "
	+ "value(#{project_id},#{project_name},#{project_type},#{lev_select},#{wy_zutsign},#{press_name},#{press_lev},#{qk_name},#{qk_num},#{zp_num},#{zp_size},#{cb_time})")
	 
	void Add_WyChuBan(WY_ChuBan wy_chuban)throws Exception;
	
	@Insert("INSERT INTO wy_biaoyaninfo(wy_project_id,wy_project_name,wy_project_type,wy_project_lev,wy_project_time)  "
			+ "value(#{project_id},#{project_name},#{project_type},#{project_lev},#{project_time})")
	
	void Add_WYBiaoYan(WY_BiaoYan wy_biaoyan)throws Exception;
	
	
	@Insert("insert into wy_record(record_id,record_sort,record_project_id,record_sbtime,record_state,record_local,record_piont) "
			+ "value(#{record_id},#{record_sort},#{record_project_id},#{record_sbtime},#{state},#{Record_local},#{record_piont})")
	Boolean Add_Record(WY_Record wy_record)throws Exception;
	
	
	
	@Select("select projectlev_GPA from wy_projectlev where project_id='${project_id}' ")
	public Double findgpaByprojectId(@Param("project_id")String project_id)throws Exception;
	
	@Select("select projectlev_khgpa from wy_projectlev where project_id='${project_id}' ")
	public Double findKhgpaByprojectId(@Param("project_id")String project_id)throws Exception;
	
	@Select("select projectlev_name from wy_projectlev where project_id='${project_id}' ")
	public String findNameByprojectId(@Param("project_id")String project_id)throws Exception;
	
	
	@Select("select * from wy_projectlev where wy_category_id='${wy_category_id}' ")
	List<WY_ProjectLev> findWYLevById(@Param("wy_category_id")String project_id);
	
	//查询用户名下信息
			@Select(" select wy_project_id,wy_project_name, wy_project_type,wy_lev_select,wy_zutsign, "
					+ "wy_press_name,wy_press_lev,wy_qk_name,wy_qk_num,wy_zp_num,wy_zp_size,wy_cb_time,record_piont,userteam_getGpa "
					+ "from wy_chubaninfo inner join wy_record on wy_chubaninfo.wy_project_id=wy_record.record_project_id "
					+ "INNER JOIN wy_gpadistr on "
					+ "wy_record.record_id=wy_gpadistr.record_id and wy_gpadistr.user_Id=#{id}")
		    @Results({
		    	@Result(id = true, property = "project_id", column = "wy_project_id"),
		        @Result(property = "project_name", column = "wy_project_name"),
		        @Result(property = "project_type", column = "wy_project_type"),
		        @Result(property = "lev_select", column = "wy_lev_select"),
		        @Result(property = "wy_zutsign", column = "wy_zutsign"),
		        @Result(property = "press_name", column = "wy_press_name"),
		        @Result(property = "press_lev", column = "wy_press_lev"),
		        @Result(property = "qk_name", column = "wy_qk_name"),
		        @Result(property = "qk_num", column = "wy_qk_num"),
		        @Result(property = "zp_num", column = "wy_zp_num"),
		        @Result(property = "zp_size", column = "wy_zp_size"),
		        @Result(property = "cb_time", column = "wy_cb_time"),
		        @Result(property = "record_piont", column = "record_piont"),
		    	@Result(property = "gpaDistr",column="userteam_getGpa")
		    })
			List<WY_ChuBan> findChuBanById(String id);
			
			//查询用户名下信息
			@Select(" select wy_project_id,wy_project_name, wy_project_type,wy_project_lev, "
					+ "wy_project_time,record_piont,userteam_getGpa "
					+ "from wy_biaoyaninfo inner join wy_record on wy_biaoyaninfo.wy_project_id=wy_record.record_project_id "
					+ "INNER JOIN wy_gpadistr on "
					+ "wy_record.record_id=wy_gpadistr.record_id and wy_gpadistr.user_Id=#{id}")
		    @Results({
		    	@Result(id = true, property = "project_id", column = "wy_project_id"),
		        @Result(property = "project_name", column = "wy_project_name"),
		        @Result(property = "project_type", column = "wy_project_type"),
		        @Result(property = "project_lev", column = "wy_project_lev"),
		        @Result(property = "project_time", column = "wy_project_time"),
		        @Result(property = "record_piont", column = "record_piont"),
		    	@Result(property = "gpaDistr",column="userteam_getGpa")
		    })
			List<WY_BiaoYan> findbiaoyanById(String id);


}
