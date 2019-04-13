package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.Paper;



public interface IPaperDao {
	
	//查询所有论文信息
	@Select("select * from sci_paperinfo")
	List<Paper> findAll() throws Exception;
	
	@Insert("insert into sci_paperinfo(paperinfo_Id,paperinfo_Author,paperinfo_Name,paperinfo_CN,paperinfo_ISSN,paperinfo_Time,paperinfo_Lev,paperinfo_orglev,paperinfo_getGpa)"
			+ " values(#{paperinfo_Id},#{paperinfo_Author},#{paperinfo_Name},#{paperinfo_CN},#{paperinfo_ISSN},#{paperinfo_Time},#{paperinfo_Lev},#{paperinfo_orglev},#{paperinfo_getGpa})")
	void save(Paper paper);
	

}
