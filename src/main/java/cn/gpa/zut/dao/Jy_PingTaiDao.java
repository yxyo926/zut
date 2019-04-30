package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.JY_Book;
import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_pingtai;


public interface Jy_PingTaiDao {
	@Select("select * from tch_projectlev where tch_category_id='6' ")
	public List<JY_ProjectLev> findLevBycategory() throws Exception;
	     
	@Insert("insert into tch_plateforminfo"
			+ "(plateforminfo_id,plateforminfo_Tlev ,plateforminfo_name,plateforminfo_type,plateforminfo_organize,plateforminfo_starttime,plateforminfo_finishtime ) "
			+ "value"
			+ "(#{plateforminfo_id},#{plateforminfo_Tlev},#{plateforminfo_name},#{plateforminfo_type},#{plateforminfo_organize},#{plateforminfo_starttime},#{plateforminfo_finishtime})")
			Boolean Add_pt(JY_pingtai jy_pingtai)throws Exception;
	
	//查询用户名下信息
		@Select(" select plateforminfo_id,plateforminfo_name,plateforminfo_Tlev,plateforminfo_type,"
				+ "plateforminfo_organize,plateforminfo_starttime,plateforminfo_finishtime,userteam_getGpa "
				+ "from tch_plateforminfo inner join tch_record on" + 
				" tch_plateforminfo.plateforminfo_id=tch_record.record_project_id INNER JOIN sci_gpadistr on "
				+ "tch_record.record_Id=sci_gpadistr.record_id and sci_gpadistr.user_Id=#{id}")
	    @Results({
	    	@Result(id = true, property = "plateforminfo_id", column = "plateforminfo_id"),
	        @Result(property = "plateforminfo_name", column = "plateforminfo_name"),
	        @Result(property = "plateforminfo_Tlev", column = "plateforminfo_Tlev"),
	        @Result(property = "plateforminfo_type", column = "plateforminfo_type"),
	        @Result(property = "plateforminfo_organize", column = "plateforminfo_organize"),
	        @Result(property = "plateforminfo_starttime", column = "plateforminfo_starttime"),
	        @Result(property = "plateforminfo_finishtime", column = "plateforminfo_finishtime"),
	    	@Result(property = "gpaDistr",column="userteam_getGpa")
	    })
		List<JY_pingtai> findPingtaiById(String id);

}
