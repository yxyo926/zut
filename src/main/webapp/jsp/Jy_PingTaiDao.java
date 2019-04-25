package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
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
	

}
